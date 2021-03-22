package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.entities.AirplaneType;

public class AirplaneTypeDAO extends BaseDAO<AirplaneType> {

	public AirplaneTypeDAO(Connection conn) {
		super(conn);
	}
	
	public void create(AirplaneType a) throws SQLException, ClassNotFoundException {
		save("insert into airplane_type(max_capacity, max_capacity2, max_capacity3) values(?,?,?)",
				new Object[] {a.getCapacity(),a.getCapacity2(),a.getCapacity3()});
	}
	
	public AirplaneType read(int id) throws SQLException, ClassNotFoundException {
		return get("select * from airplane_type where id=?",new Object[] {id}).get(0);
	}

	
	public void update(AirplaneType a1) throws SQLException, ClassNotFoundException {
		save("update airplane_type set max_capacity=?, max_capacity2=?, max_capacity3=? "
				+ "where id=?",new Object[] {a1.getCapacity(),a1.getCapacity2(),a1.getCapacity3(),a1.getId()});
	}

	@Override
	protected List<AirplaneType> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<AirplaneType> array = new ArrayList<AirplaneType>();
		while(rs.next()) {
			array.add(new AirplaneType(rs.getInt("id"),rs.getInt("max_capacity"),
					rs.getInt("max_capacity2"),rs.getInt("max_capacity3")));
		}
		return array;
	}

	public List<AirplaneType> readAll() throws SQLException, ClassNotFoundException {
		return get("select * from airplane_type",new Object[] {});
	}

	public void delete(int choice) throws ClassNotFoundException, SQLException {
		save("delete from airplane_type where id=?", new Object[] {choice});
	}

}
