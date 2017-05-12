package model;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerDijagnozePacijenti {
	
	
	 @Autowired
	 private PacijentiRepository pRepo;
	 	
	 @Autowired
	 private DijagnozeRepository dRepo;
	 	
	 @Autowired
	 private DijagnozePacijentiRepository dpRepo;
	 
	 @RequestMapping("/poveziDijagnozuPacijenta")
	 public void poveziDijagnozuPacijenta(@RequestParam(value="idPacijenta", defaultValue="0") int idPacijenta, @RequestParam(value="idDijagnoze", defaultValue="0") int idDijagnoze)
	 {
	 	Pacijenti p = pRepo.findOne(idPacijenta);
	 	Dijagnoze d = dRepo.findOne(idDijagnoze);
	 	DijagnozePacijenti dp = new DijagnozePacijenti();
	 	dp.setDatumDijagnoze(new Date(System.currentTimeMillis()));
	 	dp.setDijagnoze(d);
	 	dp.setPacijenti(p);
	 	dp.setId(idPacijenta, idDijagnoze);
	 	dpRepo.save(dp);
	 	p.addDijagnozePacijenti(dp);
	 	d.addDijagnozePacijenti(dp);
	 }
	 
	 @RequestMapping("/sveDijagnozePacijenta")
	 public List<Dijagnoze> vratiDijagnozePacijenta(@RequestParam(value="idPacijenta", defaultValue="0") int idPacijenta)
	 {
	 	//Pacijenti p = pRepo.findOne(idPacijenta);
	 	List<Dijagnoze> dijagnozePacijenta = dRepo.vratiDijagnoze(idPacijenta);
	 	
	 	return dijagnozePacijenta;
	 			
	 }
	 
	 @RequestMapping("/provjeriDijagnozuTest")
	 public Boolean provjeriDijagnozuTest()
	 {
		
	 	Dijagnoze d = dRepo.findOne(1);
	 	if(d == null)
	 	{
	 		return false;
	 	}
	 	return true;
	 }
	 
	 @RequestMapping(value="/provjeriDijagnozuTestParam", method = RequestMethod.GET)
	 @ResponseBody
	 public Boolean provjeriDijagnozuTestParam(@RequestParam(value="id")Integer id)
	 {
	 	Dijagnoze d = dRepo.findOne(id);
	 	if(d == null)
	 	{
	 		return false;
	 	}
	 	return true;
	 }
	 
	 @RequestMapping(value = "/prijemDijagnoze1", method = RequestMethod.GET)
	 @ResponseBody
	 public String prijemDijagnoze1(@RequestParam(value="naziv") String naziv, @RequestParam(value="opis") String opis, @RequestHeader(value="Authorization") String token)
	 {
		 //ovdje insert
		 Dijagnoze d = new Dijagnoze();
		 d.setNaziv(naziv);
		 d.setOpis(opis);
		 d.setKoristi(0);
		 d.setPostotak("");
		 dRepo.save(d);
		 
		 return "sve proslo - armin";
	 }
	 
	 @RequestMapping(method=RequestMethod.POST, value = "/unosPacijenta")
	 @ResponseBody
	 public Pacijenti unosPacijenta (@RequestBody Pacijenti req, @RequestHeader(value="Authorization") String token){
			
			Pacijenti p = new Pacijenti();
			p=pRepo.save(req);
			return p;
	 }
	 
	 @RequestMapping(value= "/brisanjePacijentaPoImenuIPrezimenu", method=RequestMethod.GET)
		@ResponseBody
		public String brisanjePacijenta(@RequestParam("imePrezime") String imePrezime) {
			
			int id = pRepo.findIdByName(imePrezime);
			pRepo.delete(id);
			return "User succesfully deleted!";
		}

}
