package model;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

}
