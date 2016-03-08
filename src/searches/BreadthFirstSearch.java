package searches;

import graph.GraphNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : First created by Hung Hoang with code by Hung Hoang
 * @date : 06/02/16, last edited by Hung Hoang on 25/02/16
 */
public class BreadthFirstSearch {
    /**
     * Finds a path from a start node to the end node using BFS, this utilises a queue which is just a linked list
     * The returned path should not include the start node.
     *
     * @param startNode node search starts from
     * @param endNode   node search terminates with, the goal node, usually player's base
     * @return path from start to goal node
     */
    public static List<GraphNode> findPathFrom(GraphNode startNode, GraphNode endNode, boolean visit) {

        LinkedList<GraphNode> frontier = new LinkedList<>();
        ArrayList<GraphNode> visited = new ArrayList<>();
        LinkedHashMap<GraphNode, GraphNode> possiblePath = new LinkedHashMap<>();
        ArrayList<GraphNode> path = new ArrayList<>();

        GraphNode current;
        GraphNode parent;

        frontier.add(startNode);

        while (!frontier.isEmpty()) {

            current = frontier.poll();
            if (!visited.contains(current) && (current.getBlockade() == null || current.getBlockade().isBreakable())) {

                if (current.equals(endNode)) {

                    while (possiblePath.keySet().contains(current)) {

                        path.add(current);
                        parent = possiblePath.get(current);
                        current = parent;
                    }

                    Collections.reverse(path);
                    if (visit) {
                        visited.add(endNode);
                        return visited;
                    } else {
                        return path;
                    }
                } else {
                    visited.add(current);
                    frontier.addAll(current.getSuccessors());
                    for (GraphNode successor : current.getSuccessors()) {
                        if (!possiblePath.keySet().contains(successor) && !possiblePath.containsValue(successor))
                            possiblePath.put(successor, current);
                    }
                }
            }
        }
        return null;
    }
}
