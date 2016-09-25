package alex.users.service;

import alex.users.Swit;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface SwitService {

    List<Swit> getLastSwits(int count);

    List<Swit> getSwitsPerUser(String login, int count);

    void saveSwit(Swit swit);
}
