package recognition;

import java.io.File;
import java.io.Serializable;

public class BigData implements Serializable {
    final private double[][] allData;
    final private int ALL_DATA_COUNT = 70000;
    final private int PART_FROM_ALL = 7000;
    final private int TEST_DATA_COUNT = 10000;
    final private int DATA_COUNT = 785;

    public BigData() {
        allData = new double[ALL_DATA_COUNT][DATA_COUNT];
    }

    public void readDir(String dirName) {
        File dir = new File(dirName);
        File[] listFiles = dir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            PartData part = new PartData(listFiles[i]);
            allData[i] = part.getData();
            if (i % 10000 == 0) System.out.println(i + " " + allData[i][DATA_COUNT-1]);
        }

    }

    public double[][] getData() {
        double[][] out = new double[TEST_DATA_COUNT][DATA_COUNT];
        int ind;
        for (int i = 0; i < TEST_DATA_COUNT; i++) {
            ind = (int)(Math.random() * ALL_DATA_COUNT - 1);
            out[i] = allData[ind];
        }
        return out;
    }

    public double[][] getAllData() {
        return allData;
    }
}
