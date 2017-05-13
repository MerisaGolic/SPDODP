package model;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("modul-za-korisnike")
public interface DijagnozePacijentiFeign {
	
	@RequestMapping("/provjeriPacijentaTest")
	public Boolean provjeriPacijentaTest();
}
