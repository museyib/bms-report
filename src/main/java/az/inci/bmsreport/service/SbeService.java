package az.inci.bmsreport.service;

import az.inci.bmsreport.model.Sbe;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class SbeService extends AbstractService {
    public List<Sbe> getSbeList()
    {
        List<Sbe> sbeList = new ArrayList<>();

        Query query = entityManager
                .createNativeQuery("SELECT SBE_CODE_PARENT, SBE_CODE, SBE_NAME " +
                        "FROM DBO.FN_GET_SBE_HIERARCHY('') WHERE INACTIVE_FLAG = 0 " +
                        "ORDER BY GROUP_CODE");


        List<Object[]> resultList = query.getResultList();

        for (Object[] result : resultList)
        {
            Sbe sbe = new Sbe();
            sbe.setParentSbeCode(String.valueOf(result[0]));
            sbe.setSbeCode(String.valueOf(result[1]));
            sbe.setSbeName(String.valueOf(result[2]));

            sbeList.add(sbe);
        }

        return sbeList;
    }
}
