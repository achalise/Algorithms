package algo.week4;

import java.util.ArrayList;
import java.util.List;

import algs4.In;

public class BaseballElimination {
	
	private List<String> teams;
	private int[] wins;
	private int[] losses;
	private int[] remaining;
	private int[][] remainingBetweenTesms;
	

	public BaseballElimination(String fileName) {
		In in = new In(fileName);
		int noOfTeams = in.readInt();
		teams = new ArrayList<>();
		wins = new int[noOfTeams];
		losses = new int[noOfTeams];
		remaining = new int[noOfTeams];
		remainingBetweenTesms = new int[noOfTeams][noOfTeams];
		
		for (int i = 0; i < noOfTeams; i++) {
			String team = in.readString();
			teams.add(team);
			wins[i] = in.readInt();
			losses[i] = in.readInt();
			remaining[i] = in.readInt();
			for (int j = 0; j < noOfTeams; j++) {
				remainingBetweenTesms[i][j] = in.readInt();				
			}			
		}
		System.out.println(this.numberOfTeams());
	}

	public int numberOfTeams() {
		return teams.size();
	}

	public Iterable<String> teams() {
		return null;
	}

	public int wins(String team) {
		// number of wins for given team
		return 0;
	}

	public int losses(String team) {
		// number of losses for given team
		return 0;
	}

	public int remaining(String team) {
		// number of remaining games for given team
		return 0;
	}

	public int against(String team1, String team2) {
		// number of remaining games between team1 and team2
		return 0;
	}

	public boolean isEliminated(String team) {
		// is given team eliminated?
		return false;
	}

	public Iterable<String> certificateOfElimination(String team) {
		// subset R of teams that eliminates given team; null if not eliminated
		return null;
	}
	
	public static void main(String[] args) {
		String fileName = args[0];
		BaseballElimination elimination = new BaseballElimination(fileName);
		elimination.numberOfTeams();
	}

}
