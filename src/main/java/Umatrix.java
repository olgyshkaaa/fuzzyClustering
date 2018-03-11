/**
 * Пересчет
 */
public class Umatrix {
    private double[][]probabilityMatrix;
    private double[][] centresMatrix;

    public double[][] getCentresMatrix() {
        return centresMatrix;
    }

    public void setCentresMatrix(double[][] centresMatrix) {
        this.centresMatrix = centresMatrix;
    }

    public double[][] getProbabilityMatrix() {
        return probabilityMatrix;
    }

    public void setProbabilityMatrix(double[][] probabilityMatrix) {
        this.probabilityMatrix = probabilityMatrix;
    }

    public void calculateUmatrix (int dataNumber, int clusterNumber, int m, double  distanceData[][]){
        probabilityMatrix = new double[clusterNumber][dataNumber];
        for (int j = 0; j < dataNumber ; j++) {
            for (int i = 0; i < clusterNumber ; i++) {
                probabilityMatrix[i][j] = Math.pow((normalize(dataNumber, clusterNumber, distanceData, j) / distanceData[i][j]), (2 / (m - 1)));
            }
        }
    }

    public double normalize(int dataNumber, int clusterNumber, double[][] distanceData, int currentNode) {

        double sum;
            sum = 0;
            for (int i = 0; i < clusterNumber ; i++) {
                sum += distanceData[i][currentNode];
            }
        return sum;
    }

    public void calculateCenters(int dataNumber, int clusterNumber, int dimension, int m, double[][] data) {
        centresMatrix = new double[dimension][clusterNumber];
        for (int i = 0; i < clusterNumber; i++) {
            for (int j = 0; j < dataNumber; j++) {
                for (int k = 0; k < dimension; k++) {
                    centresMatrix[k][i] = Math.pow(probabilityMatrix[i][j], m) * data[k][i] / dataNumber;
                }

            }
        }
    }
}
