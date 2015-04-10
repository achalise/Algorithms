package algo.week2;

public class Outcast {

	private WordNet wordnet;
	
    public Outcast(WordNet wordnet) {
    	this.wordnet = wordnet;
    }
    
    public String outcast(String[] nouns) {
        
    	String outcast = null;
    	int max = 0;
    	
    	for(String noun : nouns) {
    		int distance = 0;
    		for(String noun2 : nouns) {
    			if(noun != noun2) {
    				distance += this.wordnet.distance(noun, noun2);
    			}
    		}
    		
    		if(distance > max) {
    			max = distance;
    			outcast = noun;
    		}
    	}
  
        return outcast;
    }
    
    public static void main(String[] args) {
    	String fileLocation = "D:/ExampleProjects/Algorithms/src/synsets1.txt";
    	String hypernymsLocation = "D:/ExampleProjects/Algorithms/src/hypernyms1.txt";
    	WordNet wordNet = new WordNet(fileLocation, hypernymsLocation);
    	Outcast outcast = new Outcast(wordNet);
    	String oc = outcast.outcast(new String[]{"enzyme", "trypsin", "thing", "pacifier"});
    	System.out.println("Outcast: " + oc);
	}
}