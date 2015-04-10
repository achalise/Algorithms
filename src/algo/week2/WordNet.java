package algo.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algs4.Digraph;
import algs4.In;


/**o
 * Created with IntelliJ IDEA.
 * User: arunc
 * Date: 27/03/15
 * Time: 1:49 PM
 */
public class WordNet {
	
	private Map<Integer, String> synsetIdToNouns = new HashMap<Integer, String>();
	private Map<String, Integer> nounsToSynsetId = new HashMap<>();
    private Map<Integer, List<Integer>> hypernyms = new HashMap<Integer, List<Integer>>();
	private Digraph digraph;
	
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
    	readInSynsets(synsets);
    	readInHypernyms(hypernyms);
    }

	private void readInHypernyms(String hypernymsFileName) {
		digraph = new Digraph(synsetIdToNouns.size());
		In in = new In(hypernymsFileName);
		String line;
		List<Integer> hypernymsForTheSynSetID = new ArrayList<Integer>();
		while ((line = in.readLine()) != null) {
			String[] parts = line.split(",");
			Integer synId = Integer.parseInt(parts[0]);
			Integer hyperNymId;
			for(int i = 1; i < parts.length; i++) {
				hyperNymId = Integer.parseInt(parts[i]);
				hypernymsForTheSynSetID.add(hyperNymId);
				
				digraph.addEdge(synId, hyperNymId);
			}
			hypernyms.put(synId, hypernymsForTheSynSetID);
		}
	}

	private void readInSynsets(String synsets) {
		processSynsets(synsets);
	}

	private void processSynsets(String synsets) {
		In in = new In(synsets);
    	String line;
    	while ((line = in.readLine()) != null) {
    		String[] parts = line.split(",");
    		Integer synsetId = Integer.parseInt(parts[0]);
    		String[] nouns = parts[1].split(" ");
    		List<String> nounsForTheSynsetId = new ArrayList<String>();
    		for(String noun: nouns) {
    			nounsForTheSynsetId.add(noun);
    			nounsToSynsetId.put(noun, synsetId);
    		}
    		synsetIdToNouns.put(synsetId, parts[1]);    		
    	}
	}
    
    //returns all WordNet nouns
    public Iterable<String> nouns() {
    	return nounsToSynsetId.keySet();
    }
    
    // is the word a WordNet noun?
    public boolean isNoun(String word) {
    	return nounsToSynsetId.keySet().contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
    	if(!isNoun(nounA) || !isNoun(nounB)) {
    		throw new IllegalArgumentException(nounA + " or " + nounB + " not a noun");
    	}
    	SAP sap = new SAP(digraph);
    	int distance = sap.length(nounsToSynsetId.get(nounA), nounsToSynsetId.get(nounB));
    	return distance;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
    	SAP sap = new SAP(digraph);
    	int ancestor = sap.ancestor(nounsToSynsetId.get(nounA), nounsToSynsetId.get(nounB));
    	return synsetIdToNouns.get(ancestor);
    }

    // do unit testing of this class
    public static void main(String[] args) {
    	String fileLocation = "D:/ExampleProjects/Algorithms/src/synsets1.txt";
    	String hypernymsLocation = "D:/ExampleProjects/Algorithms/src/hypernyms1.txt";
    	WordNet wordNet = new WordNet(fileLocation, hypernymsLocation);
    	
    	String sap = wordNet.sap("prolamine",  "enzyme");
    	System.out.println(sap);
    }
}
