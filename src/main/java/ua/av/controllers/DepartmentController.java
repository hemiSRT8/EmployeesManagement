package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.*;
import ua.av.utils.DepartmentService;

import java.util.*;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private SelectSalaryInformationDao selectSalaryInformationDao;
    @Autowired
    private DepartmentService departmentService;

    /**
     * Create department
     */

    @RequestMapping(value = "/addDepartment.html")
    public ModelAndView addDepartmentResult(WebRequest request) {
        String departmentName = request.getParameter("departmentName");

        boolean result = departmentDao.addDepartment(departmentName);

        return new ModelAndView("addDepartment", "result", result);
    }

    /**
     * Read department
     */

    @RequestMapping(value = "/viewAllDepartments.html")
    public ModelAndView viewAllDepartments(WebRequest request) {
        ModelMap modelmap = new ModelMap();

        Map<String, List<Long>> departmentsWithEmployees = departmentDao.selectEmployeeDepartment();
        Map<String, Double> salaryExpense = selectSalaryInformationDao.selectSalaryExpenseForDepartment(departmentsWithEmployees);

        String sortType = request.getParameter("sortType");

        if (sortType == null) { //default departments page , without sorting
            modelmap.addAttribute("departmentsMap",
                    departmentsWithEmployees);
        } else if ("amountOfEmployees".equals(sortType)) { //sort by amount of employees
            @SuppressWarnings("unchecked")
            TreeMap<String, List<Long>> treeMap =
                    new TreeMap<String, List<Long>>(departmentService.amountOfEmployeesComparator(departmentsWithEmployees));

            treeMap.putAll(departmentsWithEmployees);
            modelmap.addAttribute("departmentsMap",
                    treeMap);
        } else if ("salaryExpense".equals(sortType)) { //sort by salary expense
            @SuppressWarnings("unchecked")
            TreeMap<String, List<Long>> treeMap =
                    new TreeMap<String, List<Long>>(departmentService.salaryExpenseComparator(salaryExpense));

            treeMap.putAll(departmentsWithEmployees);
            modelmap.addAttribute("departmentsMap",
                    treeMap);
        }

//regardless of the conditions above
        modelmap.addAttribute("departmentsNamesOnly",
                departmentDao.selectDepartmentsFromDatabase()); //only department's names
        modelmap.addAttribute("departmentSalaryExpense", salaryExpense);

        return new ModelAndView("viewAllDepartments", modelmap);
    }


    @RequestMapping(value = "infoAboutDepartmentEmployees.html")
    public ModelAndView infoAboutDepartmentEmployeesController(WebRequest request) {
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("departmentName", request.getParameter("departmentName"));
        modelMap.addAttribute("departmentEmployees", departmentDao.selectDepartmentEmployeesList(request.getParameter("employeesIds")));

        return new ModelAndView("infoAboutDepartmentEmployees", modelMap);
    }


    /**
     * Update department
     */

    @RequestMapping(value = "/editDepartment.html")
    public ModelAndView editEmployee(WebRequest request) {
        String oldDepartmentName = request.getParameter("oldDepartmentName");
        String newDepartmentName = request.getParameter("newDepartmentName");

        boolean result = departmentDao.editDepartment(oldDepartmentName, newDepartmentName);

        return new ModelAndView("editDepartment", "result", result);
    }

    /**
     * Delete department
     */

    @RequestMapping(value = "/deleteDepartment.html")
    public ModelAndView deleteDepartment(WebRequest request) {
        String departmentName = request.getParameter("departmentName");

        boolean result = departmentDao.deleteDepartment(departmentName);

        return new ModelAndView("deleteDepartmentResult", "result", result);
    }

    @RequestMapping(value = "/deleteEmployeeFromDepartment.html")
    public String deleteEmployeeFromDepartment(WebRequest request) {
        Long id = Long.valueOf(request.getParameter("employeeId"));
        String departmentName = request.getParameter("departmentName");

        departmentDao.deleteEmployeeFromDepartment(id, departmentName);

        return "redirect:viewAllDepartments.html";
    }
}
