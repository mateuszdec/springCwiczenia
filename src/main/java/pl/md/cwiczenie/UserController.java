package pl.md.cwiczenie;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @GetMapping("/users")
    public String users() {
        List<User> users = userRepository.getAll();

        String result = "";

        for (User user : users) {
            result += user.toString() + "<br/>";
        }
        return result;
    }


    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        String imie = request.getParameter("imie");
        String nazwisko = request.getParameter("nazwisko");
        String wiek = request.getParameter("wiek");

        int age = Integer.parseInt(wiek);

        if (StringUtils.isEmpty(imie)) {
            return "redirect:/err.html";
        } else {
            User user = new User(imie, nazwisko, age);
            userRepository.add(user);
            return "redirect:/success.html";
        }
    }

}
