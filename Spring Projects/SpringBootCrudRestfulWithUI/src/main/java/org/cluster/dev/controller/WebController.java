package org.cluster.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebController {
	
	@GetMapping(value="/")
	public String homepage()
	{
		return "index";
	}
	
}
//ref:http://javasampleapproach.com/java-integration/integrate-jquery-ajax-post-get-spring-boot-web-service
