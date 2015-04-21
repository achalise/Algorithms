package algo.week4;

public class BaseballElimination {
	
	private String[] teams;
	private int[][] statistics;

	public BaseballElimination(String fileName) {
		// construct the league table from the file provided.
	}

	public int numberOfTeams() {
		return 0;
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

}
