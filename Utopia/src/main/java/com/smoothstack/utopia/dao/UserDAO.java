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
	
	public void create(User user) throws ClassNotFoundException, SQLException {
		save("insert into user(role_id,given_name,family_name,username,email,password,phone) "
				+ "values(?,?,?,?,?,?,?)",new Object[] {user.getRoleId(),user.getGivenName(),user.getFamilyName(),
						user.getUsername(),user.getEmail(),user.getPassword(),user.getPhone()});
	}
	
	public User read(String email) throws ClassNotFoundException, SQLException {
		return get("select * from user where email=?",new Object[] {email}).get(0);
	}
	
	public void update(User user) throws ClassNotFoundException, SQLException {
		save("update user set role_id=?,given_name=?,family_name=?,username=?,email=?,password=?,phone=? "
				+ "where id=?",new Object[] {user.getRoleId(),user.getGivenName(),user.getFamilyName(),
						user.getUsername(),user.getEmail(),user.getPassword(),user.getPhone(),user.getId()});
	}
	
	/**
	 * @param email more likely to delete from email
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void delete(String email) throws ClassNotFoundException, SQLException {
		save("delete from user where email=?",new Object[] {email});
	}
	
	
	/**
	 * @return list of users
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<User> readAll() throws ClassNotFoundException, SQLException{
		return get("select * from user",new Object[] {});
	}

	@Override
	public List<User> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List<User> array = new ArrayList<User>();
		while(rs.next()) {
			array.add(new User(rs.getInt("id"), rs.getInt("role_id"), 
					rs.getString("given_name"), rs.getString("family_name"), rs.getString("username"), 
					rs.getString("email"), rs.getString("password"), rs.getString("phone")));
		}
		return array;
	}

}
