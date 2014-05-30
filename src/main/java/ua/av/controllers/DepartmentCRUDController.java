package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.*;

import java.util.List;
import java.util.Map;

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
    public ModelAndView viewAllDepartments() {
        ModelMap modelmap = new ModelMap();

        Map<String, List<Long>> departmentsWithEmployees = departmentCRUDDao.selectEmployeeDepartment();

        modelmap.addAttribute("departmentsMap",
                departmentsWithEmployees); //departments with employees
        modelmap.addAttribute("departmentsNamesOnly",
                departmentCRUDDao.selectDepartmentsFromDatabase()); //only department's names
        modelmap.addAttribute("departmentSalaryExpense",
                selectSalaryInformationDao.selectSalaryExpenseForDepartment(departmentsWithEmployees));

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
