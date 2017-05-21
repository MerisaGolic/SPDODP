package md;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import md.models.Dijagnoze;
import md.models.DijagnozeSimptomi;
import md.models.Simptomi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = {"md"})

@SpringBootApplication
public class ModulDijagnozeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModulDijagnozeApplication.class, args);
	}
}

@RestController
class ServiceInstanceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}
/*
@RestController
@EnableFeignClients(basePackages = {"ba.unsa.etf", "md", "model"})
class KomunikacijaController {
	@Autowired 
	DijagnozeClient dc;
	
	@Autowired
	private DijagnozeRepository dr;
	
	@Autowired
	private SimptomiRepository sr;
	
	@Autowired
	private DijagnozeSimptomiRepository dsr;
	
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
*/