package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
import java.util.List;
import utils.NodeLinkedList;

public class DepthFirstSearch extends GraphSearch<NodeLinkedList> {

    public DepthFirstSearch() {
        frontier = new NodeLinkedList();
    }

    //Graph Search without explored list
    @Override
    protected Solution graphSearch(Problem problem) {
        frontier.clear();
        frontier.add(new Node(problem.getInitialState()));

        while (!frontier.isEmpty()) {
            Node node = frontier.removeFirst();
            if (problem.isGoal(node.getState())) {
                return new Solution (problem, node);
            }

            List<State> successors = problem.executeActions(node.getState());
            addSuccessorsToFrontier(successors, node);
        }

        return null;
    }

    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        for (State s :
                successors) {
            if (!frontier.containsState(s)) {
                Node node = new Node(s, parent);
                if (!node.isCycle()) {
                    frontier.addFirst(node);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Depth first search";
    }
}
