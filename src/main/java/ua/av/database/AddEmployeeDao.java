package ua.av.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.WebRequest;
import ua.av.entities.Cleaner;
import ua.av.entities.Developer;
import ua.av.entities.Manager;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;


public class AddEmployeeDao {

    @Autowired
    private DataSource dataSource;

    public int addEmployee(WebRequest request) {
        int result = 0;

        String type = request.getParameter("type");

        List<String> fields = new ArrayList<String>();

        fields.add(request.getParameter("lastName"));
        fields.add(request.getParameter("firstName"));
        fields.add(request.getParameter("dateOfBirth"));
        fields.add(request.getParameter("wage"));
        fields.add(request.getParameter("bonus"));
        fields.add(request.getParameter("penalty"));
        fields.add(request.getParameter("salary"));

        if("Manager".equals(type)) {
            fields.add(request.getParameter("Amount of sales"));
            fields.add(request.getParameter("Percentage of sales"));

            result = addToDB(new Manager(),fields );

        } else if("Developer".equals(type)) {
            fields.add(request.getParameter("Lines of code"));
            result = addToDB(new Developer(),fields );

        } else if ("Cleaner".equals(type)) {
            fields.add(request.getParameter("Amount of cleaned offices"));
            result = addToDB(new Cleaner(),fields );
        }
        return result;
    }

    private int addToDB(Manager man,  List<String> fields) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            CallableStatement callableStatement = connection.prepareCall("{call addManager(?,?,?,?,?,?,?,?,?)}");

            callableStatement.setString("lastName", fields.get(0));
            callableStatement.setString("firstName", fields.get(1));
            callableStatement.setDate(3, Date.valueOf(fields.get(2)));
            callableStatement.setDouble(4, parseDouble(fields.get(3)));
            callableStatement.setDouble(5, parseDouble(fields.get(4)));
            callableStatement.setDouble(6, parseDouble(fields.get(5)));
            callableStatement.setDouble(7, parseDouble(fields.get(6)));
            callableStatement.setDouble(8, parseDouble(fields.get(7)));
            callableStatement.setDouble(9, parseDouble(fields.get(8)));

            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    private int addToDB(Developer dev,  List<String> fields) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            CallableStatement callableStatement = connection.prepareCall("{call addDeveloper(?,?,?,?,?,?,?,?)}");

            callableStatement.setString("lastName", fields.get(0));
            callableStatement.setString("firstName", fields.get(1));
            callableStatement.setDate(3, Date.valueOf(fields.get(2)));
            callableStatement.setDouble(4, parseDouble(fields.get(3)));
            callableStatement.setDouble(5, parseDouble(fields.get(4)));
            callableStatement.setDouble(6, parseDouble(fields.get(5)));
            callableStatement.setDouble(7, parseDouble(fields.get(6)));
            callableStatement.setDouble(8, parseDouble(fields.get(7)));

            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 1;


    }

    private int addToDB(Cleaner cl, List<String> fields) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            CallableStatement callableStatement = connection.prepareCall("{call addCleaner(?,?,?,?,?,?,?,?)}");

            callableStatement.setString(1, fields.get(0));
            callableStatement.setString(2, fields.get(1));
            callableStatement.setDate(3, Date.valueOf(fields.get(2)));
            callableStatement.setDouble(4, parseDouble(fields.get(3)));
            callableStatement.setDouble(5, parseDouble(fields.get(4)));
            callableStatement.setDouble(6, parseDouble(fields.get(5)));
            callableStatement.setDouble(7, parseDouble(fields.get(6)));
            callableStatement.setDouble(8, parseDouble(fields.get(7)));

            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

}
