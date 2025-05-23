package az.inci.zreport.service;

import az.inci.zreport.model.Price;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceService extends AbstractService
{
    public List<Price> getPriceList()
    {
        List<Price> priceList = new ArrayList<>();
        Query query = entityManager.createNativeQuery("""
        SELECT PRICE_CODE, PRICE_NAME FROM PRICE_MASTER""");
        List<Object[]> resultList = query.getResultList();

        for (Object[] result : resultList)
        {
            Price price = new Price();
            price.setPriceCode(String.valueOf(result[0]));
            price.setPriceName(String.valueOf(result[1]));

            priceList.add(price);
        }

        return priceList;
    }

    public List<Price> getPriceListForUser(String userId)
    {
        List<Price> priceList = new ArrayList<>();
        Query query = entityManager.createNativeQuery("""
                        SELECT PM.PRICE_CODE,
                            PM.PRICE_NAME
                        FROM PRICE_MASTER PM
                        JOIN BMS_USER_PRICE BUP ON PM.PRICE_CODE = BUP.PRICE_CODE
                        WHERE BUP.USER_ID = :USER_ID""");
        query.setParameter("USER_ID", userId);

        List<Object[]> resultList = query.getResultList();

        for (Object[] result : resultList)
        {
            Price price = new Price();
            price.setPriceCode(String.valueOf(result[0]));
            price.setPriceName(String.valueOf(result[1]));

            priceList.add(price);
        }

        return priceList;
    }
}
