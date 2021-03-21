package com.smoothstack.utopia.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Util {
	
	protected String username;
	protected String password;
	protected String url;

	public abstract Connection getConnection() throws SQLException;
}
