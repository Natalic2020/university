package ua.com.foxminded.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
    
    Logger logger = LoggerFactory.getLogger("SampleLogger");
    
    @RequestMapping("/helloWorld")
    public String helloWorld(Model model) {
        logger.info("--------------------- I am in HelloWorldController                   helloWorld");
        
        model.addAttribute("message", "Hello World!");
        return "helloWorld";
    }
}
