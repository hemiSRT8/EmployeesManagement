package ua.av.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.AddDepartmentDao;

@Controller
public class AddDepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddDepartmentController.class);

    @Autowired
    private AddDepartmentDao addDepartmentDao;

    @RequestMapping(value = "/addDepartment.html")
    public ModelAndView addDepartmentResult(WebRequest request) {
        String departmentName = request.getParameter("departmentName");

        boolean result = addDepartmentDao.addDepartment(departmentName);

        if (result) {
            LOGGER.info(departmentName + " department was added successfully");
        } else {
            LOGGER.info("Department's add was failed");
        }

        return new ModelAndView("addDepartment", "result", result);
    }
}
