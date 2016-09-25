package alex.dao.impl;

import alex.dao.HibernateUtil;
import alex.dao.SwitterDao;
import alex.users.Account;
import alex.users.Swit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PostgresqlSwitterDaoImpl implements SwitterDao {
    private HibernateUtil hibernateUtil;
    private Session session = HibernateUtil.getSession();

    private Session getCurrentSession() {
        return HibernateUtil.getSession();
    }

    @Override
    public void saveUser(Account account) {
        session.beginTransaction();
        session.saveOrUpdate(account);
        session.getTransaction().commit();
        session.close();
        session = HibernateUtil.getSession();
    }

    @Override
    public void updateUser(Account account) {
        session.beginTransaction();
        session.update(account);
        session.getTransaction().commit();
        session.close();
        session = HibernateUtil.getSession();
    }

    @Override
    public void deleteUser(String login) {

    }

    @Override
    public Account getUser(String login) {

        String hql = "FROM Account A where A.login=:login";
        Query query = session.createQuery(hql);
        query.setString("login", login);
        List list = query.list();
        if (list.isEmpty())
            return null;
        else return ((Account) list.get(0));
    }

    @Override
    public Account getUser(String login, String password) {
        String hql = "FROM Account A where A.login=:login and A.password=:password";
        Query query = session.createQuery(hql);
        query.setString("login", login);
        query.setString("password", password);
        List<Account> list = query.list();
        if (list.isEmpty())
            return null;
        else return list.get(0);
    }

    @Override
    public List<Account> getAllUsers() {
        session.beginTransaction();
        String hql = "FROM Account A ";
        Query query = session.createQuery(hql);
        List<Account> list = query.list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public void saveSwit(Swit swit) {
        session.beginTransaction();
        session.save(swit);
        session.getTransaction().commit();
        session.close();
        session = HibernateUtil.getSession();
    }

    @Override
    public void deleteSwit(String title, String userLogin) {

        session.beginTransaction();
        String hql = "delete from Swit where author.login=:userLogin ";
        Query query = session.createQuery(hql);
        query.setString("userLogin", userLogin);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public Swit getSwit(String title, String userLogin) {
        String hql = "FROM Swit  where author.login=:userLogin ";
        Query query = session.createQuery(hql);
        query.setString("userLogin", userLogin);

        List<Swit> list = query.list();
        if (list.isEmpty())
            return null;
        else return list.get(0);
    }

    @Override
    public List<Swit> getAllSwits() {
        session.beginTransaction();
        String hql = "FROM Swit order by date desc ";
        Query query = session.createQuery(hql);
        List<Swit> list = query.list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public List<Swit> getAllSwitsForUser(String userLogin) {
        Account account = getUser(userLogin);
        Collections.sort(account.getSwitList());
        return account.getSwitList();
    }

    @Override
    public List<Swit> getLastSwits(int count) {
        session.beginTransaction();
        String hql = "FROM Swit order by date desc ";
        Query query = session.createQuery(hql);
        List<Swit> list = query.setMaxResults(count).list();
        session.getTransaction().commit();
        return list;
    }
}
