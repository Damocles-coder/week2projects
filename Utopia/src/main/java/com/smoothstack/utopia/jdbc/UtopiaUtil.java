package com.smoothstack.utopia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class UtopiaUtil extends Util {

	public UtopiaUtil(String url, String username, String password) {
		this.url = url;
		this.password = password;
		this.username = username;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}
