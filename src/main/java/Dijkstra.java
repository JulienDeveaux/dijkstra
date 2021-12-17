import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;

import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra implements Algorithm {
    Graph graph;
    private Node source;
    PriorityQueue<Node> priorityQueue;

    /* Initialisation des variables pour l'algorithme */
    public void init(Graph graph) {
        this.graph = graph;

        for(int i = 0; i < graph.getNodeCount(); i++) {
            graph.getNode(i).setAttribute("p", 1);
        }

        // créé une file de priorité ordonnée
        priorityQueue = new PriorityQueue<>(graph.getNodeCount(), (n1, n2) -> Integer.compare((int) n2.getAttribute("p"), (int) n1.getAttribute("p")));
        for(int i=  0; i < graph.getNodeCount(); i++) {
            graph.getNode(i).setAttribute("dist", Integer.MAX_VALUE);
            graph.getNode(i).setAttribute("parent", (Object) null);
        }
    }

    /* calcul des chemins le plus court */
    public void compute() {
        Node u;
        if(source != null) {
            source.setAttribute("dist", 0);
            priorityQueue.add(source);
            while(!priorityQueue.isEmpty()) {
                u = priorityQueue.poll();
                for(int i = 0; i < u.getDegree(); i++) {
                    Node v = u.getEdge(i).getOpposite(u);
                    int uv = (int)v.getEdgeBetween(u).getAttribute("p");
                    if((int)v.getAttribute("dist") > (int)u.getAttribute("dist") + uv) {
                        v.setAttribute("dist", (int)u.getAttribute("dist") + uv);
                        v.setAttribute("parent", u);
                        priorityQueue.add(v);
                    }
                }
            }
        } else {
            throw new ArithmeticException("Source not specified");
        }
    }

    /* Set la source de l'algorithme */
    /* Impératif au fonctionnement */
    public void setSource(Node source) {
        this.source = source;
    }

    /* Return le chemin de la source vers le noeud */
    public Path getPath(Node n) {
        Path path = new Path();
        path.setRoot(source);
        if(n == source) {
            return path;
        }
        Node parent = (Node)n.getAttribute("parent");
        Node current = n;
        Stack<Edge> stack = new Stack<>();
        while(current != source && parent != null) {            // si parent = null, le noeud est isolé et n'a donc pas de chemin vers la source
            Edge e = parent.getEdgeBetween(current);
            stack.push(e);
            current = parent;
            parent = (Node)current.getAttribute("parent");
        }
        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return path;
    }

    /* Return la somme des poids du chemin */
    public double getPathLength(Node n) {
        return (double) ((Integer)n.getAttribute("dist"));
    }
}
