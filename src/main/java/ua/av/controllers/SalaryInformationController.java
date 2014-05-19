package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectSalaryInformation;

@Controller
public class SalaryInformationController {

    @RequestMapping(value = "/salaryInformation.html")
    public ModelAndView salaryInformation() {
        ModelMap map = new ModelMap();

        double expense = SelectSalaryInformation.selectSalaryExpense();
        double averageSalary = SelectSalaryInformation.selectAverageSalary();
        double maxSalary = SelectSalaryInformation.selectMaxSalary();
        double minSalary = SelectSalaryInformation.selectMinSalary();

        map.addAttribute("expense", expense);
        map.addAttribute("averageSalary", averageSalary);
        map.addAttribute("maxSalary", maxSalary);
        map.addAttribute("minSalary", minSalary);

        return new ModelAndView("salaryInformation", map);
    }
}
