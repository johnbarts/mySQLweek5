package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Team;

public class TeamDao {
	
private Connection connection; 
private final String GET_TEAMS_QUERY = "SELECT * FROM sportsteams";
private final String CREATE_NEW_TEAM_QUERY = "INSERT INTO sportsteams(name) VALUES(?)";
private final String UPDATE_TEAM_QUERY = "UPDATE sportsteams SET name = ? WHERE id = ?"; // <-- This kept throwing a "com.mysql.cj.jdbc.exceptions. 
private final String DELETE_TEAM_BY_ID_QUERY = "DELETE FROM sportsteams WHERE id = ?";   // MysqlDataTruncation:Data truncation: Truncated incorrect DOUBLE 
																						 // value:" exception and I could not get it to work, there are 
																						 // no DOUBLE values in the database. Any advice would be welcome, 
																						 // I researched solutions online that were over my head.
																						 // This was the hardest part of the assignment for me but I didn't 
																						 // want to leave it out.
	
	public TeamDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Team> getTeams() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_TEAMS_QUERY).executeQuery();
		List<Team> teams = new ArrayList<Team>();
		
		while (rs.next()) {
			teams.add(populateTeam(rs.getInt(1), rs.getString(2)));
		}
		return teams;
	}
	
	public void createNewTeam(String teamName) throws SQLException {
		
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_TEAM_QUERY);
		ps.setString(1, teamName);
		ps.executeUpdate();
		
	}
	
	public void updateTeam(int id, String name) throws SQLException {
		
		PreparedStatement ps = connection.prepareStatement(UPDATE_TEAM_QUERY);
		ps.setString(2, name);
		ps.setInt(1, id);
		ps.executeUpdate();
		
	}

	public void deleteTeamById(int id) throws SQLException {
		
		PreparedStatement ps = connection.prepareStatement(DELETE_TEAM_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
		
	}
	
	private Team populateTeam(int id, String name) throws SQLException {
		return new Team(id, name);
	}

}
