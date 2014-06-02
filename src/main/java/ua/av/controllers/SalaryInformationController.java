package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectSalaryInformationDao;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SalaryInformationController {

    @Autowired
    private SelectSalaryInformationDao selectSalaryInformationDao;

    @RequestMapping(value = "/salaryInformation.html")
    public ModelAndView salaryInformation() {
        ModelMap map = new ModelMap();

        Map<String, Double> salaryInfo = new HashMap<String, Double>();

        salaryInfo.put("expense", selectSalaryInformationDao.selectSalaryExpenseForEmployees());
        salaryInfo.put("averageSalary", selectSalaryInformationDao.selectAverageSalaryOfEmployees());
        salaryInfo.put("maxSalary", selectSalaryInformationDao.selectMaxSalaryOfEmployees());
        salaryInfo.put("minSalary", selectSalaryInformationDao.selectMinSalaryOfEmployees());

        map.addAttribute("salaryInfo" , salaryInfo);

        return new ModelAndView("salaryInformation", map);
    }
}
