package az.inci.bmsreport.service.security;

import az.inci.bmsreport.model.User;
import az.inci.bmsreport.service.AbstractService;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class UserService extends AbstractService {
    public User getById(String userId)
    {
        User user = new User();
        Query query = entityManager.createNativeQuery("SELECT USER_ID, PASS_WORD, USER_NAME, ADMIN_FLAG FROM BMS_USER WHERE USER_ID = ?");
        query.setParameter(1, userId);

        List<Object[]> resultList = query.getResultList();

        if (resultList.size() > 0)
        {
            user.setUserId(String.valueOf(resultList.get(0)[0]));
            user.setPassword(String.valueOf(resultList.get(0)[1]));
            user.setUserName(String.valueOf(resultList.get(0)[2]));
            user.setAdmin(String.valueOf(resultList.get(0)[3]).equals("1"));
        }

        return user;
    }
}
