package pl.md.cwiczenie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("/testy/test1")
    public String hello(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        System.out.println("Ktoś wszedł na stronę. Jego/ jej adres ip to " + ip);
        return "Cześć tutaj Mateusz. Wszystko działa!";
    }
}
