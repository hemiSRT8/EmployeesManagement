package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectEmployeesDao;
import ua.av.entities.Employee;
import ua.av.utils.SearchEmployee;

import java.util.List;

import static java.lang.Long.*;

@Controller
public class InfoAboutEmployeeController {

    @Autowired
    private SelectEmployeesDao selectEmployeesDao = new SelectEmployeesDao();

    @RequestMapping(value = "/infoAboutEmployee.html")
    public ModelAndView infoAboutEmployee(WebRequest request) {
        Long id = valueOf(request.getParameter("infoActionId"));
        List<Employee> employees = selectEmployeesDao.selectAllEmployees();
        Employee employee = SearchEmployee.searchById(id, employees);

        return new ModelAndView("infoAboutEmployee", "employee", employee);
    }
}
