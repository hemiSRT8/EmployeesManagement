package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectSalaryInformationDao;

@Controller
public class SalaryInformationController {

    @Autowired
    private SelectSalaryInformationDao selectSalaryInformationDao;

    @RequestMapping(value = "/salaryInformation.html")
    public ModelAndView salaryInformation() {
        ModelMap map = new ModelMap();

        double expense = selectSalaryInformationDao.selectSalaryExpenseForEmployees();
        double averageSalary = selectSalaryInformationDao.selectAverageSalaryOfEmployees();
        double maxSalary = selectSalaryInformationDao.selectMaxSalaryOfEmployees();
        double minSalary = selectSalaryInformationDao.selectMinSalaryOfEmployees();

        map.addAttribute("expense", expense);
        map.addAttribute("averageSalary", averageSalary);
        map.addAttribute("maxSalary", maxSalary);
        map.addAttribute("minSalary", minSalary);

        return new ModelAndView("salaryInformation", map);
    }
}
