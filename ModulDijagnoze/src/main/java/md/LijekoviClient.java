package md;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("modul-za-odredjivanje-terapije")

public interface LijekoviClient {
	@RequestMapping(value = "/prijemDijagnoze1", method = RequestMethod.GET)
	@ResponseBody
	public String proslijediDijagnozu1(@RequestParam(value="naziv") String naziv, @RequestParam(value="opis") String opis);

}
