package ua.av.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.AddEmployeesToDepartmentDao;
import ua.av.database.SelectDepartmentsDao;
import ua.av.database.SelectEmployeesDao;
import ua.av.entities.Department;
import ua.av.entities.Employee;

import java.util.List;

@Controller
public class AddEmployeesToDepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddEmployeesToDepartmentController.class);

    @Autowired
    private SelectEmployeesDao selectEmployeesDao;
    @Autowired
    private AddEmployeesToDepartmentDao addEmployeesToDepartmentDao;
    @Autowired
    private SelectDepartmentsDao selectDepartmentsDao;

    @RequestMapping(value = "/addEmployeesToDepartment.html")
    public ModelAndView addEmployeesToDepartment() {
        List<Department> departments = selectDepartmentsDao.selectDepartmentsFromDatabase();
        List<Employee> managers = selectEmployeesDao.selectManagers();
        List<Employee> developers = selectEmployeesDao.selectDevelopers();
        List<Employee> cleaners = selectEmployeesDao.selectCleaners();

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("managers", managers);
        modelMap.addAttribute("developers", developers);
        modelMap.addAttribute("cleaners", cleaners);
        modelMap.addAttribute("departments", departments);

        LOGGER.info("Add employees to department page was loaded successfully");
        return new ModelAndView("addEmployeesToDepartment", modelMap);
    }

    @RequestMapping(value = "/addEmployeesToDepartmentResult.html")
    public ModelAndView addEmployeesToDepartmentResult(WebRequest request) {
        String[] stringIdsArray = request.getParameterValues("employeeId");
        String[] departmentsArray = request.getParameterValues("department");

        boolean result = addEmployeesToDepartmentDao.addEmployeesToDepartment(stringIdsArray, departmentsArray);

        if (result) {
            LOGGER.info("Employee (s) was (were) added to department successfully");
        } else {
            LOGGER.info("Employee (s) was (were) failed");
        }

        return new ModelAndView("addEmployeesToDepartmentResult", "result", result);
    }
}
