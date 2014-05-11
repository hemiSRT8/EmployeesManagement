package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.select.SelectSalaryInformation;

@Controller
public class SalaryInformationController {

    @RequestMapping(value = "/salaryInformation.html")
    public ModelAndView salaryInformation() {
        SelectSalaryInformation selectSalaryInformation = new SelectSalaryInformation();
        ModelMap map = new ModelMap();

        double expense = selectSalaryInformation.selectSalaryExpense();
        double averageSalary = selectSalaryInformation.selectAverageSalary();
        double maxSalary = selectSalaryInformation.selectMaxSalary();
        double minSalary = selectSalaryInformation.selectMinSalary();

        map.addAttribute("expense", expense);
        map.addAttribute("averageSalary", averageSalary);
        map.addAttribute("maxSalary", maxSalary);
        map.addAttribute("minSalary", minSalary);

        return new ModelAndView("salaryInformation", map);
    }
}
