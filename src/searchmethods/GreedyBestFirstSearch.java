package searchmethods;

import agent.State;
import java.util.List;

public class GreedyBestFirstSearch extends InformedSearch {

    //f = h
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {

// para cada sucessor
        for (State successor : successors) {
            // calcular o custo
            double g = parent.getG() + successor.getAction().getCost();

            // se a fronteira nao contiver o sucessor
            if (!frontier.containsState(successor)){

                // se a lista de nos explorados nao contiver o sucessor
                if (!explored.contains(successor)){

                    // adicionar o sucessor a fronteira (com o custo)
                    frontier.add(new Node(successor, parent, g, heuristic.compute(successor)));
                }
            }
            // senao (se ja tiver o sucessor)
            else {
                double gFrontier = frontier.getNode(successor).getG();

                // se o custo do no que esta na fronteira for superior
                if (gFrontier > g){

                    // remover o no que esta na fronteira
                    frontier.removeNode(successor);

                    // colocar o sucessor na fronteira
                    explored.add(successor);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Greedy best first search";
    }
}