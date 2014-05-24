package ua.av.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectSalaryInformationDao;

@Controller
public class SalaryInformationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalaryInformationController.class);

    @Autowired
    private SelectSalaryInformationDao selectSalaryInformationDao;

    @RequestMapping(value = "/salaryInformation.html")
    public ModelAndView salaryInformation() {
        ModelMap map = new ModelMap();

        double expense = selectSalaryInformationDao.selectSalaryExpense();
        double averageSalary = selectSalaryInformationDao.selectAverageSalary();
        double maxSalary = selectSalaryInformationDao.selectMaxSalary();
        double minSalary = selectSalaryInformationDao.selectMinSalary();

        if (expense < 0 || averageSalary < 0 || maxSalary < 0 || minSalary < 0) {
            LOGGER.info("Salary is less then 0");
        }

        map.addAttribute("expense", expense);
        map.addAttribute("averageSalary", averageSalary);
        map.addAttribute("maxSalary", maxSalary);
        map.addAttribute("minSalary", minSalary);

        LOGGER.info("salary information page was loaded successfully");
        return new ModelAndView("salaryInformation", map);
    }
}
