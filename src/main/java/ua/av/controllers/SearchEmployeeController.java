package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.select.SearchEmployeeInDatabase;

@Controller
public class SearchEmployeeController {

    @RequestMapping(value = "/searchEmployee.html")
    public ModelAndView searchEmployee(WebRequest request) {
        return new ModelAndView("searchEmployee");
    }

    @RequestMapping(value = "searchEmployeeResult.html")
    public ModelAndView searchEmployeeResult(WebRequest request) {
        SearchEmployeeInDatabase search = new SearchEmployeeInDatabase();
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("foundEmployees", search.searchEmployee(request));
        modelMap.addAttribute("lastName", request.getParameter("lastName"));

        return new ModelAndView("searchEmployeeResult", modelMap);
    }
}
