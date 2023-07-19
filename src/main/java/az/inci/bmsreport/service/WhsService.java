package az.inci.bmsreport.service;

import az.inci.bmsreport.model.Whs;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WhsService extends AbstractService
{
    public List<Whs> getWhsList()
    {
        List<Whs> whsList = new ArrayList<>();

        Query query = entityManager
                .createNativeQuery("""
        SELECT WHS_CODE, WHS_NAME FROM WHS_MASTER ORDER BY WHS_CODE""");


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

    public List<Whs> getWhsListForUser(String userId)
    {
        List<Whs> whsList = new ArrayList<>();

        Query query = entityManager.createNativeQuery("""
                SELECT WM.WHS_CODE,
                    WM.WHS_NAME
                FROM WHS_MASTER WM
                JOIN SBE_MASTER SM ON WM.WHS_CODE = SM.WHS_CODE
                JOIN BMS_USER_SBE BUS ON SM.SBE_CODE = BUS.SBE_CODE
                WHERE BUS.USER_ID = :USER_ID AND BUS.DEFAULT_FLAG = 1
                ORDER BY WHS_CODE""");
        query.setParameter("USER_ID", userId);

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
