package algo.week2;


import algs4.BreadthFirstDirectedPaths;
import algs4.Digraph;
import algs4.In;
import algs4.Queue;

public class SAP {
	   private Digraph G;
	   // constructor takes a digraph (not necessarily a DAG)
	   public SAP(Digraph G) {
		   this.G = new Digraph(G);
	   }

	   // length of shortest ancestral path between v and w; -1 if no such path
	   public int length(int v, int w) {
		   validate(v,w);
		   BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(G,  v);
		   BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(G,  w);
		   int ancestor = ancestor(v, w);		   
		   return bfdpV.distTo(ancestor) + bfdpW.distTo(ancestor);
	   }

	   private void validate(int v, int w) {
		   if (v < 0 || v > G.V() || w < 0 || w > G.V()) {
			   throw new IllegalArgumentException("Invalid vertex");
		   }
	   }

	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	   public int ancestor(int v, int w) {
		   BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(G,  v);
		   BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(G,  w);
		   Queue<Integer> ancestors = new Queue<>(); 
		   
		   for (int i = 0; i < G.V(); i++) {
			   if (bfdpV.hasPathTo(i) && bfdpW.hasPathTo(i)) {
				   ancestors.enqueue(i);
			   }
		   }
		   
		   int shortestLength = Integer.MAX_VALUE;
		   int shortestAncestsor = -1;
		   while (!ancestors.isEmpty()) {
			   int ancestor = ancestors.dequeue();
			   int distV =  bfdpV.distTo(ancestor);
			   int distW = bfdpW.distTo(ancestor);
			   int totalDist = distV + distW;
			   if (totalDist < shortestLength) {
				   shortestLength = totalDist;
				   shortestAncestsor = ancestor;
			   }
		   }		   
		   return shortestAncestsor;
	   }

	   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	   public int length(Iterable<Integer> v, Iterable<Integer> w) {
		   return 0;
	   }

	   // a common ancestor that participates in shortest ancestral path; -1 if no such path
	   public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		   BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(G,  v);
		   BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(G,  w);
		   Queue<Integer> ancestors = new Queue<>(); 
		   
		   for (int i = 0; i < G.V(); i++) {
			   if (bfdpV.hasPathTo(i) && bfdpW.hasPathTo(i)) {
				   ancestors.enqueue(i);
			   }
		   }
		   
		   int shortestLength = Integer.MAX_VALUE;
		   int shortestAncestsor = -1;
		   while (!ancestors.isEmpty()) {
			   int ancestor = ancestors.dequeue();
			   int distV =  bfdpV.distTo(ancestor);
			   int distW = bfdpW.distTo(ancestor);
			   int totalDist = distV + distW;
			   if (totalDist < shortestLength) {
				   shortestLength = totalDist;
				   shortestAncestsor = ancestor;
			   }
		   }		   
		   return shortestAncestsor;
	   }

	   // do unit testing of this class
	   public static void main(String[] args) {
		  In in = new In("D:/ExampleProjects/Algorithms/src/digraph9.txt");
		  Digraph G = new Digraph(in);
		  SAP sap = new SAP(G);
		  sap.length(-1, 4);
	   }
}
