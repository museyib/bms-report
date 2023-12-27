package az.inci.bmsreport.service;

import az.inci.bmsreport.model.InvSaleStockReportData;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleStockReportService extends AbstractService
{

    public List<InvSaleStockReportData> getReportData()
    {
        List<InvSaleStockReportData> reportData = new ArrayList<>();

        Query query = entityManager.createNativeQuery("""
            SELECT IM.INV_CODE,
                IM.INV_NAME,
                IT.QTY AS SALE,
                WS.QTY AS WHS_QTY
            FROM INV_MASTER IM
            JOIN (SELECT IT.INV_CODE, -SUM(IT.QTY * IT.UOM_FACTOR * IT.DBT_CRD) AS QTY
                    FROM IVC_TRX AS IT
                    WHERE IT.TRX_DATE = CAST(GETDATE() AS DATE)
                        AND IT.TRX_TYPE_ID IN (17, 22, 27)
                    GROUP BY INV_CODE) AS IT ON IM.INV_CODE = IT.INV_CODE
            JOIN (SELECT INV_CODE, SUM(WHS_QTY) AS QTY
                    FROM WHS_SUM
                    GROUP BY INV_CODE) WS ON IM.INV_CODE = WS.INV_CODE
            WHERE IM.INV_CODE IN ('a001102', 'a001115', 'a001124', 'a001347', 'a003931', 'a001098', 'a001381', 'a001104')
            ORDER BY IM.INV_NAME, IM.INV_CODE""");

        List<Object[]> resultList = query.getResultList();

        for (Object[] result : resultList)
        {
            InvSaleStockReportData data = new InvSaleStockReportData();
            data.setInvCode(String.valueOf(result[0]));
            data.setInvName(String.valueOf(result[1]));
            data.setSaleQty(Double.parseDouble(String.valueOf(result[2])));
            data.setWhsQty(Double.parseDouble(String.valueOf(result[3])));

            reportData.add(data);
        }

        return reportData;
    }
}
