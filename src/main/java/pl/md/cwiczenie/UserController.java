package pl.md.cwiczenie;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
        return convertToString(users);
    }

    private String convertToString(List<User> users) {
        String result = "";

        for (User user : users) {
            result += user.toString() + "<br/>";
        }
        return result;
    }

    @RequestMapping("/add")
    public String add(@RequestParam(defaultValue = "Anonim", required = false) String imie,
                      @RequestParam String nazwisko,
                      @RequestParam Integer wiek) {
        if(StringUtils.isEmpty(imie)) {
            return "redirect:/err.html";
        } else  {
            User user = new User(imie, nazwisko, wiek);
            userRepository.add(user);
            return "redirect:/success.html";
        }
    }

    @PostMapping("/search")
    @ResponseBody
    public String search(@RequestParam String imie,
                         @RequestParam String nazwisko,
                         @RequestParam Integer wiek) {

        List<User> users = userRepository.search(imie, nazwisko, wiek);
        return convertToString(users);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String imie,
                         @RequestParam String nazwisko,
                         @RequestParam Integer wiek) {

        userRepository.delete(imie, nazwisko, wiek);
        return "redirect:/users";
    }

}
