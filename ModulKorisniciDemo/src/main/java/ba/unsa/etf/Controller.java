package ba.unsa.etf;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.print.attribute.standard.Media;

import org.eclipse.persistence.oxm.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ba.unsa.etf.Pacijenti;
import ba.unsa.etf.PacijentiRepository;

@RestController
public class Controller {
	@Autowired
	private PacijentiRepository pr;
	
	@Autowired
	private KorisniciRepository kr;
	
	@Autowired
	private PacijentiKorisniciRepository pkr;
	
	@RequestMapping(method=RequestMethod.POST, value = "/unosPacijenta", headers="Accept=application/json", produces="application/json")
	@ResponseBody
	
	public Pacijenti unosPacijenta (@RequestBody Pacijenti req){
		
		return pr.save(req);
		
	}
	
	@RequestMapping(value= "/brisanjePacijentaPoImenuIPrezimenu", method=RequestMethod.GET)
	@ResponseBody
	public String brisanjePacijenta(@RequestParam String imePrezime) {
		
		int id = pr.findIdByName(imePrezime);
		pr.delete(id);
		return "User succesfully deleted!";
	}
	
	@RequestMapping(value= "/login")
	@ResponseBody
	public String login(@RequestParam String username, @RequestParam String password) {
		
		Korisnici k = null;
		k = kr.provjeriLogin(username, password);
		if (k != null)
			return "Succesfully loged in!";
		else
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
	
}
