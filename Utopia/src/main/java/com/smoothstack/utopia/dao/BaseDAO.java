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
public interface BaseDAO<E> {
	default void save(String command, Object[] values, Connection conn) throws ClassNotFoundException, SQLException{
		PreparedStatement prst = conn.prepareStatement(command);
		
		int count = 1;
		for(Object o: values) {
			prst.setObject(count++, o);
		}
		
		prst.executeUpdate();
		
		return;
	}
	
	default Integer saveReturnPK(String command, Object[] values, Connection conn) throws ClassNotFoundException, SQLException{
		PreparedStatement prst = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
		
		int count = 1;
		for(Object o: values) {
			prst.setObject(count++, o);
		}
		prst.executeUpdate();
		ResultSet rs = prst.getGeneratedKeys();
		while(rs.next()) {
			//if fails, try 1
			return rs.getInt(0);
		}
		return null;
	}
	
	
	default List<E> get(String command, Object[] values, Connection conn) throws ClassNotFoundException, SQLException{
		PreparedStatement prst = conn.prepareStatement(command);
		
		int count = 1;
		for(Object o:values) {
			prst.setObject(count++, o);
		}
		
		ResultSet rs = prst.executeQuery();
		return extractData(rs);
	}
	
	abstract List<E> extractData(ResultSet rs);
}
