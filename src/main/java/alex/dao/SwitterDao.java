package alex.dao;

import alex.users.Account;
import alex.users.Swit;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface SwitterDao {

    @Transactional
    public void saveUser(Account account);

    @Transactional
    public void updateUser(Account account);

    @Transactional
    public void deleteUser(String login);

    @Transactional
    public Account getUser(String login);

    @Transactional
    public Account getUser(String login, String password);

    @Transactional
    public List<Account> getAllUsers();

    @Transactional
    public void saveSwit(Swit swit);

    @Transactional
    public void deleteSwit(String title, String userLogin);

    @Transactional
    public Swit getSwit(String title, String userLogin);

    @Transactional
    public List<Swit> getAllSwits();

    @Transactional
    public List<Swit> getAllSwitsForUser(String userLogin);

    @Transactional
    public List<Swit> getLastSwits(int count);
}
