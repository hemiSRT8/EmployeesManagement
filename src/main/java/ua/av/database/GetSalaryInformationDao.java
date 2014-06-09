package ua.av.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetSalaryInformationDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetSalaryInformationDao.class);

    @Autowired
    private DataSource dataSource;

    public Map<String, Double> getEmployeesSalaryInformation() {
        LOGGER.info("Getting min,max,average and total expense salary for employees started");

        Connection connection = null;
        ResultSet resultSet;
        double minSalary = -1;
        double maxSalary = -1;
        double averageSalary = -1;
        double salaryExpense = -1;
        Map<String, Double> result = new HashMap<String, Double>();

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call salaryInformation()}");
            resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                minSalary = resultSet.getDouble("minSalary");
                maxSalary = resultSet.getDouble("maxSalary");
                averageSalary = resultSet.getDouble("averageSalary");
                salaryExpense = resultSet.getDouble("salaryExpense");

                result.put("minSalary", minSalary);
                result.put("maxSalary", maxSalary);
                result.put("averageSalary", averageSalary);
                result.put("salaryExpense", salaryExpense);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.info("connection is null while closing");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }

        if (maxSalary < 0 || minSalary < 0 || averageSalary < 0 || salaryExpense < 0) {
            LOGGER.error("min,max,average or expense salaries were less then 0,minSalary={},maxSalary={},averageSalary={},expense={}",
                    minSalary, maxSalary, averageSalary, salaryExpense);
        }

        LOGGER.info("Getting min,max,average and total expense salary for employees finished,min={},max={},average={},expense={}",
                minSalary, maxSalary, averageSalary, salaryExpense);

        return result;
    }

    public Map<String, Double> getSalaryExpenseForDepartment(Map<String, List<Long>> map) {
        LOGGER.info("Salary expense calculating for department started");

        Map<String, Double> result = new HashMap<String, Double>();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call departmentSalaryExpense(?)}");

            for (String department : map.keySet()) {
                callableStatement.setString("nameOfDepartment", "'" + department + "'");
                ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    result.put(department, resultSet.getDouble("salaryExpense"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception", e);
            }
        }

        LOGGER.info("Salary expense calculating for department finished");
        return result;
    }
}