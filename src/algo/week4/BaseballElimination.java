package algo.week4;

import java.util.ArrayList;
import java.util.List;

import algs4.FlowNetwork;
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
		teams = new ArrayList<String>();
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
		return teams;
	}

	public int wins(String team) {
		// number of wins for given team
		return wins[teams.indexOf(team)];
	}

	public int losses(String team) {
		// number of losses for given team
		return losses[teams.indexOf(team)];
	}

	public int remaining(String team) {
		// number of remaining games for given team
		return remaining[teams.indexOf(team)];
	}

	public int against(String team1, String team2) {
		// number of remaining games between team1 and team2
		return remainingBetweenTesms[teams.indexOf(team1)][teams.indexOf(team2)];
	}

	public boolean isEliminated(String team) {
		// is given team eliminated?
		if(triviallyEliminated(team)) {
			return true;
		}
		
		FlowNetwork flowNetwork = createFlowNetwork();
		
		return false;
	}

	private FlowNetwork createFlowNetwork() {
		FlowNetwork flowNetwork = new FlowNetwork(numberOfTeams());
		//flowNetwork.addEdge(e)
		return null;
	}

	private boolean triviallyEliminated(String team) {
		int x = teams.indexOf(team);
		int maxWinsPossibleX = wins[x] + remaining[x];
		for(int i = 0; i < numberOfTeams(); i++) {
			if (maxWinsPossibleX < (wins[i] + remaining[i])) {
				return true;
			}
		}
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
		System.out.println("Remaining games between Montreal and Philadelphia: " + elimination.against("Montreal", "Philadelphia"));
	}

}
