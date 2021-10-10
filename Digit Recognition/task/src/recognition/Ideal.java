package recognition;

public class Ideal {
    private static final double[][] outputs = new double[][]{
            new double[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0
            new double[]{0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
            new double[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 2
            new double[]{0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, // 3
            new double[]{0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, // 4
            new double[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 5
            new double[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // 6
            new double[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // 7
            new double[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, // 8
            new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 9
    };

    public static double[][] getOutputs() {
        return outputs;
    }
}
