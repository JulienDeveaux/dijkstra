import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static int degreMoyen = 5;
    public static int nodeCount = 1000;

    protected static String styleSheet =
            "node {" +
                    "   size: 10px, 10px;" +
                    "   text-size: 15px;" +
                    "	fill-color: grey, red;" +
                    "   text-background-mode: rounded-box;" +
                    "   text-background-color: orange;" +
                    "   text-alignment: above;" +
                    "   fill-mode: gradient-radial;" +
                    "}" +
                    "edge {" +
                    "   text-alignment: above;" +
                    "   text-background-color: orange;" +
                    "   text-background-mode: rounded-box;" +
                    "   text-size: 20px;" +
                    "}";

    public static void main(String[] args) {
        /* GENERATION DU GRAPH ALEATOIRE */
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("RandomGraph");
        graph.setAttribute("ui.stylesheet", styleSheet);

        Generator gen = new RandomGenerator(degreMoyen);
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < nodeCount; i++) {
            gen.nextEvents();
        }
        gen.end();

        Random rand = new Random(20);
        for (int i = 0; i < graph.getEdgeCount(); i++) {
            int min = 1;
            int max = 10;
            int r = rand.nextInt(max - min + 1) + min;
            graph.getEdge(i).setAttribute("p", r);                  // Ajout de poids aléatoires positifs au liens
            graph.getEdge(i).setAttribute("ui.label", "E" + r);     // E pour edge
        }
        for (int i = 0; i < graph.getNodeCount(); i++) {
            graph.getNode(i).setAttribute("ui.label", "N" + i);     // N pour node
        }
        /* FIN GENERATION DU GRAPH ALEATOIRE */
        /* CALCUL DU CHEMIN LE PLUS COURT A PARTIR DU NOEUD 0 */
        long startTimePerso = System.nanoTime();
        Dijkstra comp = new Dijkstra();
        comp.init(graph);
        comp.setSource(graph.getNode(0));
        comp.compute();
        long estimatedTimePerso = System.nanoTime() - startTimePerso;
        /* FIN DU CALCUL DE MON DIJKSTRA */
        long startTimeGraphStream = System.nanoTime();
        org.graphstream.algorithm.Dijkstra d = new org.graphstream.algorithm.Dijkstra(org.graphstream.algorithm.Dijkstra.Element.EDGE, null, "p");
        d.init(graph);
        d.setSource(graph.getNode(0));
        d.compute();
        long estimatedTimeGraphStream = System.nanoTime() - startTimeGraphStream;
        /* FIN DU CALCUL DU DIJKSTRA DE GRAPHSTREAM */

        System.out.println("\nComparatif des temps d'exécutions pour " + nodeCount + " noeuds de degré " + degreMoyen + " : ");
        System.out.println("mon implémentation : " + TimeUnit.MILLISECONDS.convert(estimatedTimePerso, TimeUnit.NANOSECONDS) + " milliseconds");
        System.out.println("implémentation graphStream : " + TimeUnit.MILLISECONDS.convert(estimatedTimeGraphStream, TimeUnit.NANOSECONDS) + " milliseconds");
        //graph.display();
    }
}
