package az.inci.zreport.service.security;

import az.inci.zreport.model.User;
import az.inci.zreport.service.AbstractService;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractService
{

    public User getById(String userId)
    {
        User user = new User();
        Query query = entityManager.createNativeQuery("""
                SELECT BU.USER_ID,
                    BU.PASS_WORD,
                    BU.USER_NAME,
                    BU.ADMIN_FLAG,
                    CAST(IIF(SS.USER_ID IS NULL, 0, 1) AS BIT) AS SUPERVISOR_FLAG
                FROM BMS_USER BU
                LEFT JOIN SUPERVISOR_SBE SS ON BU.USER_ID = SS.USER_ID
                WHERE BU.USER_ID = ?""");
        query.setParameter(1, userId);

        List<Object[]> resultList = query.getResultList();

        if (resultList.size() > 0)
        {
            user.setUserId(String.valueOf(resultList.get(0)[0]));
            user.setPassword(String.valueOf(resultList.get(0)[1]));
            user.setUserName(String.valueOf(resultList.get(0)[2]));
            user.setAdmin(Boolean.parseBoolean(String.valueOf(resultList.get(0)[3])));
            user.setSuperVisor(Boolean.parseBoolean(String.valueOf(resultList.get(0)[4])));
        }

        return user;
    }
}
