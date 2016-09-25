package alex.users.service;


import alex.users.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface UserService {
    public List<Account> getAllUsers();

    public boolean registerUser(Account account);

    public boolean authentificateUser(String name, String password);


    public Account getUser(String login, String password);

    public Account getUser(String login);

    public void addNewFriend(String friendLogin, String userLogin, String userPassword);

    public Set<Account> getFriendsList(String login);

    public void updateUser(Account account);

}
