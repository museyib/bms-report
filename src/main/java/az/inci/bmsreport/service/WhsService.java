package az.inci.bmsreport.service;

import az.inci.bmsreport.model.Whs;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class WhsService extends AbstractService {
    public List<Whs> getWhsList()
    {
        List<Whs> whsList = new ArrayList<>();

        Query query = entityManager
                .createNativeQuery("SELECT WHS_CODE, WHS_NAME FROM WHS_MASTER ORDER BY WHS_CODE");


        List<Object[]> resultList = query.getResultList();

        for (Object[] result : resultList)
        {
            Whs whs = new Whs();
            whs.setWhsCode(String.valueOf(result[0]));
            whs.setWhsName(String.valueOf(result[1]));

            whsList.add(whs);
        }

        return whsList;
    }
}
