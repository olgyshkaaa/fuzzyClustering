import static java.lang.Math.sqrt;

/**
 *Вычисление евклидового расстояния
 */
public class EvklidDistance {
    private double distanceData[][];

    public double[][] getDistanceData() {
        return distanceData;
    }

    public void calculateEvklidDistance(double data[][], double centers[][], int dimension, int clusterNumber, int dataNumber){
        distanceData = new double[clusterNumber][dataNumber];
        double sum = 0;
        for (int i = 0; i < clusterNumber; i++) {
            for (int j = 0; j < dataNumber;  j++) {
                sum = 0;
                for (int k = 0; k <dimension ; k++) {
                    sum += (data[k][j] - centers[k][i])*(data[k][j] - centers[k][i]);
                }
                distanceData[i][j] = sqrt(sum);
            }
        }
        
    }
}
