package restAssuredFramework;

import java.util.HashMap;
import java.util.Map;

public class testExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<Character, Integer> baseMap = new HashMap<Character, Integer>();
		
		String str= "Happy Happy Testing";
		int count =0;
	    str = str.toLowerCase();
	    // split the words in a string using split function
	    String[] words = str.split(" ");
	    for(int i =1; i <words.length ; i++) {
	    	for (int j =i+1; j <words.length ; j++) {
	    		if(words[i].equals(words[j]))
	    				{
	    			      count ++;
	    			      words[j] = "0";
	    				}    		
	    	}
	    	if(count > 1 && words[i]!= "0")
	    	System.out.println("The word " + words[i] + "occurs multiple times in the string");	    		       	    	    	
	    }
	    	
	}
}
