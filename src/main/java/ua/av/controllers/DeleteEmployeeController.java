package ua.av.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.DeleteEmployeeDao;

import static java.lang.Long.valueOf;

@Controller
public class DeleteEmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteEmployeeController.class);

    @Autowired
    private DeleteEmployeeDao deleteEmployee;

    @RequestMapping(value = "/deleteEmployee.html", method = RequestMethod.POST)
    public ModelAndView deleteEmployee(WebRequest request) {
        long id = valueOf(request.getParameter("deleteEmployeeId"));

        boolean result = deleteEmployee.deleteEmployee(id);

        if (result) {
            LOGGER.info("Employee with id: " + id + "  was deleted successfully");
        } else {
            LOGGER.info("Employee with id: " + id + " deletion was failed");
        }

        return new ModelAndView("deleteEmployeeResult", "result", result);
    }
}
