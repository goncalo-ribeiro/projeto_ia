package eightpuzzle;

import agent.Agent;
import java.io.File;
import java.io.IOException;

public class EightPuzzleAgent extends Agent<EightPuzzleState>{
    
    protected EightPuzzleState initialEnvironment;    
    
    public EightPuzzleAgent(EightPuzzleState environment) {
        super(environment);
        initialEnvironment = (EightPuzzleState) environment.clone();
        heuristics.add(new HeuristicTileDistance());
        heuristics.add(new HeuristicTilesOutOfPlace());
        heuristic = heuristics.get(0);
    }
            
    public EightPuzzleState resetEnvironment(){
        environment = (EightPuzzleState) initialEnvironment.clone();
        return environment;
    }
                 
    public EightPuzzleState readInitialStateFromFile(File file) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(file);
        int tam = scanner.nextInt();
        int[][] matrix = new int [tam][tam];
        
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (matrix[i][j] != -1) {
                    matrix[i][j] = scanner.nextInt();
                    if (matrix[i][j] == 1) {
                        matrix [i][tam-1] = -1;
                    }
                }
            }
            scanner.nextLine();
        }
        initialEnvironment = new EightPuzzleState(matrix);
        resetEnvironment();
        System.out.println("**************************************************************************");
        return environment;
    }
}
