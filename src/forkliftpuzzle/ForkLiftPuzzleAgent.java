package forkliftpuzzle;

import agent.Agent;
import java.io.File;
import java.io.IOException;

public class ForkLiftPuzzleAgent extends Agent<ForkliftPuzzleState>{
    
    protected ForkliftPuzzleState initialEnvironment;
    
    public ForkLiftPuzzleAgent(ForkliftPuzzleState environment) {
        super(environment);
        initialEnvironment = (ForkliftPuzzleState) environment.clone();
        heuristics.add(new HeuristicTileDistance());
        heuristics.add(new HeuristicTilesOutOfPlace());
        heuristic = heuristics.get(0);
    }
            
    public ForkliftPuzzleState resetEnvironment(){
        environment = (ForkliftPuzzleState) initialEnvironment.clone();
        return environment;
    }

    public ForkliftPuzzleState readInitialStateFromFile(File file) throws IOException {
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
        initialEnvironment = new ForkliftPuzzleState(matrix);
        resetEnvironment();
        return environment;
    }
}
