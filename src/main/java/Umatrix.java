/**
 * Пересчет
 */
public class Umatrix {
    private double[][]probabilityMatrix;

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
                probabilityMatrix[i][j] += Math.pow((1/distanceData[i][j]),(2/(m-1)));
            }
        }
        normalize(dataNumber,clusterNumber, probabilityMatrix);
    }

    public void normalize(int dataNumber, int clusterNumber, double[][] probabilityMatrix){

        double sum;
        for (int j = 0; j < dataNumber ; j++) {
            sum = 0;
            for (int i = 0; i < clusterNumber ; i++) {
                sum += probabilityMatrix[i][j];
            }
            for (int i = 0; i < clusterNumber ; i++) {
                probabilityMatrix[i][j] /= sum;
            }

        }
    }
}
