import generator.DataGenerator;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        double eps = 0.00000000000000000003;
        int m = 2;
        double u[][];
        double centres[][];
        double[][] uPrev;
        double max = 100;
        int i = 0;
        DataGenerator data = new DataGenerator();
        data.generateProbabilityMatrix();
        DecisionFunction decisionFunction = new DecisionFunction();
        EvklidDistance evklidDistance = new EvklidDistance();
        Umatrix umatrix = new Umatrix();
        centres = data.getCenters();

        data.generateData();
        data.generateCenters();
        uPrev =data.getProbabilityMatrix();
        while(max > eps && i < 100) {
            evklidDistance.calculateEvklidDistance(data.getData(), centres,
                    data.getDimension(), data.getClusterNumber(), data.getDataNumber());
           umatrix.calculateUmatrix(data.getDataNumber(), data.getClusterNumber(),m, evklidDistance.getDistanceData());
            u = umatrix.getProbabilityMatrix();
            max = decisionFunction.calculateDecisionFunction(data.getDataNumber(), data.getClusterNumber(), u, uPrev);
            uPrev = u;
            umatrix.calculateCenters(data.getDataNumber(), data.getClusterNumber(), data.getDimension(), m, data.getData());
            centres = umatrix.getCentresMatrix();
            i++;
        }

        System.out.println(i);


    }
}
