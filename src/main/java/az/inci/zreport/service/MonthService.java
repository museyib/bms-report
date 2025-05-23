package az.inci.zreport.service;

import az.inci.zreport.model.Month;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonthService extends AbstractService
{
    public List<Month> months()
    {
        List<Month> months = new ArrayList<>();
        LocalDate date = LocalDate.now();

        for (int n = 0; n < date.getMonthValue(); n++)
        {
            months.add(Month.getById(n));
        }

        return months;
    }
}
