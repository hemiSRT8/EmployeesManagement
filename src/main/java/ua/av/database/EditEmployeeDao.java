package ua.av.database;

import com.mysql.jdbc.MysqlDataTruncation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class EditEmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditEmployeeDao.class);

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

        if (professionOfEmployee == null) {
            LOGGER.error("professionOfEmployee was null");
            return false;
        }

        if ("manager".equalsIgnoreCase(professionOfEmployee)) {
            parameters.add("amountOfSales");
            parameters.add("percentageOfSales");
        } else if ("developer".equalsIgnoreCase(professionOfEmployee)) {
            parameters.add("linesOfCode");
        } else if ("cleaner".equalsIgnoreCase(professionOfEmployee)) {
            parameters.add("amountOfCleanedOffices");
        } else {
            LOGGER.info("type was not instance of employee");
            return false;
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
                LOGGER.error("SQL exception", e);
                return false;
            } catch (SQLException e) {
                LOGGER.error("SQL exception", e);
                return false;
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    } else {
                        LOGGER.info("connection is null while closing");
                    }
                } catch (SQLException e) {
                    LOGGER.error("SQL exception while connection closing", e);
                    throw new BusinessException();
                }
            }
        }
        return true;
    }
}
