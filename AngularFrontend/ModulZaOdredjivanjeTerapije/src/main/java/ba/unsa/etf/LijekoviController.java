package ba.unsa.etf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LijekoviController {
	
	private double donjaSecer = 3.8;
	private double gornjaSecer = 6.9;
	
	private double donjaEritrociti = 3.86;
	private double gornjaEritrociti = 5.08;
	
	private double donjaLeukociti = 3.9;
	private double gornjaLeukociti = 10.0;
	
	private double donjaTrombociti = 140.0;
	private double gornjaTrombociti = 450.0;
	
	@Autowired
	private LijekoviRepository lr;
	
	@Autowired
	private DijagnozeRepository dr;
	
	@Autowired
	private DijagnozeLijekoviRepository dlr;
	
	@RequestMapping(value="/poveziDijagnozeILijekove", method =RequestMethod.GET)
	@ResponseBody
	public void povezi(@RequestParam(value="idLijeka", defaultValue="") int idLijeka, 
			           @RequestParam(value="idDijagnoze", defaultValue="") int idDijagnoze)
	{
		Lijekovi l = lr.findOne(idLijeka);
		Dijagnoze d = dr.findOne(idDijagnoze);
		DijagnozeLijekovi dl = new DijagnozeLijekovi();
		dl.setDijagnoze(d);
		dl.setLijekovi(l);
		dl.setId(idLijeka, idDijagnoze);
		dl.setEritrociti(0);
		dl.setLeukociti(0);
		dl.setSecer(0);
		dl.setTrombociti(0);
		dlr.save(dl);
		l.addDijagnozeLijekovi(dl);
		d.addDijagnozeLijekovi(dl);
	}
	
	
	@RequestMapping("/izracunajDozu")
	public String izracunaj(@RequestParam(value="idDijagnoze", defaultValue="0") int idDijagnoze, 
			@RequestParam(value="secer", defaultValue="0") double secer, 
			@RequestParam(value="eritrociti", defaultValue="0") double eritrociti,
			@RequestParam(value="leukociti", defaultValue="0") double leukociti, 
			@RequestParam(value="trombociti", defaultValue="0") double trombociti)
	{
		
		int idLijeka = lr.vratiIdLijeka(idDijagnoze);
		
		Lijekovi l = lr.findOne(idLijeka);
		Dijagnoze d = dr.findOne(idDijagnoze);
		
		double doza = l.getStandardnaDoza();
		
		if(secer < donjaSecer || secer > gornjaSecer)
			doza += 0.3;
		
		if(eritrociti < donjaEritrociti || eritrociti > gornjaEritrociti)
			doza += 0.2;
		
		if(leukociti < donjaLeukociti || leukociti > gornjaLeukociti)
			doza += 0.17;
		
		if(trombociti < donjaTrombociti || trombociti > gornjaTrombociti)
			doza += 0.12;
		
		String data = "{\"naziv\":\""+l.getNaziv()+"\", \"doza\":\""+ Double.toString(doza)+"\" }";
		return data;
	}
	
	
	@RequestMapping(value = "/prijemDijagnoze1", method = RequestMethod.POST)
	@ResponseBody
	public String prijemDijagnoze1(@RequestBody Dijagnoze req, @RequestHeader(value="Cookie") String token)
	{
		dr.save(req);
	 
		return "sve proslo - adi";
	 }
	
	@RequestMapping(value = "/dajLijekove", method = RequestMethod.GET)
	public List<Lijekovi> dajLijekove()
	{
		List<Lijekovi> l = new ArrayList<Lijekovi>();
		for (Lijekovi lijek : lr.findAll()) 
		{
			lijek.setDijagnozeLijekovis(null);
			l.add(lijek);
			
			System.out.println(lijek.getId() + "backend");
		}
		return l;
	}
	
	@RequestMapping(value = "/unosLijeka", method = RequestMethod.POST)
	public void unosLijeka(@RequestBody Lijekovi l)
	{
		System.out.println(l.getNaziv() + l.getStandardnaDoza());
		
		//l.setDijagnozeLijekovis(null);
		
		lr.save(l);
	}

}
