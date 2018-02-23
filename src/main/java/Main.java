import generator.DataGenerator;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        double eps = 0.00000003;
        int m = 3;
        double u[][];
        double[][] uPrev;
        double max = 1000;
        int i = 0;
        DataGenerator data = new DataGenerator();
        data.generateProbabilityMatrix();
        DecisionFunction decisionFunction = new DecisionFunction();
        EvklidDistance evklidDistance = new EvklidDistance();
        Umatrix umatrix = new Umatrix();


        data.generateData();
        data.generateCenters();
        uPrev =data.getProbabilityMatrix();
        while(max > eps && i < 100) {
            evklidDistance.calculateEvklidDistance(data.getData(), data.getCenters(),
                    data.getDimension(), data.getClusterNumber(), data.getDataNumber());
            umatrix.normalize(data.getDataNumber(), data.getClusterNumber());
            u = umatrix.getProbabilityMatrix();
            max = decisionFunction.calculateDecisionFunction(data.getDataNumber(), data.getClusterNumber(), u, uPrev);
            uPrev = u;
            i++;
        }




    }
}
