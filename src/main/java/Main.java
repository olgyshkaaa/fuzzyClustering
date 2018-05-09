import clustering.DataGenerator;
import clustering.DecisionFunction;
import clustering.EvklidDistance;
import clustering.UMatrix;
import lombok.Data;


/**
 * Main class
 */
@Data
public class Main {
    //количество кластеров
    private static final int clusterNumber = 6;
    //количество начальных точек
    private static final int dataNumber = 5;
    //разность данных
    private static final int dimension = 2;
    ///ограничение для генерации чисел (диапазон)
    private static final int dataRange = 100;

    //параметр останова
    private static final double eps = 0.00003;
    //экспоненциальный вес
    private static final int m = 2;


    public static void main(String[] args) {
        //матрица исходных данных
        double[][] data;
        //матрица с центрами кластеров
        double[][] centres;
        //матрица с расстояниями до центров
        double[][] distanceData;
        //матрица вероятности
        double[][] probabilityMatrix;
        //служебные переменные
        double[][] u;
        double[][] uPrev;
        double max = 100;
        int i = 0;

        //генерация матрицы исходных данных
        data = DataGenerator.generateData(dataNumber, dimension, dataRange);
        //генерация матрицы вероятности
        probabilityMatrix = DataGenerator.generateProbabilityMatrix(dataNumber, clusterNumber);

        /// centres = clustering.DataGenerator.generateCenters(dimension,clusterNumber,dataRange);


        uPrev = probabilityMatrix;
        while (max > eps && i < 10000000) {
            //генерация изначальных центров кластеров
            centres = UMatrix.calculateCenters(dataNumber, clusterNumber, dimension, m, data, uPrev);
            distanceData = EvklidDistance.calculateEvklidDistance(data, centres,
                    dimension, clusterNumber, dataNumber);
            u = UMatrix.calculateUMatrix(dataNumber, clusterNumber, m, distanceData);
            max = DecisionFunction.calculateDecisionFunction(dataNumber, clusterNumber, u, uPrev);
            uPrev = u;
            i++;

        }

        System.out.println(i);


    }
}
