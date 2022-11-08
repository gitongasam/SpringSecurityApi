package com.devsam.springbootsecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("home")
    public String homeContrller(){
        return"welcome to home Controller";
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        return "you are seeing the dashboard contents";
    }
}
