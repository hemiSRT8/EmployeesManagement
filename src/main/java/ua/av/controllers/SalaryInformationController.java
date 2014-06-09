package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.GetSalaryInformationDao;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SalaryInformationController {

    @Autowired
    private GetSalaryInformationDao getSalaryInformationDao;

    @RequestMapping(value = "/salaryInformation.html")
    public ModelAndView salaryInformation() {
        ModelMap map = new ModelMap();

        Map<String, Double> salaryInfo = new HashMap<String, Double>();

        salaryInfo.put("minSalary", getSalaryInformationDao.getEmployeesSalaryInformation().get("minSalary"));
        salaryInfo.put("maxSalary", getSalaryInformationDao.getEmployeesSalaryInformation().get("maxSalary"));
        salaryInfo.put("averageSalary", getSalaryInformationDao.getEmployeesSalaryInformation().get("averageSalary"));
        salaryInfo.put("salaryExpense", getSalaryInformationDao.getEmployeesSalaryInformation().get("salaryExpense"));

        map.addAttribute("salaryInfo" , salaryInfo);

        return new ModelAndView("salaryInformation", map);
    }
}
