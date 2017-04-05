package model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = {"model"})
public class DijagnozePacijentiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DijagnozePacijentiApplication.class, args);
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
@EnableFeignClients(basePackages = {"ba.unsa.etf", "md", "model"})
class KomunikacijaController {
	@Autowired DijagnozePacijentiFeign dpf;
	
	@RequestMapping("/get-pozdrav")
    public String pozdrav(Model model) {
        return "pozdrav";
    }
	
	@RequestMapping(value= "/get-provjeri-pacijenta-test")
	public Boolean provjeriPacijentaTest()
	{
		return dpf.provjeriPacijentaTest();
	}
	
	
}