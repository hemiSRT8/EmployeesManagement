package ua.av.database.parser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ua.av.entities.Department;
import ua.av.exception.BusinessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DepartmentParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentParser.class);

    public Map<Long, List<Department>> parseDepartments(ResultSet departments) {
        LOGGER.info("Parsing departments started");
        Map<Long, List<Department>> departmentsMap = new HashMap<Long, List<Department>>();
        try {
            while (departments.next()) {
                Department department = new Department(departments.getString("departmentName"));
                Long employeeId = departments.getLong("employeeId");

                if (!departmentsMap.containsKey(employeeId)) {
                    List<Department> departmentsBox = new ArrayList<Department>();
                    departmentsMap.put(employeeId, departmentsBox);
                }
                departmentsMap.get(employeeId).add(department);
            }
            LOGGER.info("Parsing departments finished");
            if (LOGGER.isDebugEnabled()) {
                for (Long employeeId : departmentsMap.keySet()) {
                    LOGGER.debug("Employee  id{} -> linked deparments {}", employeeId, departmentsMap.get(employeeId));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error occurs during parsing departments from result set", e);
            throw new BusinessException(e);
        }

        return departmentsMap;
    }
}
