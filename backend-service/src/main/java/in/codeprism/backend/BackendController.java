package in.codeprism.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BackendController {

    @GetMapping("/admin")
    public String admin() {
    	System.out.println("admin entered");
        return "Hello Admin!";
    }

    @GetMapping("/user")
    public String user() {
    	System.out.println("user entered");
        return "Hello User!";
    }

    @GetMapping("/guest")
    public String guest() {
    	System.out.println("quest entered");
        return "Hello Guest!";
    }
}

