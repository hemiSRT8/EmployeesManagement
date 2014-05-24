package ua.av.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.DeleteDepartmentDao;

@Controller
public class DeleteDepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteDepartmentController.class);

    @Autowired
    private DeleteDepartmentDao deleteDepartmentDao;

    @RequestMapping(value = "/deleteDepartment.html")
    public ModelAndView deleteDepartment(WebRequest request) {
        String departmentName = request.getParameter("departmentName");
        boolean result = deleteDepartmentDao.deleteDepartment(departmentName);

        if (result) {
            LOGGER.info(departmentName + " department was deleted successfully");
        } else {
            LOGGER.info("Department's deletion was failed");
        }

        return new ModelAndView("deleteDepartmentResult", "result", result);
    }
}
