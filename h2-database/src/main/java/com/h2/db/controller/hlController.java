package com.h2.db.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hlController {

    @RequestMapping("/admin")
    public String helloAdmin()
    {
        return "hello admin";
    }

}
