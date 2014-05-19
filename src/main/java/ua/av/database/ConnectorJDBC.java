package ua.av.database;

import javax.sql.DataSource;

public class ConnectorJDBC {

    private DataSource dataSource;

    public ConnectorJDBC() {
        //Config by Spring
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
