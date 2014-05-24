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
        map.addAttribute("expense", selectSalaryInformationDao.selectSalaryExpense());
        map.addAttribute("averageSalary", selectSalaryInformationDao.selectAverageSalary());
        map.addAttribute("maxSalary", selectSalaryInformationDao.selectMaxSalary());
        map.addAttribute("minSalary", selectSalaryInformationDao.selectMinSalary());

        return new ModelAndView("salaryInformation", map);
    }
}
