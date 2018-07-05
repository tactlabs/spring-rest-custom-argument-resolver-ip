package org.tact.base.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class IpAddressArgumentResolver implements HandlerMethodArgumentResolver {
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.getParameterAnnotation(IpAddressArgument.class) != null && Long.class == parameter.getParameterType())
			return true;
		else 
			return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		String ipAddr = webRequest.getParameter("ip");
		if ( ipAddr == null || ipAddr.isEmpty() ){
			throw new Exception("IP Parameter Missing");
		}
		
		return ipToLong(ipAddr);
	}
	
	public static long ipToLong(String ipAddr) throws Exception {
		//fix for localhost and ipv6
		if("0:0:0:0:0:0:0:1".equals(ipAddr))
			ipAddr = "127.0.0.1";
        String[] ipAddrArray = ipAddr.split("\\."); 
        if (ipAddrArray.length != 4)
        	throw new Exception("Invalid IP");

        long num = 0; 
        for (int i = 0; i < ipAddrArray.length; i++) { 
            int power = 3 - i; 
            num += ((Integer.parseInt(ipAddrArray[i]) % 256 * Math.pow(256, power))); 
        } 
        return num; 
    }
}

