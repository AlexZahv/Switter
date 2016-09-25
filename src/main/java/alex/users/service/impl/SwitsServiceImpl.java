package alex.users.service.impl;


import alex.dao.SwitterDao;
import alex.users.Swit;
import alex.users.service.SwitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwitsServiceImpl implements SwitService {
    @Autowired
    private SwitterDao switterDao;

    @Override
    public List<Swit> getLastSwits(int count) {
        if (count == 0)
            return switterDao.getAllSwits();
        else return switterDao.getLastSwits(count);
    }

    @Override
    public List<Swit> getSwitsPerUser(String login, int count) {
        List<Swit> switsList = switterDao.getAllSwitsForUser(login);
        if (count != 0)
            return switsList.subList(0, count - 1);
        else
            return switsList;
    }

    @Override
    public void saveSwit(Swit swit) {
        switterDao.saveSwit(swit);
    }

    public SwitterDao getSwitterDao() {
        return switterDao;
    }

    public void setSwitterDao(SwitterDao switterDao) {
        this.switterDao = switterDao;
    }
}
