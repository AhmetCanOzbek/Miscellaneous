// Name: Ahmet Can Ozbek

import java.util.ArrayList;
/**
 * Prefix class
 * Stores the words of the prefix and
 * has some methods related.
 * @author ahmetcanozbek
 *
 */

public class Prefix {

	//Instance variables
	private ArrayList<String> prefixWords;
	
	//Constructors
	public Prefix(){
		this.prefixWords = new ArrayList<String>();
	}
	public Prefix(ArrayList<String> arrList){
		this.prefixWords = arrList;
	}
	
	//Methods
	public Prefix shiftIn(String newWord){
		if(prefixWords.size() != 0){
			prefixWords.add(newWord);
			prefixWords.remove(0);
			return new Prefix(prefixWords);
		}
		return null;
	}

	/**
	 * Overriding equals method for our keys of the HashMap
	 */
	public boolean equals(Object o) {
	    if (o instanceof Prefix) {
	      Prefix other = (Prefix) o;
	      return prefixWords.equals(other.prefixWords);
	    }
	    return false;
	  }
	
	/**
	 * Overriding hashCode method for our keys of the HashMap
	 */
	public int hashCode(){
		return prefixWords.hashCode();
	}
	
	public String toString(){
		return RandomTextGenerator.mergeToString(prefixWords);
	}
	
	
	
	
	
}
