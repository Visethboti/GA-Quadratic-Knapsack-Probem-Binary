



public class GA_Binary {
	private static Random random;
	
	// problem
	private final int[][] qurdraticKnapsackProblem;
	private int numGenerationsToRun;
	
	// Parameters
	private final int popsSize = 100;
	private final double parentSizePercentage = 0.5; 
	private final double offspringSizePercentage = 0.9;
	private final double elitismSizePercentage = 0.1;
	private final double mutationProbality = 0.0005;
	private final int kTournamentSize = 3;
	
	// Parameters Size
	private final int parentSize = (int) (popsSize * parentSizePercentage);
	private final int offspringSize = (int) (popsSize * offspringSizePercentage);
	private final int elitismSize = popsSize - offspringSize;
	
	// Initialization
	private int[][] populations; // populations[][0][] is Value // populations[][1][] is Weight
		
	// Fitness Calculation
	private int[] popFitness;
		
	// Parents SelectionEvent
	private int[] parentPool;
		
	// Crossover
	private int[][][] offspringPool;
		
	// Elitism
	private int[][][] elitismPool;
		
	// New generation
	private int[][][] nextGenPopulation;
	
	// *** Constructor ***
	public GA_Binary (int[][] problem) {
		this.qurdraticKnapsackProblem = problem;
		
		
	}
	
	// *** Run ***
	public void runEA(int numGenerationsToRun){
		this.numGenerationsToRun = numGenerationsToRun;
		
		
	}
	
	// EA Methods
	private void initialization(){
	
	}
	
	private void fitnessCalculation(){
		
	}
	
	private void parentSelection(){
		
	}
	
	private void crossOver(){
		
	}
	
	private void elitism() {
		
	}
	
	private void replaceGeneration(){
		
	}
	
	private void mutation(){
		
	}
	
	
	// Untilies Methods
	private void printPopulations(){
		
	}
}