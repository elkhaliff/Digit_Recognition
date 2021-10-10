package recognition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PartData{
    private final double[] data;

    public PartData(File fileName) {
        final int DATA_COUNT = 785;
        data = new double[DATA_COUNT];
        try (Scanner scanData = new Scanner(fileName)) {
            for (var i = 0; i < data.length - 1; i++) {
                data[i] = Double.parseDouble(scanData.next()) / 255;
            }
//            data[DATA_COUNT-1] = Double.parseDouble(scanData.next());
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + fileName);
        }
    }

    public double[] getData() {
        return data;
    }
}