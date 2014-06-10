package ua.av.database.parser;

import org.junit.Test;
import ua.av.entities.Department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DepartmentParserTest {

    @Test
    public void testDepartmentParser() {

        /**
         * Setup mock data
         */

        ResultSet resultSet = mock(ResultSet.class);
        Map<Long, List<Department>> departmentsList = null;

        try {
            when(resultSet.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false);

            when(resultSet.getString("departmentName"))
                    .thenReturn("Logistic")
                    .thenReturn("It")
                    .thenReturn("Eco");

            when(resultSet.getLong("employeeId")).thenReturn(69L);

            departmentsList = DepartmentParser.parseDepartments(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         * Results validation
         */

        assertTrue(departmentsList.size() == 1); //all map size

        List<Department> departmentNames = departmentsList.get(69L);

        assertTrue(departmentNames.size() == 3); //size of departments
        assertEquals(departmentNames.get(0).getName(), "Logistic");
        assertEquals(departmentNames.get(1).getName(), "It");
        assertEquals(departmentNames.get(2).getName(), "Eco");

        assertTrue(departmentsList.keySet().size() == 1); //employees size
        assertTrue(departmentsList.containsKey(69L));
    }
}
