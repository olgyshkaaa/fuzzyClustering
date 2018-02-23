/**
 * Пересчет
 */
public class Umatrix {
    private double[][] probabilityMatrix = new double[20][20]; //todo:исправить;

    public double[][] getProbabilityMatrix() {
        return probabilityMatrix;
    }

    public void setProbabilityMatrix(double[][] probabilityMatrix) {
        this.probabilityMatrix = probabilityMatrix;
    }

    public void calculateUmatrix (int dataNumber, int clusterNumber, int m, double  distanceData[][]){
        for (int j = 0; j < dataNumber ; j++) {
            for (int i = 0; i < clusterNumber ; i++) {
                probabilityMatrix[i][j] = Math.pow((1/distanceData[i][j]),(2/(m-1)));
            }
        }
        normalize(dataNumber,clusterNumber);
    }

    public void normalize (int dataNumber, int clusterNumber){
        double sum;
        for (int j = 0; j < dataNumber ; j++) {
            sum = 0;
            for (int i = 0; i < clusterNumber ; i++) {
                sum += probabilityMatrix[clusterNumber][dataNumber];
            }
            for (int i = 0; i < clusterNumber ; i++) {
                probabilityMatrix[i][j] /= sum;
            }

        }
    }
}
