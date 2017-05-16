package ba.unsa.etf;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ba.unsa.etf.Pacijenti;
import ba.unsa.etf.PacijentiRepository;

@RestController
@EnableFeignClients(basePackages = {"ba.unsa.etf","model"})
public class Controller {
	@Autowired
	private PacijentiRepository pr;
	
	@Autowired
	private KorisniciRepository kr;
	
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
	
	
	
}
