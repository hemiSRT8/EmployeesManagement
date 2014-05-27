package ua.av.controllers;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.AddEmployeesToDepartmentDao;
import ua.av.database.SelectDepartmentsDao;
import ua.av.database.SelectEmployeesDao;
import ua.av.entities.Department;
import ua.av.entities.Employee;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<Employee> employees = selectEmployeesDao.selectAllEmployees();

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("employees", employees);
        modelMap.addAttribute("departments", departments);

        return new ModelAndView("addEmployeesToDepartment", modelMap);
    }

    @RequestMapping(value = "/addEmployeesToDepartmentResult.html")
    public ModelAndView addEmployeesToDepartmentResult(WebRequest request) {
        String[] stringIdsArray = request.getParameterValues("employeeId");
        String[] departmentsArray = request.getParameterValues("department");
        boolean isSuccess = false;

        if (ArrayUtils.isEmpty(stringIdsArray) || ArrayUtils.contains(stringIdsArray, null)) {
            LOGGER.error("employees ids are not populated");
        } else if (ArrayUtils.isEmpty(departmentsArray)  || ArrayUtils.contains(departmentsArray, null)) {
            LOGGER.error("departments ids are not populated");
        } else {
            isSuccess = addEmployeesToDepartmentDao.addEmployeesToDepartment(new ArrayList<String>(Arrays.asList(stringIdsArray)),
                    new ArrayList<String>(Arrays.asList(departmentsArray)));
        }


        return new ModelAndView("addEmployeesToDepartmentResult", "result", isSuccess);
    }
}
