/**
 * 
 */
package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author dyltr
 * base class of every DAO for most utopia tables
 */
public abstract class BaseDAO<E> {
	private Connection conn;
	
	public BaseDAO(Connection conn) {
		this.conn=conn;
	}
	
	
	protected void save(String command, Object[] values) throws ClassNotFoundException, SQLException{
		PreparedStatement prst = conn.prepareStatement(command);
		
		int count = 1;
		for(Object o: values) {
			prst.setObject(count++, o);
		}
		
		prst.executeUpdate();
		
		return;
	}
	
	public Integer saveReturnPK(String command, Object[] values) throws ClassNotFoundException, SQLException{
		PreparedStatement prst = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
		
		int count = 1;
		for(Object o: values) {
			prst.setObject(count++, o);
		}
		prst.executeUpdate();
		ResultSet rs = prst.getGeneratedKeys();
		while(rs.next()) {
			return rs.getInt(1);
		}
		return null;
	}
	
	
	protected List<E> get(String command, Object[] values) throws ClassNotFoundException, SQLException{
		PreparedStatement prst = conn.prepareStatement(command);
		
		int count = 1;
		for(Object o:values) {
			prst.setObject(count++, o);
		}
		
		ResultSet rs = prst.executeQuery();
		return extractData(rs);
	}
	
	protected abstract List<E> extractData(ResultSet rs) throws SQLException, ClassNotFoundException;
}
