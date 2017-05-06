package agent;

import java.util.List;

public abstract class Problem <S extends State>{

    //TODO
    protected Heuristic heuristic;

    //estado inicial
    protected S initialState;

    //estado final
    static final int[][] goalMatrix = {{0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}};

    // um conjunto de operadores (actions) que permitem criar novos estados a partir de um determinado estado
    protected List<Action> actions;

    public Problem(S initialState, List<Action> actions) {
        this.initialState = initialState;
        this.actions = actions;
    }

//      Implemente a classe Problem, que descreve um problema genérico. Um problema tem um
//        estado inicial e um conjunto de operadores (actions) que permitem criar novos estados a partir
//            de um determinado estado. Além disso, deve fornecer serviços que permitam:

//  => Aplicar todos os operadores a um determinado estado e devolver os estados resultantes;
    public abstract List<S> executeActions(S state);

//    => Verificar se um determinado estado é um estado objetivo;
    public abstract Boolean isGoal(S state);

    //    => Calcular o custo de uma solução (lista de operadores)
    public double computePathCost(List<Action> path){
        double cost = 0;

        for (Action action :
                path) {
            cost += action.getCost();
        }
        return cost;
    }

    public S getInitialState() {
        return initialState;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }
}
