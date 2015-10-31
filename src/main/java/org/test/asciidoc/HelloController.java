package org.test.asciidoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class HelloController {

    @Autowired
    private TestService testService;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello man this is a spring controller on Heroku!!" + testService.doSomething() + new Date();
    }

}
