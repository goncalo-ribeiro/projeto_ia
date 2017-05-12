package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class IDAStarSearch extends InformedSearch {
    private double limit;
    private double newLimit;    

    @Override
    public Solution search(Problem problem) {
        statistics.reset();
        stopped = false;
        this.heuristic = problem.getHeuristic();

        limit = heuristic.compute(problem.getInitialState());
        Solution solution = null;
        do {
            solution = graphSearch(problem);
        } while (solution == null);

        return graphSearch(problem);
    }

    @Override
    protected Solution graphSearch(Problem problem) {
        newLimit = Double.POSITIVE_INFINITY;

        frontier.clear();
        frontier.add(new Node(problem.getInitialState()));

        while (!frontier.isEmpty() && !stopped) {
            Node n = frontier.poll();
            if (problem.isGoal(n.getState())) {
                return new Solution(problem, n);
            }
            List<State> successors = problem.executeActions(n.getState());
            addSuccessorsToFrontier(successors, n);
            computeStatistics(successors.size());
        }

        limit = newLimit;

        return null;
    }

    @Override
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        for (State successor :
                successors) {
            double g = parent.getG() + successor.getAction().getCost();
            double h = heuristic.compute(successor);
            double f = g + h;
            if (!frontier.containsState((successor))) {

                if (f <= limit) {
                    Node node = new Node(successor, parent, g, f);
                    if (!node.isCycle()) {
                        frontier.add(node);
                    }
                } else {
                    newLimit = Math.min(newLimit, f);
                }
            } else {
                if (frontier.getNode(successor).getG() > g) {
                    frontier.removeNode(successor);
                    Node node = new Node(successor);
                    frontier.add(node);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "IDA* search";
    }
}
