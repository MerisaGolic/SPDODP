package md;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import md.models.Dijagnoze;
import md.models.DijagnozeSimptomi;
import md.models.Simptomi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.ui.Model;

@RestController
@EnableFeignClients(basePackages = {"ba.unsa.etf", "md", "model"})
public class Controller {
	
	@Autowired
	private DijagnozeRepository dr;
	
	@Autowired
	private SimptomiRepository sr;
	
	@Autowired
	private DijagnozeSimptomiRepository dsr;
	
	@Autowired 
	DijagnozeClient dc;
	
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
		String poruka = dc.proslijediDijagnozu1(dijagnoza, opis);
		
		
		return poruka;
		//return "uredu";
	}
	
	@RequestMapping(value = "/novaDijagnoza", method = RequestMethod.GET)
	@ResponseBody
    public String m3(@RequestParam (value="simptomi", defaultValue="") String[] simptomi, 
    		         @RequestParam (value="dijagnoza", defaultValue="") String dijagnoza) 
	{
		//String delims = "[,]";
		//String[] tokens = simptomi.split(delims);
		ArrayList<String> listaSimptoma = new ArrayList<String>(Arrays.asList(simptomi));	
		
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
	
	@RequestMapping("/get-pozdrav")
    public String pozdrav(Model model) {
        return "pozdrav";
    }
	
	@RequestMapping(value= "/get-provjeri-dijagnozu-test")
	public Boolean provjeriDijagnozuTest()
	{
		return dc.provjeriDijagnozuTest();
	}
	
	@RequestMapping(value= "/get-provjeri-dijagnozu-test-param", method = RequestMethod.GET)
	@ResponseBody
	public Boolean provjeriDijagnozuTestParam(@RequestParam("id") Integer id)
	{
		return dc.provjeriDijagnozuTestParam(id);
	}

}
