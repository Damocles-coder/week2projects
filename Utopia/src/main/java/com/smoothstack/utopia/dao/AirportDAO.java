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
	
	public void create(Airport airport) throws ClassNotFoundException, SQLException{
		save("insert into airport values(?,?)", new Object[] {airport.getIataId(), airport.getCity()});
	}
	
	public void update(Airport airport) throws ClassNotFoundException, SQLException{
		save("update airport set city=? where iata_id=?", new Object[] {airport.getCity(),airport.getIataId()});
	}
	
	public void delete(Airport airport) throws ClassNotFoundException, SQLException{
		save("delete from airport where iata_id=?", new Object[] {airport.getIataId()});
	}
	
	public List<Airport> readByCode(String code) throws ClassNotFoundException, SQLException {
		return get("select * from airport where iata_id=?",new Object[] {code});
	}
	
	public List<Airport> readAll() throws ClassNotFoundException, SQLException {
		return get("select * from airport",new Object[] {});
	}

	@Override
	public List<Airport> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List<Airport> array = new ArrayList<Airport>();
		while(rs.next()) {
			array.add(new Airport(rs.getString("iata_id"),rs.getString("city")));
		}
		return array;
	}
	
	

}
