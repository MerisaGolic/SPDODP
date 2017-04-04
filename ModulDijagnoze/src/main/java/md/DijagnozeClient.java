package md;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("modulDijagnozePacijenti")

public interface DijagnozeClient {
	
	@RequestMapping("/provjeriDijagnozuTest")
	 	public Boolean provjeriDijagnozuTest();

}
