package ba.unsa.etf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@RequestMapping("/poveziDijagnozeILijekove")
	public void povezi(@RequestParam(value="idLijeka", defaultValue="World") int idLijeka, @RequestParam(value="idDijagnoze", defaultValue="World") int idDijagnoze)
	{
		Lijekovi l = lr.findOne(idLijeka);
		Dijagnoze d = dr.findOne(idDijagnoze);
		DijagnozeLijekovi dl = new DijagnozeLijekovi();
		dl.setDijagnoze(d);
		dl.setLijekovi(l);
		dl.setId(idLijeka, idDijagnoze);
		dlr.save(dl);
		l.addDijagnozeLijekovi(dl);
		d.addDijagnozeLijekovi(dl);
	}
	
	
	@RequestMapping("/izracunajDozu")
	public double izracunaj(@RequestParam(value="idLijeka", defaultValue="World") int idLijeka, @RequestParam(value="idDijagnoze", defaultValue="World") int idDijagnoze, 
			@RequestParam(value="secer", defaultValue="World") double secer, @RequestParam(value="eritrociti", defaultValue="World") double eritrociti,
			@RequestParam(value="leukociti", defaultValue="World") double leukociti, @RequestParam(value="trombociti", defaultValue="World") double trombociti)
	{
		String komb;
		komb = Integer.toString(idLijeka) + Integer.toString(idDijagnoze);
		int id = Integer.parseInt(komb);
		DijagnozeLijekovi dl = dlr.findOne(id);
		dl.setSecer(secer);
		dl.setEritrociti(eritrociti);
		dl.setLeukociti(leukociti);
		dl.setTrombociti(trombociti);
		dlr.save(dl);
		
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
		
		return doza;
	}
	
	
	@RequestMapping(value = "/prijemDijagnoze1", method = RequestMethod.POST)
	 @ResponseBody
	 public String prijemDijagnoze1(@RequestBody Dijagnoze req, @RequestHeader(value="Cookie") String token)
	 {
		 //ovdje insert
		 Dijagnoze d = new Dijagnoze();
		 d = dr.save(req);
		 
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
		}
		return l;
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/unosLijeka")
	@ResponseBody
	public Lijekovi unosLijeka (@RequestBody Lijekovi req){
		Lijekovi l = new Lijekovi();
		l = lr.save(req);
		return l;
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/dajNaziveLijekova")
	@ResponseBody
	public List<String> dajNaziveLijekova (@RequestParam(value="ideviDijagnoza") String ideviDijagnoza){
		final Pattern pattern = Pattern.compile("\\d+"); // the regex
		final Matcher matcher = pattern.matcher(ideviDijagnoza); // your string

		final ArrayList<Integer> ints = new ArrayList<Integer>(); // results

		while (matcher.find()) { // for each match
		    ints.add(Integer.parseInt(matcher.group())); // convert to int
		}
		List<String> l = new ArrayList<String>();
		for(int i = 0; i < ints.size(); i++)
		{
			l.add(lr.vratiNazivLijeka(ints.get(i)));
		}
		return l;
	}
	

}
