package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.smoothstack.utopia.entities.Airport;

public class AirportDAO extends BaseDAO<Airport> {

	public AirportDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<Airport> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List<Airport> array = new ArrayList<Airport>();
		return array;
	}
	
	

}
