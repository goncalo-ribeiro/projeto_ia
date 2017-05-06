package searchmethods;

import agent.State;
import java.util.List;
import utils.NodePriorityQueue;

public class UniformCostSearch extends GraphSearch<NodePriorityQueue> {

    public UniformCostSearch(){
        frontier = new NodePriorityQueue();
    }    
    
    // f = g
    //a fronteira é ordenada pelo valor f sendo que, no caso do
    //Uniform cost search é igual ao custo (g)
    /*
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        /*
        para cada sucessor:
            calcular o custo
            se a fronteira não contiver o sucessor
                se a lista de nós explorados não contiver o sucessor
                    adicionar o sucessor à fronteira (com o custo)
            senão (se a fronteira já contiver o sucessor)
                se o custo do nó que está na fronteira for superior
                    remover o nó que está na fronteira
                    colocar o sucessor na fronteira

        *//*
        for (State s :
                successors) {
            double g = parent.getG() + s.getAction().getCost();
            if (frontier.containsState(s)) {
                if (!explored.contains(s)) {
                    //f é o valor utilizado para ordenar os nós na NodePriorityQueue: quanto menor o seu valor maior prioridade terá o nó
                    //Uniform Cost => f = g (custo)
                    //GreedyBestfirstSearch => f = h (heuristica)
                    //AStarSearch => f = g + h (custo + heuristica)
                    Node node = new Node(s, parent, g, g);
                    frontier.add(node);
                } else {
                    double gFrontier = frontier.getNode(s).getG();

                    if (gFrontier > g) {
                        frontier.removeNode(s);

                        Node node = new Node(s, parent, g, g);
                        frontier.add(node);
                    }
                }
            }
        }
    }*/


    @Override
    public String toString() {
        return "Uniform cost search";
    }

    @Override
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
                    frontier.add(new Node(successor, parent, g, g));
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
}
