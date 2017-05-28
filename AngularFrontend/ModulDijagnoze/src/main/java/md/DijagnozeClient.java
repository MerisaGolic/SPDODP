package md;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import md.models.Dijagnoze;

@FeignClient("modul-dijagnoze-pacijenti")

public interface DijagnozeClient {
	
	@RequestMapping("/provjeriDijagnozuTest")
	public Boolean provjeriDijagnozuTest();
	
	@RequestMapping(value = "/provjeriDijagnozuTestParam", method = RequestMethod.GET)
	@ResponseBody
	public Boolean provjeriDijagnozuTestParam(@RequestParam(value="id")Integer id);
	
	@RequestMapping(value = "/prijemDijagnoze1", method = RequestMethod.POST)
	@ResponseBody
	public String proslijediDijagnozu1(@RequestBody Dijagnoze req, @RequestHeader(value="Cookie") String cookie);
	
}
