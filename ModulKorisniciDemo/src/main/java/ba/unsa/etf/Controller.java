package ba.unsa.etf;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ba.unsa.etf.Pacijenti;
import ba.unsa.etf.PacijentiRepository;
import ba.unsa.etf.security.PasswordResetTokenAuthenticationService;
import ba.unsa.etf.security.TokenAuthenticationService;

@Component
@RestController
@EnableFeignClients(basePackages = {"ba.unsa.etf","model"})
public class Controller {
	@Autowired
    private JavaMailSender javaMailSender;
	
	
	private PasswordResetTokenAuthenticationService prt;
	
	@Autowired
	private PacijentiRepository pr;
	
	static KorisniciRepository kr;
	
	@Autowired
	public Controller(KorisniciRepository kr)
	{
		Controller.kr = kr;
	}
	
	@Autowired
	private PacijentiKorisniciRepository pkr;
	
	@Autowired
	private DijagnozePacijentiClient dpc;
	
	@RequestMapping(method=RequestMethod.POST, value = "/unosPacijenta")
	@ResponseBody
	public Pacijenti unosPacijenta (@RequestBody Pacijenti req, @CookieValue("Authorization") String cookie){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String korisnikUsername = auth.getName();
		
		Pacijenti p = new Pacijenti();
		p=pr.save(req);

		povezi(kr.findByUsername(korisnikUsername).getId(), pr.findIdByName(p.getImePrezime()));
		String novi = "Authorization=" + cookie;
		System.out.println(novi);
		dpc.unosPacijenta(req,novi);
		return p;
	}
	
	@RequestMapping(value= "/brisanjePacijentaPoImenuIPrezimenu", method=RequestMethod.GET)
	@ResponseBody
	public String brisanjePacijenta(@RequestParam("imePrezime") String imePrezime, @CookieValue("Authorization") String cookie) {
		
		int id = pr.findIdByName(imePrezime);
		pr.delete(id);
		String novi = "Authorization=" + cookie;
		dpc.brisanjePacijenta(imePrezime,novi);
		return "User succesfully deleted!";
	}
	
	@RequestMapping(value= "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
		
		Korisnici k = null;
		k = kr.provjeriLogin(username, password);
		if (k != null){
			session.setAttribute("loggedInUser", k);
			return "Succesfully loged in!";
		}else
			return "False username or password";
	}
	
	@RequestMapping(value = "/poveziKorisnikeIPacijente")
	public void povezi(@RequestParam(value="idKorisnika", defaultValue="0") int idKorisnika, @RequestParam(value="idPacijenta", defaultValue="0") int idPacijenta) {
		
		
		Korisnici k = kr.findOne(idKorisnika);
		Pacijenti p = pr.findOne(idPacijenta);
		PacijentiKorisnici pk = new PacijentiKorisnici();
		pk.setPacijenti(p);
		pk.setKorisnici(k);
		pk.setId(idKorisnika, idPacijenta);
		pkr.save(pk);
		k.addPacijentiKorisnici(pk);
		p.addPacijentiKorisnici(pk);
	}
	
	@RequestMapping("/sviPacijentiDoktora")
	 public List<Pacijenti> vratiPacijenteNavedenogDoktora(@RequestParam(value="idKorisnika", defaultValue="0") int idKorisnika)
	 {
	 	
	 	List<Pacijenti> pacijentiDoktora = pr.vratiPacijente(idKorisnika);
	 	
	 	return pacijentiDoktora;
	 			
	 }
	
	@RequestMapping("/dajIdLogovanogKorisnika")
	public int dajIdLogovanogKorisnika()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String korisnikUsername = auth.getName();
		
		return kr.findByUsername(korisnikUsername).getId();
	}
	
	@RequestMapping("/provjeriPacijentaTest")
	public Boolean provjeriPacijentaTest()
	{
		Pacijenti p = pr.findOne(1);
		if(p == null)
		{
			return false;
		}
		return true;
	}
	
	@RequestMapping("/provjeriZadnjuPromjenuPassworda")
	public int provjeriZadnjuPromjenuPassworda(@RequestParam(value="user") String user, @RequestParam(value="issuedAt") String issuedAt, @RequestHeader(value="Cookie") String cookie) 
	{
		System.out.println(cookie);
		Korisnici k = kr.findByUsername(user);
		
		DateFormat df = new SimpleDateFormat("EEE MMMM dd HH:mm:ss zzzz yyyy");
		
		Date datum = new Date();
		try
		{
			datum = df.parse(issuedAt);
		}
		catch (ParseException e) {
		    e.printStackTrace();
		}

	      
	    return k.getPromjenaPassworda().compareTo(datum);
	}
	
	@RequestMapping(value = "/korisnik/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public String resetPassword(HttpServletRequest request, @RequestParam String email) 
	{
		prt = new PasswordResetTokenAuthenticationService();
		Korisnici k = kr.findByEmail(email);
		String token = prt.kreirajToken(k.getUsername());
		MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(email);
            helper.setFrom("projekatnwt@gmail.com");
            helper.setSubject("Promjena passworda");
            helper.setText("http://localhost:8081/modul-za-korisnike/korisnik/promjenaPassworda" + "?id=" + k.getId() + "&token=" + token);
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {}
        javaMailSender.send(mail);
		return "ok";
	}
	
	@RequestMapping(value = "/korisnik/promjenaPassworda", method = RequestMethod.GET)
	public String promjenaPassworda(@RequestParam("id") int id, @RequestParam("token") String token, HttpServletResponse httpServletResponse)
	{
		prt = new PasswordResetTokenAuthenticationService();
		String poruka = prt.validirajToken(token);
		try {
			httpServletResponse.sendRedirect("http://localhost:8081/promijeniPasswordValidanToken.html");
		} catch(IOException e) {
			e.printStackTrace();
		}
		return poruka;
	}
	
	@RequestMapping(value = "/korisnik/sacuvajPassword", method = RequestMethod.POST)
	@ResponseBody
	public String savePassword(@RequestBody String json) 
	{
	    String user = SecurityContextHolder.getContext().getAuthentication().getName();
	    System.out.println(user); 
	    Korisnici k = kr.findByUsername(user);
	    Map jsonJavaRootObject = new Gson().fromJson(json, Map.class);
	    String password = jsonJavaRootObject.get("password").toString();
	    System.out.println(password); 
	    k.setPassword(password);
	    k.setPromjenaPassworda(new Timestamp(System.currentTimeMillis()));
	    kr.save(k);
	    return "Password promijenjen!";
	}
	
	
	
}
