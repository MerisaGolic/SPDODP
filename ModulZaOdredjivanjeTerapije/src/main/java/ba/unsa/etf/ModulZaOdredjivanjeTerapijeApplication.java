package ba.unsa.etf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.netflix.feign.EnableFeignClients;



@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ba.unsa.etf"})
public class ModulZaOdredjivanjeTerapijeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ModulZaOdredjivanjeTerapijeApplication.class, args);
	}
	
}


@RefreshScope
@RestController
class MessageRestController {

    @Value("${message:Hello default}")
    private String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
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

@RestController
@EnableFeignClients(basePackages = {"ba.unsa.etf"})
class KomunikacijaController {
	@Autowired KorisniciClient kc;
	
	@RequestMapping("/get-pozdrav")
    public String pozdrav(Model model) {
        return "pozdrav";
    }
	
	@RequestMapping(value= "/get-provjeri-pacijenta-test")
	public Boolean provjeriPacijentaTest()
	{
		return kc.provjeriPacijentaTest();
	}
	
	
}


