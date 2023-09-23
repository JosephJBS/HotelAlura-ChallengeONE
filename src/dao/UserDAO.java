package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;

import security.Encrypt;

public class UserDAO {
	private Connection con;

	public UserDAO(Connection con) {
		this.con = con;
	}

	public boolean Login(String username, String password) {
		boolean succes = false;
		int resultado = 0;
		
		String passwordEncrypt = Encrypt.encryptPassword(password);

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT COUNT(*) FROM USER_LOGIN WHERE USERNAME = ?  AND" + " PASSWORD = ?");

			try (statement) {
				statement.setString(1, username);
				statement.setString(2, passwordEncrypt);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();
				if (resultSet.next()) {
					resultado = resultSet.getInt(1); // Obtiene el valor del primer (y único) resultado
				}

				if (resultado == 1) {
					succes = true;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return succes;
	}



}
