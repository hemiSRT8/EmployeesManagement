package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.delete.DeleteEmployee;

@Controller
public class DeleteEmployeeController {

    @RequestMapping(value = "/deleteEmployee.html", method = RequestMethod.POST)
    public ModelAndView deleteEmployee(WebRequest request) {
        DeleteEmployee delete = new DeleteEmployee();
        delete.deleteEmployee(request);
        return new ModelAndView ("redirect:/index.html");
    }
}
