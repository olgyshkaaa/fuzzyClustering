package clustering;

import static java.lang.Math.abs;

/**
 * Класс, который выносит реешение о продолжении расчетов
 */
public class DecisionFunction {


    public static double calculateDecisionFunction(int dataNumber, int clusterNumber, double[][] u, double[][] uPrev) {
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
