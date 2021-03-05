package pl.md.cwiczenie;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User("Adam", "Kowalski", 20));
        users.add(new User("Joanna", "Marciniak", 26));
        users.add(new User("Tomasz", "Zalewski", 55));
    }

    public List<User> getAll() {
        return users;
    }
}
