package az.inci.bmsreport.controller;

import az.inci.bmsreport.model.*;
import az.inci.bmsreport.service.*;
import az.inci.bmsreport.service.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportPageController
{
    private final UserService userService;
    private final SbeService sbeService;
    private final WhsService whsService;
    private final PriceService priceService;
    private final MonthService monthService;

    @RequestMapping("/sbe-stock-report/report")
    public String sbeStockReportPage(Model model)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails)
            username = ((UserDetails) principal).getUsername();
        else
            username = principal.toString();

        User user = userService.getById(username);

        List<Sbe> sbeList;
        if(user.isAdmin())
            sbeList = sbeService.getSbeList();
        else
            sbeList = sbeService.getSbeListForUser(username);

        List<Whs> whsList;
        if(user.isAdmin())
            whsList = whsService.getWhsList();
        else
            whsList = whsService.getWhsListForUser(username);

        List<Price> priceList;
        if(user.isAdmin())
            priceList = priceService.getPriceList();
        else
            priceList = priceService.getPriceListForUser(username);

        model.addAttribute("username", username);
        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("sbe_list", sbeList);
        model.addAttribute("whs_list", whsList);
        model.addAttribute("price_list", priceList);

        return "reports/sbe-stock-report/report";
    }

    @RequestMapping("/comparative/report")
    public String comparativeReportPage(Model model)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails)
            username = ((UserDetails) principal).getUsername();
        else
            username = principal.toString();

        User user = userService.getById(username);
        boolean isSuperVisor = user.isSuperVisor();

        model.addAttribute("isSuperVisor", isSuperVisor);
        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("months", monthService.months());
        return "reports/comparative/report";
    }
}
