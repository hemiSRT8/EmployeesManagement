package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.EditDepartmentDao;

@Controller
public class EditDepartmentController {

    @Autowired
    private EditDepartmentDao editDepartmentDao;

    @RequestMapping(value = "/editDepartment.html")
    public ModelAndView editEmployee(WebRequest request) {

        return new ModelAndView("editDepartment", "result",
                    editDepartmentDao.editDepartment(request.getParameter("oldDepartmentName"),
                                                     request.getParameter("newDepartmentName")));
    }
}
