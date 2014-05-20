package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.EditDepartment;

@Controller
public class EditDepartmentController {

    @Autowired
    private EditDepartment editDepartment;

    @RequestMapping(value = "/editDepartment.html")
    public ModelAndView editEmployee(WebRequest request) {
        String oldDepartmentName = request.getParameter("oldDepartmentName");
        String newDepartmentName = request.getParameter("newDepartmentName");

        boolean result = editDepartment.editDepartment(oldDepartmentName, newDepartmentName);

        return new ModelAndView("editDepartment", "result", result);
    }
}
