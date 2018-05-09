package clustering;

import static java.lang.Math.sqrt;

/**
 *Вычисление евклидового расстояния
 */
public class EvklidDistance {

    public static double[][] calculateEvklidDistance(double data[][], double centers[][], int dimension, int clusterNumber, int dataNumber) {
        double[][] distanceData = new double[clusterNumber][dataNumber];
        double sum = 0;
        for (int i = 0; i < clusterNumber; i++) {
            for (int j = 0; j < dataNumber; j++) {
                sum = 0;
                for (int k = 0; k <dimension ; k++) {
                    sum += (data[k][j] - centers[k][i])*(data[k][j] - centers[k][i]);
                }
                distanceData[i][j] = sqrt(sum);
            }
        }
        return distanceData;

    }
}
