
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
	private final int popsSize = 10;
	private final double parentSizePercentage = 0.2; 
	private final double offspringSizePercentage = 0.1;
	private final double elitismSizePercentage = 0.2;
	private final double mutationProbality = 0.0005;
	private final int kTournamentSize = 3;
	
	// Parameters Size
	private final int parentSize = (int) (popsSize * parentSizePercentage);
	private final int offspringSize = (int) (popsSize * offspringSizePercentage);
	//private final int elitismSize = popsSize - offspringSize;
	private final int elitismSize = (int) (popsSize * elitismSizePercentage);
	
	// Initialization
	private char[][] populations; // populations[][0][] is Value // populations[][1][] is Weight
		
	// Fitness Calculation
	private int[] popFitness;
		
	// Parents SelectionEvent
	private int[] parentPool;
		
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
		this.parentPool = new int[parentSize];
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
		parentSelection();
		crossOver();
		elitism();
		
		printParentPool();
		printOffspringPool();
		printElitismPool();
	}
	
	// EA Methods
	private void initialization(){
		for(int i = 0; i < popsSize; i++) {
			// Only chromosome that fit in the knapsack capacity is allowed. The do while loop will run till one fit under capacity.
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
	
	private void parentSelection(){ // K-tournament Selection
		// clear Parent Pool
		for(int i = 0; i < parentSize; i++) {
			parentPool[i] = -1;
		}
		
		//int[] tournament = new int[kTournamentSize]; // to store the index to the chromosome randomly choosen from population
		int bestChromosomeIndex;
		int randomNum;
		for(int i = 0; i < parentSize; i++) { // number of parents need to choose
			bestChromosomeIndex = -1;
			for(int j = 0; j < kTournamentSize; j++) { // choose random chromosome from population ktournamentSize number of times and select the best fitness
				do {
					randomNum = random.nextInt(popsSize);
				} while(!notExistInArray(parentPool, randomNum));	
				
				if(bestChromosomeIndex == -1)
					bestChromosomeIndex = randomNum;
				else if(popFitness[randomNum] > popFitness[bestChromosomeIndex]) 
					bestChromosomeIndex = randomNum;
			}
			
			parentPool[i] = bestChromosomeIndex;
			//System.out.println("parent: "+ bestChromosomeIndex); // for debugging
		}
	}
	
	private void crossOver(){
		int randomNum1, randomNum2, randomPoint;
		for(int i = 0; i < offspringSize; i++) { // For each num of off spring required
			randomNum1 = random.nextInt(parentSize); // Randomly pick two parent from the parentPool that is not the same
			do {
				randomNum2 = random.nextInt(parentSize);
			}while(randomNum1 == randomNum2);
			
			// One point crossover
			randomPoint = random.nextInt(qkNumObjects-1) + 1; // randomPoint between 1 to number of objects
			
			for(int j = 0; j < randomPoint; j++) {
				offspringPool[i][j] = populations[parentPool[randomNum1]][j];
			}
			for(int j = randomPoint; j < qkNumObjects; j++) {
				offspringPool[i][j] = populations[parentPool[randomNum2]][j];
			}
		}
	}
	
	private void elitism() {
		int[] elitismIndex = new int[elitismSize];
		for(int i = 0; i < elitismSize; i++) 
			elitismIndex[i] = -1;
		
		int indexHighestFitness = 0;
		int highestFitness = 0;
		boolean notExist = true;
		
		// get all the indexs
		for(int i = 0; i < elitismSize; i++){	
			for(int j = 0; j < popsSize; j++){
				if(!notExistInArray(elitismIndex, j)){
					notExist = false;
				}
				
				if(highestFitness < popFitness[j] && notExist){
					highestFitness = popFitness[j];
					indexHighestFitness = j;
				}
				notExist = true;
			}
			elitismIndex[i] = indexHighestFitness;
			indexHighestFitness = 0;
			highestFitness = 0;	
		}
		
		// put the index pop into elitismPool
		for(int i = 0; i < elitismSize; i++){
			for(int j = 0; j < qkNumObjects; j++){
					elitismPool[i][j] = populations[elitismIndex[i]][j];
			}
			//System.out.println("Elitism - " + elitismIndex[i]);// for debugging
		}
		
		return;
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
	
	// *** Untilities Methods ***
	
	// Print Methods
	private void printPopulations(){
		for(int i = 0; i < popsSize; i++) {
			System.out.print(i+"-");
			System.out.print(populations[i]);
			System.out.println(" Fitness: " + popFitness[i]);
		}
	}
	
	private void printParentPool(){
		System.out.println("*** Parent Pool ***");
		for(int i = 0; i < parentSize; i++) {
			System.out.print(i+"-Pop:" + parentPool[i] + "-");
			System.out.print(populations[parentPool[i]]);
			System.out.println(" Fitness: " + popFitness[parentPool[i]]);
		}
	}
	
	private void printOffspringPool(){
		System.out.println("*** Offspring Pool ***");
		for(int i = 0; i < offspringSize; i++) {
			System.out.print(i+"-");
			System.out.print(offspringPool[i]);
			System.out.println(" Fitness: " + getFitness(offspringPool[i]));
		}
	}
	
	private void printElitismPool(){
		System.out.println("*** Elitism Pool ***");
		for(int i = 0; i < elitismSize; i++) {
			System.out.print(i+"-");
			System.out.print(elitismPool[i]);
			System.out.println(" Fitness: " + getFitness(elitismPool[i]));
		}
	}
	
	private boolean notExistInArray(int[] array, int value) {
		for(int i = 0; i < array.length; i++) {
			if(value == array[i])
				return false;
		}
		return true;
	}
}



