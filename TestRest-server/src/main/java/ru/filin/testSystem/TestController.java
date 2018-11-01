package ru.filin.testSystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("ind")
    public static String ind(){
        return "index";
    }
}
