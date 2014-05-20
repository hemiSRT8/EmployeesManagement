package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.DeleteDepartmentDao;

@Controller
public class DeleteDepartmentController {

    @Autowired
    private DeleteDepartmentDao deleteDepartmentDao;

    @RequestMapping(value = "/deleteDepartment.html")
    public ModelAndView deleteDepartment(WebRequest request) {
        String departmentName = request.getParameter("departmentName");
        boolean result = deleteDepartmentDao.deleteDepartment(departmentName);

        return new ModelAndView("deleteDepartmentResult", "result", result);
    }
}
