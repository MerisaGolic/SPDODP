package md;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@Autowired 
	LijekoviClient lc;
	
	@RequestMapping(value = "/dijagnosticiranje", method = RequestMethod.GET)
    public List<Dijagnoze> m1(@RequestParam String simptomi) 
	{	
		String delims = "[,]";
		String[] tokens = simptomi.split(delims);
		ArrayList<String> listaSimptoma = new ArrayList<String>(Arrays.asList(tokens));
		
		List<Object[]> listaDijagnoza1 = dr.nadjiDijagnozePoSimptomima(listaSimptoma); 
		List<Dijagnoze> listaDijagnoza = new ArrayList<Dijagnoze>();
		
		for (int i = 0; i<listaDijagnoza1.size(); i++)
		{
			Dijagnoze d = new Dijagnoze();
			d.setId((Integer)listaDijagnoza1.get(i)[0]);
			d.setNaziv((String)listaDijagnoza1.get(i)[1]);
			d.setOpis((String)listaDijagnoza1.get(i)[2]);
			d.setPostotak((Integer)listaDijagnoza1.get(i)[3]);
			listaDijagnoza.add(d);
		}
		
		for (int i = 0; i<listaDijagnoza.size(); i++)
		{
			
			int brojUkupnihSimptoma = dr.nadjiBrojSimptoma(listaDijagnoza.get(i).getId());

			int brojPoklapajucihSimptoma = dr.nadjiBrojPoklapajucihSimptoma(listaDijagnoza.get(i).getId(), listaSimptoma);
			
			int postotak = (int)(100.0 * (brojPoklapajucihSimptoma/(double)brojUkupnihSimptoma));

			listaDijagnoza.get(i).setPostotak(postotak);
		}
		
        return listaDijagnoza;
    }
	
	@RequestMapping(value = "/dodavanjeDijagnoze", method = RequestMethod.POST)
    public void m2(@RequestBody Dijagnoze req, @CookieValue("Authorization") String cookie) 
	{
		
		dr.save(req);
		String novi = "Authorization=" + cookie;
		String poruka = dc.proslijediDijagnozu1(req, novi);
		String poruka2 = lc.proslijediDijagnozu1(req, novi);

	}
	
	@RequestMapping(value = "/novaDijagnoza", method = RequestMethod.GET)
	@ResponseBody
    public void m3(@RequestParam (value="simptomi", defaultValue="") String[] simptomi, 
    		       @RequestParam (value="dijagnoza", defaultValue="") String dijagnoza) 
	{
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
	
	@RequestMapping(value = "/dajSimptome", method = RequestMethod.GET)
	public List<Simptomi> dajSimptome()
	{
		List<Simptomi> s = new ArrayList<Simptomi>();
		for (Simptomi simptom : sr.findAll()) 
		{
			simptom.setDijagnozeSimptomis(null);
			s.add(simptom);
		}
		return s;
	}
	
	@RequestMapping(value="/dajIdDijagnoze", method = RequestMethod.GET)
	public int dajID(@RequestParam("naziv") String naziv)
	{
		return dr.vratiIdPremaNazivu(naziv);
	}
	

}
