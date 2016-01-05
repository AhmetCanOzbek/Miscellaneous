// Name: Ahmet Can Ozbek
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * This program generates a random text using Word-level Markov generation.
 * This also performs error input checkings.
 * USAGE: java GenText [-d] prefixLength numWords sourceFile outFile 
 * @author ahmetcanozbek
 *
 */

public class GenText {	
	public static void main(String args[]){
		
		final int MAX_CHAR_PER_LINE = 80;		
		int prefixLength =0;
		int numWords =0;
		int numberOfWordsInSourceFile;
		String sourceFileName = "";
		String outputFileName = "";
		boolean isDebugMode=false;
			 
		 //Is debug mode
		 int i = 0;
		 if(args[0].equals("-d")){				
			 isDebugMode = true;
			 i = 1;				
		 }else{
			 isDebugMode = false;
			 i = 0;				
		 }		 
		 //Error checking
		 //Checking if there is any missing arguments	
		 if(isDebugMode){
			 if(args.length < 5){
			 		System.out.println("ERROR: Missing command-line arguments.");
			 		System.out.println("USAGE: java GenText [-d] prefixLength numWords sourceFile outFile");
			 		System.exit(0);
			 }
		 }else{
			 if(args.length < 4){
				 	System.out.println("ERROR: Missing command-line arguments.");
				 	System.out.println("USAGE: java GenText [-d] prefixLength numWords sourceFile outFile");
				 	System.exit(0);
			 }
		 }		 
			 
		//PrefixLength Integer checking
		 try{
			 prefixLength = Integer.parseInt(args[i]);	
		 }catch(NumberFormatException exc){
			 System.out.println("ERROR: prefixLength is not an integer.");
			 System.out.println("USAGE: java GenText [-d] prefixLength numWords sourceFile outFile");
			 System.exit(0);
		 }		 
		//numWords Integer checking	
		 try{
			 numWords = Integer.parseInt(args[i+1]);	
		 }catch(NumberFormatException exc){
			 System.out.println("ERROR: numWords is not an integer");
			 System.out.println("USAGE: java GenText [-d] prefixLength numWords sourceFile outFile");
			 System.exit(0);
		 }		 		
		 	
		 sourceFileName = args[i+2];
		 outputFileName = args[i+3];		
		 
		 //Read the input file into an arrayList
		 ArrayList<String> sampleWords = readFile(sourceFileName);
		 numberOfWordsInSourceFile = sampleWords.size();
		 checkInputParameters(numWords,prefixLength,numberOfWordsInSourceFile);
		 //Generate the random output text
		 RandomTextGenerator myText = new RandomTextGenerator(sampleWords,prefixLength,numWords, isDebugMode);
		 String outputString = myText.generateRandomText();					
		 //Write the random text to the output file
		 staticWriteToFile(outputString, outputFileName,MAX_CHAR_PER_LINE);			
	}
		
	public static void checkInputParameters(int numWords, int prefixLength, int numberOfWordsInSourceFile){
		 //numWords<0 checking
		 if(numWords<0){
			 System.out.println("ERROR: numWords is smaller than 0.");
			 System.out.println("USAGE: java GenText [-d] prefixLength numWords sourceFile outFile");
			 System.exit(0);			 
		 }
		//prefixLength<1 checking
		 if(prefixLength<1){
			 System.out.println("ERROR: prefixLength is smaller than 1.");
			 System.out.println("USAGE: java GenText [-d] prefixLength numWords sourceFile outFile");
			 System.exit(0);			 
		 }
		 //prefixLength >= numberOfWordsInSourceFile checking
		 if(numberOfWordsInSourceFile<=prefixLength){
			 System.out.println("ERROR: number of words in sourceFile is smaller than or equal to prefix length.");
			 System.exit(0);			 
		 }		  
	}
	
	/**
	 * Writes the input String to an output file with a constraint of maximum characters per line
	 * @param input The String that is going to be written into the file
	 * @param fileName the name of the output file
	 * @param maxCharPerLine maximum number of characters allowed per line in the output text
	 */	
	public static void staticWriteToFile(String input,String fileName, int maxCharPerLine) {
		
		PrintWriter out;
		try {
			out = new PrintWriter(fileName);
			String[] splitInput = input.split(" ");
			int lineLength = 0;
			
			//For loop
			for(int i=0;i<splitInput.length-1;i++){
				//Print the next word
				out.print(splitInput[i]);
				lineLength = lineLength + splitInput[i].length() + 1;
				if(maxCharPerLine-lineLength>splitInput[i+1].length()){
					//If there is enough space in the line, go on printing
					out.print(" ");
				}else{
					//If there is not enough space in the line, go to the next line
					out.println();
					lineLength =0;
				}
			}
			//Print the last word
			if(maxCharPerLine-lineLength>=splitInput[splitInput.length-1].length()){
				out.print(splitInput[splitInput.length-1]);
			}else{
				out.println();
				out.print(splitInput[splitInput.length-1]);
			}
			out.close();
		} 
		catch (FileNotFoundException exc) {
			System.out.println("ERROR: can't write to output file.");
			System.exit(0);
		}		
	}	
	
	/**
	 * Reads the file which the name of the is given as String
	 * @param fileName name of the file to be read
	 * @return the words inside file as arrayList of String
	 */
	public static ArrayList<String> readFile(String fileName){

		ArrayList<String> arr = new ArrayList<String>();	
		File inputFile = new File(fileName);
		Scanner in;
		try {
			in = new Scanner(inputFile);
			//Read the words and add to the arrayList one by one from the sample text
			while(in.hasNext()){
				arr.add(in.next());
			}
		} 
		catch (FileNotFoundException exc) {
			System.out.println("ERROR: File not found: " + fileName);
			System.exit(0);
		}	
		return arr;	
	}
}
