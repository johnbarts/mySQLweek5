package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import dao.TeamDao;
import entity.Team;

public class Menu {
	
	private TeamDao teamDao = new TeamDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Teams",  
			"Create Team",
			"Update Team",
			"Delete Team",
			"Exit");
	
	public void start() {
		String selection = "";
		
		do {
			
			printMenu();
			selection = scanner.nextLine();
			
			try {
				
				if (selection.equals("1")) {
					displayTeams();
				} else if (selection.equals("2")) {
					createTeam();
				} else if (selection.equals("3")) {
					updateTeam();
				} else if (selection.equals("4")) {
					deleteTeam();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("< < < Press enter to continue > > >");
			scanner.nextLine();
		
		} while (!selection.equals("5"));
		
		System.out.println("Goodbye!");
	}
	
	private void printMenu() {
		
		System.out.println("Select an Option:\n----------------------");
		
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void displayTeams() throws SQLException {
		
		List<Team> teams = teamDao.getTeams();
		
		for (Team team : teams) {
			System.out.println(team.getTeamId() + ": " + team.getName()); 
		}
	}
	
	private void createTeam() throws SQLException {
		
		System.out.print("Enter new team name:");
		String teamName = scanner.nextLine();
		teamDao.createNewTeam(teamName);
	}
	
	private void updateTeam() throws SQLException {
		
		System.out.print("Enter team id to update:");
		int id = Integer.parseInt(scanner.nextLine());
		
		System.out.print("Enter new team name: ");
		String name = scanner.nextLine();
		
		teamDao.updateTeam(id, name);
	}
	
	private void deleteTeam() throws SQLException {
		
		System.out.print("Enter team id to delete:");
		int id = Integer.parseInt(scanner.nextLine());
		teamDao.deleteTeamById(id);
	}

}
