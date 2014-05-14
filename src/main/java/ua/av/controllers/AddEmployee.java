package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.delete.DeleteEmployee;
import ua.av.entities.Cleaner;
import ua.av.entities.Developer;
import ua.av.entities.Manager;

@Controller
public class AddEmployee {


    @RequestMapping(value = "addEmployee.html", method = RequestMethod.POST)
    public ModelAndView addEmployee(WebRequest request) {
        ModelMap modelMap = new ModelMap();

        String type = request.getParameter("type");

        if("manager".equals(type)) {
            modelMap.addAttribute("manager",new Manager() );

        } else if("developer".equals(type)) {
            modelMap.addAttribute("developer",new Developer() );

        } else if("cleaner".equals(type)) {
            modelMap.addAttribute("cleaner",new Cleaner() );
        }

        return new ModelAndView ("addEmployee", "employee", modelMap);
    }

}
