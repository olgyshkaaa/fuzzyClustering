import clustering.*;
import krizeji1.girvan_newman.GirvanNewmanClusterer;
import org.gephi.clustering.api.Cluster;
import org.gephi.graph.api.*;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;


/**
 * Main class
 */
public class Main {
    //количество кластеров
    private static final int clusterNumber = 3;
    //количество начальных точек
    private static final int dataNumber = 100;
    //разность данных
    private static final int dimension = 2;
    ///ограничение для генерации чисел (диапазон)
    private static final int dataRange = 100;

    //параметр останова
    private static final double eps = 0.00003;
    //экспоненциальный вес
    private static final int m = 2;


    public static void main(String[] args) {
        //матрица исходных данных
        double[][] data;
        //матрица с номерами абонентов
        int[][] people;
        //матрица с центрами кластеров
        double[][] centres;
        //матрица с расстояниями до центров
        double[][] distanceData;
        //матрица вероятности
        double[][] probabilityMatrix;
        //служебные переменные
        double[][] u;
        double[][] uPrev;
        double max = 100;
        int i = 0;

        //генерация матрицы исходных данных
        data = DataGenerator.generateData(dataNumber, dimension, dataRange);
        //генерация матрицы вероятности
        probabilityMatrix = DataGenerator.generateProbabilityMatrix(dataNumber, clusterNumber);
        //генерация связи абонентов
        people = DataGenerator.generatePeopleData(dataNumber, dataRange);

        /// centres = clustering.DataGenerator.generateCenters(dimension,clusterNumber,dataRange);


        uPrev = probabilityMatrix;
        while (max > eps && i < 10000000) {
            //генерация изначальных центров кластеров
            centres = UMatrix.calculateCenters(dataNumber, clusterNumber, dimension, m, data, uPrev);
            distanceData = EvklidDistance.calculateEvklidDistance(data, centres,
                    dimension, clusterNumber, dataNumber);
            u = UMatrix.calculateUMatrix(dataNumber, clusterNumber, m, distanceData);
            max = DecisionFunction.calculateDecisionFunction(dataNumber, clusterNumber, u, uPrev);
            uPrev = u;
            i++;

        }

        FinalData[] finalData = DataGenerator.generateFinalData(uPrev, people, clusterNumber, dataNumber);

        int index = 0;
        for (FinalData cluster : finalData) {
        Lookup lookup = Lookup.getDefault();
        ProjectController pc = lookup.lookup(ProjectController.class);
        pc.newProject();
        @SuppressWarnings("unused")
        Workspace workspace = pc.getCurrentWorkspace();
        GraphController controller = Lookup.getDefault().lookup(GraphController.class);
        GraphModel model = controller.getModel();
        UndirectedGraph graph = model.getUndirectedGraph();
            index ++;
            if(cluster == null) {
                System.out.println("Cluster" + index + " is empty");
                return;
            }
            else {
                System.out.println("Cluster" + index + ":");
            }
            for (int j = 0; j < cluster.getWho().size() && j < cluster.getWhom().size(); j++) {
                Node a = null;
                Node b = null;
                if (!contains(graph, cluster.getWho().get(j))) {
                    a = createNode(model, graph, cluster.getWho().get(j));
                } else {
                    a = getNode(graph, cluster.getWho().get(j));
                }
                if (!contains(graph, cluster.getWhom().get(j))) {
                    b = createNode(model, graph, cluster.getWhom().get(j));
                } else {
                    b = getNode(graph, cluster.getWhom().get(j));
                }

                createEdge(model, graph, a, b, 2f);
            }

            // When
            GirvanNewmanClusterer clusterer = new GirvanNewmanClusterer();
            clusterer.setPreferredNumberOfClusters(3);
            clusterer.execute(model);
            // Then
            Cluster[] clusters = clusterer.getClusters();
        }



    }

    private static Node createNode(GraphModel model, UndirectedGraph graph, Integer id) {
        Node node = model.factory().newNode(id.toString());
        graph.addNode(node);
        return node;
    }

    private static void createEdge(GraphModel model, UndirectedGraph graph, Node a, Node b, float weight) {
        Edge edge = model.factory().newEdge(a, b, weight, false);
        graph.addEdge(edge);
    }

    private static boolean contains(UndirectedGraph graph, Integer id) {
        for (Node node : graph.getNodes()) {
            if (node.getId() == id) {
                return true;
            }

        }
        return false;
    }

    private static Node getNode(UndirectedGraph graph, Integer id) {
        for (Node node : graph.getNodes()) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }

}
