package hu.iit.uni.miskolc.advanced.java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/hello")
    public String greetings(@RequestParam(defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}
