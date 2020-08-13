package com.basic.springboot;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HelloWorldController {
    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        
        String message="You just create Spring Boot Example successfully";
        model.addAttribute("name", name);
        model.addAttribute("message", message);
        
        return "hello";
    }
}
