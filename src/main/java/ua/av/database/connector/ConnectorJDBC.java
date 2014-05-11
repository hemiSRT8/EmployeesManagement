package ua.av.database.connector;

import ua.av.exception.BusinessException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectorJDBC {

    private DataSource dataSource;

    public ConnectorJDBC() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/employeesJDBC");
        } catch (NamingException e) {
            throw new BusinessException(e);
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
