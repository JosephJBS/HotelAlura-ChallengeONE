package dbConection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import models.Constantes;

public class ConnectionDB {
	private DataSource dataSource;

	public ConnectionDB() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/"+ Constantes.DB_NAME+"?useTimeZone=true&serverTimeZone=UTC");
        comboPooledDataSource.setUser(Constantes.DB_USER);
        comboPooledDataSource.setPassword(Constantes.DB_PASSWORD);
        comboPooledDataSource.setMaxPoolSize(10);
        
        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperaConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
