package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.DeleteEmployee;

@Controller
public class DeleteEmployeeController {

    @Autowired
    private DeleteEmployee deleteEmployee;

    @RequestMapping(value = "/deleteEmployee.html", method = RequestMethod.POST)
    public ModelAndView deleteEmployee(WebRequest request) {
        boolean result = deleteEmployee.deleteEmployee(request);
        return new ModelAndView("deleteEmployeeResult", "result", result);
    }
}
