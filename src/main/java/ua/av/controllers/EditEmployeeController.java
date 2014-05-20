package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.EditEmployee;
import ua.av.database.SelectSingleEmployee;

@Controller
public class EditEmployeeController {

    @Autowired
    private EditEmployee editEmployee;
    @Autowired
    private SelectSingleEmployee selectSingleEmployee;

    @RequestMapping(value = "/editEmployee.html", method = RequestMethod.POST)
    public ModelAndView editEmployee(WebRequest request) {
        ModelMap map = new ModelMap();
        if ("manager".equalsIgnoreCase(request.getParameter("profession"))) {
            map.addAttribute("employee", selectSingleEmployee.selectSingleEmployee(request, "manager"));
            map.addAttribute("profession", "manager");
            return new ModelAndView("editEmployee", map);
        } else if ("developer".equalsIgnoreCase(request.getParameter("profession"))) {
            map.addAttribute("employee", selectSingleEmployee.selectSingleEmployee(request, "developer"));
            map.addAttribute("profession", "developer");
            return new ModelAndView("editEmployee", map);
        } else if ("cleaner".equalsIgnoreCase(request.getParameter("profession"))) {
            map.addAttribute("employee", selectSingleEmployee.selectSingleEmployee(request, "cleaner"));
            map.addAttribute("profession", "cleaner");
            return new ModelAndView("editEmployee", map);
        } else {
            return new ModelAndView("redirect:/index.html");
        }
    }

    @RequestMapping(value = "/editEmployeeResult.html", method = RequestMethod.POST)
    public ModelAndView editEmployeeResult(WebRequest request) {
        boolean result = editEmployee.editEmployee(request);
        return new ModelAndView("editEmployeeResult", "result", result);
    }
}