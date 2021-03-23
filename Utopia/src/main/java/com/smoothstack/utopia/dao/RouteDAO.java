package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.smoothstack.utopia.entities.Route;

public class RouteDAO extends BaseDAO<Route> {

	public RouteDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Route r) throws SQLException, ClassNotFoundException {
		save("insert ignore into route(origin_id,destination_id) values(?,?)",
				new Object[] { r.getSource().getIataId(), r.getDestination().getIataId() });
	}

	public Route read(Route r) throws SQLException, ClassNotFoundException {
		return get(
				"select r.id, r.origin_id, r.destination_id, o.city as origin_city, d.city as destination_city "
						+ "from route r " + "join airport o on r.origin_id = o.iata_id "
						+ "join airport d on r.destination_id = d.iata_id "
						+ "where r.origin_id=? and r.destination_id=? and " + "o.city=? and d.city=?",
				new Object[] { r.getSource().getIataId(), r.getDestination().getIataId(), r.getSource().getCity(),
						r.getDestination().getCity() }).get(0);
	}

	/**
	 * Not used
	 */
	@Override
	protected List<Route> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Route> array = new ArrayList<Route>();
		while (rs.next()) {
			array.add(new Route(rs.getInt("id"), rs.getString("origin_id"), rs.getString("destination_id"),
					rs.getString("origin_city"), rs.getString("destination_city")));
		}
		return array;
	}

}
