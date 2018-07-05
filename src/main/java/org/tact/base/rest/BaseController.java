package org.tact.base.rest;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tact.base.resolver.IpAddressArgument;

@RestController
@RequestMapping(value = "/base")
public class BaseController {
	
	/**
	 * 
	 * @return
	 * 
	 * Possible urls:
	 * 		http://localhost:1878/base/
	 */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public <T> T listUsers() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("one", "two");
        map.put("three", "four");       
        
        return (T) map;
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param ip
     * @return
     * 
     * Possible urls:
	 * 		http://localhost:1878/base/ip/resolver
     * 		http://localhost:1878/base/ip/resolver?ip=70.28.13.169
     */
    @GetMapping(value = "/ip/resolver")
	public <T> T testIP(HttpServletRequest request,
			HttpServletResponse response, @IpAddressArgument Long ip) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		resultMap.put("ok", "ok");
		resultMap.put("ip", ip);

        return (T) resultMap;
	}
}
