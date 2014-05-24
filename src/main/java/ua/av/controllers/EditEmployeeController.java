package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.EditEmployeeDao;
import ua.av.database.SelectSingleEmployeeDao;


@Controller
public class EditEmployeeController {

    @Autowired
    private EditEmployeeDao editEmployeeDao;
    @Autowired
    private SelectSingleEmployeeDao selectSingleEmployeeDao;

    @RequestMapping(value = "/editEmployee.html", method = RequestMethod.POST)
    public ModelAndView editEmployee(WebRequest request) {
        ModelMap map = new ModelMap();

        String profession = request.getParameter("profession");
        long id = Long.valueOf(request.getParameter("editEmployeeId"));

        if (profession.contains("class ua.av.entities.Manager")) {
            map.addAttribute("employee", selectSingleEmployeeDao.selectSingleEmployee(id, "manager"));
            map.addAttribute("profession", "manager");
            return new ModelAndView("editEmployee", map);
        } else if (profession.contains("class ua.av.entities.Developer")) {
            map.addAttribute("employee", selectSingleEmployeeDao.selectSingleEmployee(id, "developer"));
            map.addAttribute("profession", "developer");
            return new ModelAndView("editEmployee", map);
        } else if (profession.contains("class ua.av.entities.Cleaner")) {
            map.addAttribute("employee", selectSingleEmployeeDao.selectSingleEmployee(id, "cleaner"));
            map.addAttribute("profession", "cleaner");
            return new ModelAndView("editEmployee", map);
        } else {
            return new ModelAndView("redirect:/index.html");
        }
    }

    @RequestMapping(value = "/editEmployeeResult.html", method = RequestMethod.POST)
    public ModelAndView editEmployeeResult(WebRequest request) {
        return new ModelAndView("editEmployeeResult", "result", editEmployeeDao.editEmployee(request));
    }
}