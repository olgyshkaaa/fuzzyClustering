package clustering;

import java.util.Random;

/**
 * Генерация исходной матрицы
 */
public class DataGenerator {


    public static double[][] generateProbabilityMatrix(int dataNumber, int clusterNumber) {
        double sum;
        double[][] probabilityMatrix = new double[clusterNumber][dataNumber];
        final Random random = new Random();
        for (int j = 0; j < dataNumber; j++) {
            sum = 0;
            for (int i = 0; i < clusterNumber; i++) {
                probabilityMatrix[i][j] = random.nextInt(100);
                sum += probabilityMatrix[i][j];
            }
            for (int i = 0; i < clusterNumber; i++) {
                probabilityMatrix[i][j] /= sum;
            }
        }
        return probabilityMatrix;
    }

    public static double[][] generateData(int dataNumber, int dimension, int dataRange) {
        double[][] data = new double[dimension][dataNumber];
        final Random random = new Random();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dataNumber; j++) {
                data[i][j] = random.nextInt(dataRange);
            }
        }
        return data;
    }

    public static int[][] generatePeopleData(int dataNumber, int dataRange) {
        int[][] data = new int[2][dataNumber];
        final Random random = new Random();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < dataNumber; j++) {
                data[i][j] = random.nextInt(dataRange);
            }
        }
        return data;
    }

    public static double[][] generateCenters(int dimension, int clusterNumber, int dataRange) {
        final Random random = new Random();
        double[][] centers = new double[dimension][clusterNumber];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < clusterNumber; j++) {
                centers[i][j] = random.nextInt(dataRange);
            }
        }
        return centers;
    }

    public void show(int dataNumber, int clusterNumber, double[][] probabilityMatrix) {
        for (int i = 0; i < clusterNumber; i++) {
            for (int j = 0; j < dataNumber; j++) {
                System.out.println(probabilityMatrix[i][j]);
            }
            System.out.println("\n");
        }
    }

    public static FinalData[] generateFinalData(double[][] probabilityMatrix, int[][] peopleMatrix, int clusterNumber, int dataNumber) {
        FinalData[] finalData = new FinalData[clusterNumber];
        for (int j = 0; j < dataNumber; j++) {
            double max = probabilityMatrix[0][j];
            int maxIndex = 0;
            for (int i = 0; i < clusterNumber; i++) {
                if (probabilityMatrix[i][j] > max) {
                    max = probabilityMatrix[i][j];
                    maxIndex = i;
                }
                if (finalData[maxIndex] == null) {
                    finalData[maxIndex] = new FinalData();
                }
                finalData[maxIndex].getWho().add(peopleMatrix[0][j]);
                finalData[maxIndex].getWhom().add(peopleMatrix[1][j]);
            }

        }
        return finalData;
    }

}
