package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.smoothstack.utopia.entities.FlightStatus;

public class FlightStatusDAO extends BaseDAO<FlightStatus> {

	public FlightStatusDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<FlightStatus> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List<FlightStatus> array = new ArrayList<FlightStatus>();
		return array;
	}

}
