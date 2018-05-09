package clustering;

/**
 * Пересчет
 */
public class UMatrix {

    //перерасчет матрицы вероятности
    public static double[][] calculateUMatrix(int pointNumber, int clusterNumber, int m, double distanceData[][]) {
        double[][] probabilityMatrix = new double[clusterNumber][pointNumber];
        double sum;
        for (int j = 0; j < pointNumber; j++) {
            for (int i = 0; i < clusterNumber; i++) {
                sum = 0;
                for (int k = 0; k < clusterNumber; k++) {
                    sum += Math.pow(((float) distanceData[i][j] / distanceData[k][j]), (2 / (m - 1)));
                }
                probabilityMatrix[i][j] = 1 / sum;
            }
        }
        return probabilityMatrix;
    }

    public static double[][] calculateCenters(int dataNumber, int clusterNumber, int dimension, int m, double[][] data, double[][] probabilityMatrix) {
        double[][] centresMatrix = new double[dimension][clusterNumber];
        double numerator;
        double denominator;
        for (int k = 0; k < dimension; k++) {
            for (int i = 0; i < clusterNumber; i++) {
                numerator = 0;
                denominator = 0;
                for (int j = 0; j < dataNumber; j++) {
                    numerator = Math.pow(probabilityMatrix[i][j], m) * data[k][j];
                    denominator = Math.pow(probabilityMatrix[i][j], m);
                }
                centresMatrix[k][i] = (float) numerator / denominator;
            }
        }
        return centresMatrix;
    }
}
