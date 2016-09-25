package alex.users.service.impl;

import alex.dao.SwitterDao;
import alex.users.Account;
import alex.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Set;


@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private SwitterDao switterDao;
    @Override
    public List<Account> getAllUsers() {
        return switterDao.getAllUsers();
    }
    @Override
    public boolean registerUser(Account account) {
        if (checkUser(account.getLogin()))
            return false;
        else {
            Random random = new Random();
            account.setPhotoPath("avatar" + (random.nextInt(14) + 1) + ".png");
            switterDao.saveUser(account);
        }
        return true;
    }
    @Override
    public boolean authentificateUser(String name, String password) {
        return (checkUser(name, password));
    }

    private boolean checkUser(String name) {
        Account databaseAccount = switterDao.getUser(name);
        return (databaseAccount != null);
    }

    private boolean checkUser(String name, String password) {
        Account databaseAccount = switterDao.getUser(name, password);
        return (databaseAccount != null);
    }
    @Override
    public Account getUser(String login, String password) {
        return switterDao.getUser(login, password);
    }
    @Override
    public Account getUser(String login) {
        return switterDao.getUser(login);
    }
    @Override
    public void addNewFriend(String friendLogin, String userLogin, String userPassword) {
        Account friendAccount;
        Account userAccount;
        if (((friendAccount = getUser(friendLogin)) != null) && ((userAccount = getUser(userLogin, userPassword)) != null)) {
           Set<Account> friendsSet=userAccount.getFollowers();
            if (!friendsSet.contains(friendAccount))
                friendsSet.add(friendAccount);
            userAccount.setFollowers(friendsSet);
            switterDao.updateUser(userAccount);
        }
    }
    @Override
    public Set<Account> getFriendsList(String login) {
        return null;
    }

    @Override
    public void updateUser(Account account){
        switterDao.updateUser(account);
    }
}
