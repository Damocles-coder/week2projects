package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.smoothstack.utopia.entities.User;

public class UserDAO extends BaseDAO<User> {

	public UserDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<User> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List<User> array = new ArrayList<User>();
		return array;
	}

}
