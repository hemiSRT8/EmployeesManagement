package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.add.AddDepartment;

@Controller
public class AddDepartmentController {

    @RequestMapping(value = "/addDepartment.html")
    public String addDepartment() {
        return ("addDepartment");
    }

    @RequestMapping(value = "/addDepartmentResult.html")
    public ModelAndView addDepartmentResult(WebRequest request) {
        AddDepartment addDepartment = new AddDepartment();
        addDepartment.addDepartment(request);
        return new ModelAndView("redirect:/index.html");
    }
}
