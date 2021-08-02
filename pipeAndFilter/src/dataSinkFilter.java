import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class dataSinkFilter extends filters{
	 private HashMap<String, Integer> counts;

	    public dataSinkFilter(BlockingQueue<String> dataIn, BlockingQueue<String> dataOut) {
	        super(dataIn, dataOut);
	        counts = new HashMap<String, Integer>();
	    }

	    @Override
	    public String getFilter(String input){
	        if(input == null){
	            return null;
	        }

	        if(counts.containsKey(input)){
	        	counts.put(input, counts.get(input) + 1);
	        }
	        else{
	        	counts.put(input, 1);
	        }
	        return null;
	    }

	    @Override
	    public void prints(){
	    	  List<Entry<String,Integer>> list = new ArrayList<Entry<String,Integer>>(counts.entrySet());
	    	Collections.sort(list, new Comparator<Entry<String,Integer>>() {
		            
		            @Override
		            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
		                String s1 = e1.getKey();
		                String s2 = e2.getKey();
		                return s2.compareTo(s1.toString());
		            }
		        }); 
	    	  
	        Collections.sort(list , new Comparator<Entry<String,Integer>>() {
	            
	            @Override
	            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
	                int v1 = e1.getValue();
	                int v2 = e2.getValue();
	                return v2-v1;
	            }
	        });
	        System.out.println("words count with frequncy");
	        for(int i = 0; i < 10;){
	        	 Map.Entry<String, Integer> result = list.get(i);
	        	 Map.Entry<String, Integer> results = list.get(i + 1);
	        	 if(result.getValue() != results.getValue()) {	
	        		 i++;
	        	 }
	        	 System.out.println(result.getKey() + " | " + result.getValue());
	        }

	        System.out.println("total numbers words left: " + list.size());
	    
	    }
	    
	    

	}
