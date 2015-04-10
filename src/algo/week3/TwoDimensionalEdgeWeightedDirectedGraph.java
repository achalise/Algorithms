package algo.week3;

import algs4.Bag;

public class TwoDimensionalEdgeWeightedDirectedGraph {
	private int width;
	private int height;
	private int[][] vertices;
	private Bag<TwoDimensionalEdge>[][] adj;
	
	public TwoDimensionalEdgeWeightedDirectedGraph(int width, int height) {
		this.width = width;
		this.height = height;
		vertices = new int[width][height];
		adj = (Bag<TwoDimensionalEdge>[][])new Bag[width][height];
		for(int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				adj[i][j] = new Bag<TwoDimensionalEdge>();
			}
		}
	}
	
	public void addEdge(TwoDimensionalEdge edge) {
		Vertex from = edge.from();
		adj[from.X()][from.Y()].add(edge);
	}
	
	public Iterable<TwoDimensionalEdge> edges(Vertex vertex) {
		return adj[vertex.X()][vertex.Y()];
	}
	
	public void populateEdges() {
		int i = 0;
		for (int j = 0; j < height - 1; j++ ) {
			Vertex from = new Vertex(i, j);
			adj[i][j].add(new TwoDimensionalEdge(from, new Vertex(i, j+1)));
			adj[i][j].add(new TwoDimensionalEdge(from, new Vertex(i+1, j+1)));
		}
		
		for(i = 1; i < width - 1; i++) {
			for (int j = 0; j < height -1; j++) {
				Vertex from = new Vertex(i, j);
				adj[i][j].add(new TwoDimensionalEdge(from, new Vertex(i, j+1)));
				adj[i][j].add(new TwoDimensionalEdge(from, new Vertex(i+1, j+1)));
				adj[i][j].add(new TwoDimensionalEdge(from, new Vertex(i-1, j+1)));				
			}
		}
		i = width - 1;
		for (int j = 0; j < height - 1; j++ ) {
			Vertex from = new Vertex(i, j);
			adj[i][j].add(new TwoDimensionalEdge(from, new Vertex(i, j+1)));
			adj[i][j].add(new TwoDimensionalEdge(from, new Vertex(i-1, j+1)));
		}		
	}
	
	public static void main(String[] args) {
		TwoDimensionalEdgeWeightedDirectedGraph twoDimensionalEdgeWeightedDirectedGraph = new TwoDimensionalEdgeWeightedDirectedGraph(2, 2);
		twoDimensionalEdgeWeightedDirectedGraph.populateEdges();
		for(int i = 0 ; i < twoDimensionalEdgeWeightedDirectedGraph.width; i++) {
			for (int j = 0; j < twoDimensionalEdgeWeightedDirectedGraph.height; j++) {
				Bag<TwoDimensionalEdge> edges = twoDimensionalEdgeWeightedDirectedGraph.adj[i][j];
				System.out.println("Edges from [" + i + "][" + j +"]" );
				for(TwoDimensionalEdge edge: edges) {
					System.out.println("\t" + edge);
				}
			}
		}		
	}
}
