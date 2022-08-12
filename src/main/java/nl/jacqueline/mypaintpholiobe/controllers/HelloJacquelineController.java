package nl.jacqueline.mypaintpholiobe.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloJacquelineController {
    @GetMapping
    public String helloJacqueline() {
        return "Hallo Jacqueline, goed bezig vandaag!";
    }
}
