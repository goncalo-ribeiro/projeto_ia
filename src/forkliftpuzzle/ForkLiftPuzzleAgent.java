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
        processaMatriz(matrix);
        initialEnvironment = new ForkliftPuzzleState(matrix);
        resetEnvironment();
        return environment;
    }

    private void processaMatriz(int[][] matrix) {
        int contador = 0;
        for (int linha = 0; linha < matrix.length; linha++) {
            for (int coluna = 0; coluna < matrix.length; coluna++) {
                if (matrix[linha][coluna] > 1 && matrix[linha][coluna] % 2 == 0){
                    definirIDHorizontal(matrix, contador++, linha, coluna);
                    int colunasSaltar = (matrix[linha][coluna]%10) /2 - 1;
                    coluna+=colunasSaltar;
                }
            }
        }

        for (int coluna = 0; coluna < matrix.length; coluna++) {
            for (int linha = 0; linha < matrix.length; linha++) {
                if (matrix[linha][coluna] > 1 && matrix[linha][coluna]%2==1) {
                    definirIDVertical(matrix, contador++, linha, coluna);
                    int linhasSaltar = (matrix[linha][coluna]%10) /2 - 1;
                    linha+=linhasSaltar;
                }
            }
        }
    }

    private void definirIDVertical(int[][] matrix, int contador, int linha, int coluna) {
        int valor = matrix[linha][coluna];
        int comprimento = valor / 2;
        for (int i = linha; i < linha + comprimento; i++) {
            matrix[i] [coluna] += contador*10;
        }
    }

    private void definirIDHorizontal(int[][] matrix, int contador, int linha, int coluna) {
        int valor = matrix[linha][coluna];
        int comprimento = valor / 2;
        for (int i = coluna; i < coluna + comprimento; i++) {
            matrix[linha] [i] += contador*10;
        }
    }
}
