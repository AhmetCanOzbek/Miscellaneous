// Name: Ahmet Can Ozbek
import java.util.ArrayList;
import java.util.Random;


/**
 * 
 * @author ahmetcanozbek
 * Container class to hold successor words.
 * Each key(Prefix) in the map will have a container which will hold
 * its successor words.
 * This class is going to handle multiple repeated successors
 * It will be easier to design the system using this class.
 */
public class myContainer {
	
	
	//Instance variables
	private ArrayList<String> containerArrayList;
	private Random numberGenerator;
	private boolean isDebugMode;
	
	
	//Constructors
	public myContainer(boolean isDebugMode){
		containerArrayList = new ArrayList<String>();
		this.isDebugMode = isDebugMode;
		if(isDebugMode){
			//If we are in debug mode, set the seed value
			numberGenerator = new Random(RandomTextGenerator.SEED_VALUE);
		}else{
			numberGenerator = new Random();
		}
	}	
	
	//Methods
	/**
	 * Add a new successor word to the container
	 * @param newWord new word to be added to the container.
	 */
	public void add(String newWord){
		containerArrayList.add(newWord);
	}
	
	/**
	 * Gets the contents of the container as String
	 */
	public String toString(){
		return RandomTextGenerator.mergeToString(containerArrayList);
	}
	
	/**
	 * Chooses a random successor word from the container
	 * @return random chosen successor
	 */
	public String getRandomWord(){
		return containerArrayList.get(numberGenerator.nextInt(containerArrayList.size()));
	}
	
	/**
	 * 
	 * @return Whether the container is empty or not
	 */
	public boolean isEmpty(){
		return containerArrayList.isEmpty();
	}
	
	
}
