package ua.av.database;

import com.mysql.jdbc.MysqlDataTruncation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;


@Component
public class EditEmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditEmployeeDao.class);

    @Autowired
    private DataSource dataSource;

    public boolean editEmployee(Long id, String professionOfEmployee, Map<String, String> fieldsAndValues) {
        if (id == null) {
            LOGGER.error("id was null");
            return false;
        } else if (professionOfEmployee == null) {
            LOGGER.error("professionOfEmployee was null");
            return false;
        } else if (fieldsAndValues == null) {
            LOGGER.error("fieldsAndValues was null");
            return false;
        }

        Connection connection = null;

        for (String parameter : fieldsAndValues.keySet()) {
            String value = fieldsAndValues.get(parameter);

            if (parameter != null && value != null) {
                try {
                    connection = dataSource.getConnection();
                    CallableStatement callableStatement = connection.prepareCall("{call editEmployee(?,?,?,?)}");
                    callableStatement.setLong("id", id);
                    callableStatement.setString("tableName", professionOfEmployee);
                    callableStatement.setString("colName", parameter);
                    callableStatement.setString("newValue", "'" + value + "'");

                    if (value.equals("")) {
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
        }

        LOGGER.info("Employee with id {} was edited successfully", id);
        return true;
    }
}
