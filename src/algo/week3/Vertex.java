package algo.week3;

public class Vertex {
	private int x;
	private int y;
	private double weight;
	
	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void weight(double weight) {
		this.weight = weight;
	}
	
	public double weight() {
		return this.weight;
	}
	
	public int Y() {
		return y;
	}
	
	public int X() {
		return x;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y +")";
	}

}
