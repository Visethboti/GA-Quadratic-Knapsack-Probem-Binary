import java.io.*;


public class Main {
	// problem
	private final int[][] qkValueWeight; // [0][] is Value // [1][] is Weight
	private final int[][] qkPairValue;
	
	public static void main(String[] args) {
		
	}
	
	public static readProblem() {
		File file  = new File("problem_instances\\jeu_100_25_1.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String fileName = scanner.nextLine();
		int size = scanner.nextInt(); // size of object in this problem instance
		
		// init problem
		qkValueWeight = new int[2][size];
		qkPairValue = new int[size][size];
		
		// read in value
		for(int i = 0; i < size; i++) {
			qkValueWeight[0][i] = scanner.nextInt();
		}
	}
	
	public static printProblem() {
		print(qkValueWeight);
		print(qkPairValue);
	}
	
	public static print(int[][] 2DArray) {
		int size1 = 2DArray.length;
		int size2 = 2DArray[0].length;
		For(int i = 0; i < size1; i++){
			For(int j = 0; j < size2; j++){
				System.out.print(2DArray[i][j]);
			}
			System.out.println("");
		}
	}
}