package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.entities.AirplaneType;
import com.smoothstack.utopia.entities.Airport;
import com.smoothstack.utopia.entities.User;

public class AirplaneTypeDAO extends BaseDAO<AirplaneType> {

	public AirplaneTypeDAO(Connection conn) {
		super(conn);
	}
	
	public AirplaneType read(int id) throws SQLException, ClassNotFoundException {
		return get("select * from airplane_type where id=?",new Object[] {id}).get(0);
	}

	
	public void update(AirplaneType a1) throws SQLException, ClassNotFoundException {
		save("update airplane_type set max_capacity=?, max_capacity2=?, max_capacity3 "
				+ "where id=?",new Object[] {a1.getCapacity(),a1.getId()});
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
	
	

}
