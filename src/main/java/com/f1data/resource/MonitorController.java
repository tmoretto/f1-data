package com.f1data.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitorController {
	
    @RequestMapping("/hello2me")
    public String sayHello(){
        return "Hey";
    }

}
