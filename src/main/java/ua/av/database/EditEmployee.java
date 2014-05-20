package ua.av.database;

import com.mysql.jdbc.MysqlDataTruncation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.valueOf;

@Component
public class EditEmployee {

    @Autowired
    private DataSource dataSource;

    public boolean editEmployee(WebRequest request) {
        Connection connection = null;

        Long id = valueOf(request.getParameter("id"));

        List<String> parameters = new ArrayList<String>();
        parameters.add("lastName");
        parameters.add("firstName");
        parameters.add("dateOfBirth");
        parameters.add("wage");
        parameters.add("bonus");
        parameters.add("penalty");
        parameters.add("salary");

        String professionOfEmployee = request.getParameter("type");

        if ("manager".equalsIgnoreCase(professionOfEmployee)) {
            parameters.add("amountOfSales");
            parameters.add("percentageOfSales");
        } else if ("developer".equalsIgnoreCase(professionOfEmployee)) {
            parameters.add("linesOfCode");
        } else if ("cleaner".equalsIgnoreCase(professionOfEmployee)) {
            parameters.add("amountOfCleanedOffices");
        }

        for (String parameter : parameters) {
            try {
                connection = dataSource.getConnection();
                CallableStatement callableStatement = connection.prepareCall("{call editEmployee(?,?,?,?)}");
                callableStatement.setLong("id", id);
                callableStatement.setString("tableName", professionOfEmployee);
                callableStatement.setString("colName", parameter);
                callableStatement.setString("newValue", "'" + request.getParameter(parameter) + "'");

                if (request.getParameter(parameter).equals("")) {
                    continue;
                }

                callableStatement.executeUpdate();

            } catch (MysqlDataTruncation e) {
                return false;
            } catch (SQLException e) {
                return false;
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    throw new BusinessException();
                }
            }
        }
        return true;
    }
}
