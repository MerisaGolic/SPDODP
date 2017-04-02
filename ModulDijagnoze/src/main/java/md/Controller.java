package md;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class Controller {
	
	@Autowired
	private DijagnozeRepository dr;
	
	@Autowired
	private SimptomiRepository sr;
	
	@Autowired
	private DijagnozeSimptomiRepository dsr;
	
	@RequestMapping(value = "/dijagnosticiranje", method = RequestMethod.GET)
    public List<Dijagnoze> m1(@RequestParam String simptomi) 
	{	
		String delims = "[,]";
		String[] tokens = simptomi.split(delims);
		ArrayList<String> listaSimptoma = new ArrayList<String>(Arrays.asList(tokens));
		
		List<Dijagnoze> listaDijagnoza = dr.nadjiDijagnozePoSimptomima(listaSimptoma); 
		
        return listaDijagnoza;
    }
	
	@RequestMapping(value = "/dodavanjeDijagnoze", method = RequestMethod.GET)
    public String m2(@RequestParam (value="dijagnoza", defaultValue="") String dijagnoza, 
    		         @RequestParam (value="opis", defaultValue="") String opis) 
	{
		Dijagnoze d = new Dijagnoze();
		d.setNaziv(dijagnoza);
		d.setOpis(opis);
		dr.save(d);
		
		return "uredu";
	}
	
	@RequestMapping(value = "/novaDijagnoza", method = RequestMethod.GET)
    public String m3(@RequestParam (value="simptomi", defaultValue="") String simptomi, 
    		         @RequestParam (value="dijagnoza", defaultValue="") String dijagnoza) 
	{
		String delims = "[,]";
		String[] tokens = simptomi.split(delims);
		ArrayList<String> listaSimptoma = new ArrayList<String>(Arrays.asList(tokens));	
		
		Integer id1 = dr.vratiIdPremaNazivu(dijagnoza);
		Dijagnoze nova = dr.findOne(id1);
			
		for(String s : listaSimptoma)
		{
			Integer id2 = sr.vratiIdPremaNazivu(s);
			Simptomi smp = sr.findOne(id2);
						
			DijagnozeSimptomi ds = new DijagnozeSimptomi();
			ds.setDijagnoze(nova);
			ds.setSimptomi(smp);
			ds.setId(id1, id2);
			dsr.save(ds);
			
			nova.addDijagnozeSimptomi(ds);
			smp.addDijagnozeSimptomi(ds);
		}
	
		return "uspjesno dodana nova dijagnoza";
    }
	
	

}
