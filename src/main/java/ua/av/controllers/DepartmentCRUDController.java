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
public class DepartmentCRUDController {

    @Autowired
    private DepartmentCRUDDao departmentCRUDDao;
    @Autowired
    private SelectSalaryInformationDao selectSalaryInformationDao;

    /**
     * Create department
     */

    @RequestMapping(value = "/addDepartment.html")
    public ModelAndView addDepartmentResult(WebRequest request) {
        String departmentName = request.getParameter("departmentName");

        boolean result = departmentCRUDDao.addDepartment(departmentName);

        return new ModelAndView("addDepartment", "result", result);
    }

    /**
     * Read department
     */

    @RequestMapping(value = "/viewAllDepartments.html")
    public ModelAndView viewAllDepartments(WebRequest request) {
        ModelMap modelmap = new ModelMap();

        Map<String, List<Long>> departmentsWithEmployees = departmentCRUDDao.selectEmployeeDepartment();
        Map<String, Double> salaryExpense = selectSalaryInformationDao.selectSalaryExpenseForDepartment(departmentsWithEmployees);

        String sortType = request.getParameter("sortType");

        if (sortType == null) { //default departments page , without sorting
            modelmap.addAttribute("departmentsMap",
                    departmentsWithEmployees);
        } else if ("amountOfEmployees".compareTo(sortType) == 0) { //sort by amount of employees
            DepartmentService departmentService = new DepartmentService();

            @SuppressWarnings("unchecked")
            TreeMap<String, List<Long>> treeMap =
                    new TreeMap<String, List<Long>>(departmentService.amountOfEmployeesComparator(departmentsWithEmployees));

            treeMap.putAll(departmentsWithEmployees);
            modelmap.addAttribute("departmentsMap",
                    treeMap);
        } else if ("salaryExpense".compareTo(sortType) == 0) { //sort by salary expense
            DepartmentService departmentService = new DepartmentService();

            @SuppressWarnings("unchecked")
            TreeMap<String, List<Long>> treeMap =
                    new TreeMap<String, List<Long>>(departmentService.salaryExpenseComparator(salaryExpense));

            treeMap.putAll(departmentsWithEmployees);
            modelmap.addAttribute("departmentsMap",
                    treeMap);
        }

//regardless of the conditions above
        modelmap.addAttribute("departmentsNamesOnly",
                departmentCRUDDao.selectDepartmentsFromDatabase()); //only department's names
        modelmap.addAttribute("departmentSalaryExpense", salaryExpense);

        return new ModelAndView("viewAllDepartments", modelmap);
    }

    /**
     * Update department
     */

    @RequestMapping(value = "/editDepartment.html")
    public ModelAndView editEmployee(WebRequest request) {
        String oldDepartmentName = request.getParameter("oldDepartmentName");
        String newDepartmentName = request.getParameter("newDepartmentName");

        boolean result = departmentCRUDDao.editDepartment(oldDepartmentName, newDepartmentName);

        return new ModelAndView("editDepartment", "result", result);
    }

    /**
     * Delete department
     */

    @RequestMapping(value = "/deleteDepartment.html")
    public ModelAndView deleteDepartment(WebRequest request) {
        String departmentName = request.getParameter("departmentName");

        boolean result = departmentCRUDDao.deleteDepartment(departmentName);

        return new ModelAndView("deleteDepartmentResult", "result", result);
    }
}
