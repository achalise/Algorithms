package algo.week4;

import java.util.ArrayList;
import java.util.List;

import algs4.FlowEdge;
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
		
		FlowNetwork flowNetwork = createFlowNetwork(team);
		System.out.println(flowNetwork.toString());
		return false;
	}

	private FlowNetwork createFlowNetwork(String team) {
		int maxWinX = maxWinForTeam(team);
		
		int noOfGames = (numberOfTeams() * (numberOfTeams() - 1))/2;
		int vertices = noOfGames + teams.size() + 2;
		FlowNetwork flowNetwork = new FlowNetwork(vertices);
		int s = 0; // source
		int t = vertices - 1; //sink
		
		int v = 1; // current
		
		for (int i = 0; i < numberOfTeams(); i++) {
			for(int j = i + 1; j < numberOfTeams(); j++) {
				
				if (i == j) {
					continue;
				}
				
				flowNetwork.addEdge(new FlowEdge(s, v, remainingBetweenTesms[i][j]));

				flowNetwork.addEdge(new FlowEdge(v, noOfGames + 1 + i, Integer.MAX_VALUE));
				flowNetwork.addEdge(new FlowEdge(v, noOfGames + 1 + j, Integer.MAX_VALUE));		

				flowNetwork.addEdge(new FlowEdge((noOfGames + 1 + i), t, maxWinX - wins[i]));
				flowNetwork.addEdge(new FlowEdge((noOfGames + 1 + j), t, maxWinX - wins[j]));	
				v++;
			}
			
		}
		


		return flowNetwork;
	}

	private int maxWinForTeam(String team) {
		int x = teams.indexOf(team);
		int maxWinsPossibleX = wins[x] + remaining[x];
		return maxWinsPossibleX;
	}

	private boolean triviallyEliminated(String team) {
		int maxWinsPossibleX = maxWinForTeam(team);
		System.out.println("Max wins possible for " + team + " = " + maxWinsPossibleX);
		for(int i = 0; i < numberOfTeams(); i++) {
			if (maxWinsPossibleX <= (wins[i] )) {
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
		
		for(String team: elimination.teams()) {
			if (elimination.isEliminated(team)) {
				System.out.println(team + " is eliminated");
			} else {
				System.out.println(team + " is not eliminated");
			}
		}
	}

}
