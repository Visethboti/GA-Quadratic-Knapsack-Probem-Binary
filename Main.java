import java.io.*;
import java.util.Scanner;

public class Main {
	// problem
	private static int[][] qkValueWeight; // [0][] is Value // [1][] is Weight
	private static int[][] qkPairValue;
	private static int qkCapacity;
	private static int numObjects;
	
	public static void main(String[] args) {
		readProblem();
		printProblem();
		
		// Run GA
		GA_Binary ga_Binary = new GA_Binary();
		ga_Binary.runGA(qkValueWeight, qkPairValue, qkCapacity, numObjects);
	}
	
	public static void readProblem() {
		try {
			File file  = new File("problem_instances\\test.txt");
			Scanner scanner = new Scanner(file);
			
			String fileName = scanner.nextLine();
			numObjects = scanner.nextInt(); // size of object in this problem instance
			
			// init problem
			qkValueWeight = new int[2][size];
			qkPairValue = new int[size][size];
			
			// read in value
			for(int i = 0; i < numObjects; i++) {
				qkValueWeight[0][i] = scanner.nextInt();
			}
			
			// init array
			for(int i = 0; i < numObjects; i++) {
				for(int j = 0; j < numObjects; j++) {
					qkPairValue[i][j] = -1;
				}
			}
			
			// read in pair value
			for(int i = 0; i < numObjects-1; i++) {
				for(int j = i+1; j < numObjects; j++) {
					qkPairValue[i][j] = scanner.nextInt();
				}
			}
			
			// empty line and constraint
			scanner.nextInt();
			
			// read in capacity
			qkCapacity = scanner.nextInt();
			
			// read in weight
			for(int i = 0; i < size; i++) {
				qkValueWeight[1][i] = scanner.nextInt();
			}
			
			// close scanner
			scanner.close();
		} catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public static void printProblem() {
		System.out.println("*** Value and Weight ***");
		print(qkValueWeight);
		System.out.println("*** Pair Value ***");
		print(qkPairValue);
	}
	
	public static void print(int[][] twoDArray) {
		int size1 = twoDArray.length;
		int size2 = twoDArray[0].length;
		for(int i = 0; i < size1; i++){
			for(int j = 0; j < size2; j++){
				System.out.print(twoDArray[i][j] + " ");
			}
			System.out.println("");
		}
	}
}