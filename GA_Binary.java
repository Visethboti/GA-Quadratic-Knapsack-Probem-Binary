
import java.util.Random;


public class GA_Binary {
	private static Random random;
	
	// problem
	private final int[][] qkValueWeight; // [0][] is Value // [1][] is Weight
	private final int[][] qkPairValue;
	private final int qkCapacity;
	private final int qkNumObjects;
	
	private int numGenerationsToRun;
	
	// Parameters
	private final int popsSize = 2;
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
	private char[][] populations; // populations[][0][] is Value // populations[][1][] is Weight
		
	// Fitness Calculation
	private int[] popFitness;
		
	// Parents SelectionEvent
	private char[] parentPool;
		
	// Crossover
	private char[][] offspringPool;
		
	// Elitism
	private char[][] elitismPool;
		
	// New generation
	private char[][] nextGenPopulation;
	
	// *** Constructor ***
	public GA_Binary (int[][] qkValueWeight, int[][] qkPairValue, int qkCapacity, int numObjects) {
		this.qkValueWeight = qkValueWeight;
		this.qkPairValue = qkPairValue;
		this.qkCapacity = qkCapacity;
		this.qkNumObjects = numObjects;
		
		this.populations = new char[popsSize][numObjects];
		this.popFitness = new int[popsSize];
		this.parentPool = new char[parentSize];
		this.offspringPool = new char[offspringSize][numObjects];
		this.elitismPool = new char[elitismSize][numObjects];
		this.nextGenPopulation = new char[popsSize][numObjects];
	
		this.random = new Random();
	}
	
	// *** Run ***
	public void runGA(int numGenerationsToRun){
		this.numGenerationsToRun = numGenerationsToRun;
		
		initialization();
		fitnessCalculation();
		printPopulations();
	}
	
	// EA Methods
	private void initialization(){
		for(int i = 0; i < popsSize; i++) {
			// Only chromosome that fit in the knapsack capacity is allowed
			do {
				for(int j = 0; j < qkNumObjects; j++) {
					if(random.nextBoolean()) {
						populations[i][j] = '1';
					}
					else {
						populations[i][j] = '0';
					}
				}
			} while(getFitness(populations[i]) == 0);
		}
	}
	
	private void fitnessCalculation(){
		for(int i = 0; i < popsSize; i++) {
			popFitness[i] = getFitness(populations[i]);
		}
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
	
	// GA Untilities Methods
	private int getFitness(char[] chromosome) {
		int totalWieght = 0;
		int totalValue = 0;
		for(int i = 0; i < qkNumObjects; i++) {
			if(chromosome[i] == '1') {
				totalValue += qkValueWeight[0][i];
				totalWieght += qkValueWeight[1][i];
				
				// Add up the pair value with the other object if they are choosen too
				for(int j = i+1; j < qkNumObjects; j++) {
					if(chromosome[j] == '1')
						totalValue += qkPairValue[i][j];
				}
			}
		}
		
		if(totalWieght > qkCapacity) 
			return 0;
		else
			return totalValue;
	}
	
	// Untilities Methods
	private void printPopulations(){
		for(int i = 0; i < popsSize; i++) {
			System.out.print(i+"-");
			System.out.print(populations[i]);
			System.out.println(" Fitness: " + popFitness[i]);
		}
	}
}