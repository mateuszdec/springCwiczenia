package pl.md.cwiczenie;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User("Adam", "Kowalski", 20));
        users.add(new User("Joanna", "Marciniak", 26));
        users.add(new User("Tomasz", "Zalewski", 55));
        users.add(new User("Tomasz", "Tanowski", 45));
    }

    public List<User> getAll() {
        return users;
    }

    public void add(User user) {
        users.add(user);
    }

    public List<User> search(String imie, String nazwisko, Integer wiek) {
        List<User> resultList = new LinkedList<>();
        for (User user : users) {
            boolean isOk = checkSearchConditionsForUser(imie, nazwisko, wiek, user);
            if(isOk) {
                resultList.add(user);
            }
        }

        return resultList;
    }

    private boolean checkSearchConditionsForUser(String imie, String nazwisko, Integer wiek, User user) {
        boolean nameOk = true;
        boolean surnameOk = true;
        boolean ageOk = true;

        if(!StringUtils.isEmpty(imie) && !imie.equals(user.getName())) {
            nameOk = false;
        }

        if(!StringUtils.isEmpty(nazwisko) && !nazwisko.equals(user.getLastName())) {
            surnameOk = false;
        }

        if(wiek != null && wiek != user.getAge()) {
            ageOk = false;
        }

        return nameOk && surnameOk && ageOk;
    }

    public void delete(String imie, String nazwisko, Integer wiek) {
        List<User> usersToDelete = new LinkedList<>();
        for (User user : users) {
            boolean isOk = checkSearchConditionsForUser(imie, nazwisko, wiek, user);
            if(isOk) {
                usersToDelete.add(user);
            }
        }
        users.removeAll(usersToDelete);
    }

}
