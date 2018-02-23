import static java.lang.Math.abs;

/**
 * Created by Olga on 2/23/2018.
 */
public class DecisionFunction {
    double sum = 0;

    public double calculateDecisionFunction(int dataNumber, int clusterNumber, double[][] u, double[][] uPrev) {
        double max = abs(u[0][0] - uPrev[0][0]);
        for (int i = 0; i < clusterNumber; i++) {
            for (int j = 0; j < dataNumber; j++) {
                if (abs(u[i][j] - uPrev[i][j]) > max) {
                    max = abs(u[i][j] - uPrev[i][j]);
                }
            }
        }
        return max;
    }
}
