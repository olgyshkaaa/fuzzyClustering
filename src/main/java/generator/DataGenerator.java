package generator;

import java.util.Random;

/**
 * Генерация исходной матрицы
 */
public class DataGenerator {
    private  Integer clusterNumber =20;
    private  Integer dataNumber = 1000000;
    private double sum;
    private Integer max = 1000;
    private Integer dimension = 2;

    private double[][] probabilityMatrix = new double [clusterNumber][dataNumber];
    private double[][] data = new double [dimension][dataNumber];
    private double[][] centers = new double[dimension][clusterNumber];

    public double[][] getProbabilityMatrix() {
        return probabilityMatrix;
    }

    public void setProbabilityMatrix(double[][] probabilityMatrix) {
        this.probabilityMatrix = probabilityMatrix;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public double[][] getCenters() {
        return centers;
    }

    public void setCenters(double[][] centers) {
        this.centers = centers;
    }

    public Integer getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(Integer clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    public Integer getDataNumber() {
        return dataNumber;
    }

    public void setDataNumber(Integer dataNumber) {
        this.dataNumber = dataNumber;
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public void generateProbabilityMatrix(){
        final Random random = new Random();
        for (int j = 0; j < dataNumber ; j++) {
            sum = 0;
            for (int i = 0; i < clusterNumber ; i++) {
                probabilityMatrix[i][j] = random.nextInt(100);
                sum += probabilityMatrix[i][j];
            }
            for (int i = 0; i < clusterNumber ; i++) {
                probabilityMatrix[i][j] /= sum;
            }

        }
    }

    public void generateData(){
        final Random random = new Random();
        for (int i = 0; i < dimension ; i++) {
            for (int j = 0; j < dataNumber; j++) {
                data[i][j] = random.nextInt(max);
            }
        }
    }

    public void generateCenters(){
        final Random random = new Random();
        for (int i = 0; i < dimension ; i++) {
            for (int j = 0; j < clusterNumber; j++) {
                centers[i][j] = random.nextInt(max);
            }
        }
    }

    public void show(){
        for (int i = 0; i < clusterNumber; i++) {
            for (int j = 0; j < dataNumber ; j++) {
                System.out.println(probabilityMatrix[i][j]);
            }
            System.out.println("\n");
        }
    }



}
