package ua.av.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.EditDepartmentDao;

@Controller
public class EditDepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditDepartmentController.class);

    @Autowired
    private EditDepartmentDao editDepartmentDao;

    @RequestMapping(value = "/editDepartment.html")
    public ModelAndView editEmployee(WebRequest request) {
        String oldDepartmentName = request.getParameter("oldDepartmentName");
        String newDepartmentName = request.getParameter("newDepartmentName");

        boolean result = editDepartmentDao.editDepartment(oldDepartmentName, newDepartmentName);

        if (result) {
            LOGGER.info("Department was edited successfully.Old name/new name"
                    + oldDepartmentName + " " + newDepartmentName);
        } else {
            LOGGER.info("Department's edition was failed");
        }

        return new ModelAndView("editDepartment", "result", result);
    }
}
