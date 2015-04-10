package algo.week3;

public class TwoDimensionalEdge {
	private Vertex from;
	private Vertex to;
	
	public TwoDimensionalEdge(Vertex from, Vertex to) {
		this.from = from;
		this.to = to;
	}
	
	public Vertex from() {
		return from;
	}
	
	public Vertex to() {
		return to;
	}
	
	public double weight() {
		return to.weight();
	}
	
	@Override
	public String toString() {
		return from + " to " + to;
	}

}
