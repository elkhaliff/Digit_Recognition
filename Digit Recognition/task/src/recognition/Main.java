package recognition;
import java.io.IOException;
import java.util.*;

public class Main {
    private static void println(String string) { System.out.println(string); }
    private static void print(String string) { System.out.print(string); }

    private static String getString(String input) {
        Scanner scanner = new Scanner(System.in);
        print(input);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        final String FILE_NAME = "D:\\network.data";
        NeuralNetwork network = new NeuralNetwork();

        int choice;
//        do {
            choice = Integer.parseInt(getString("1. Learn the network\n2. Guess a number\nYour choice: "));

            if (choice == 1) {
                var strArr = getString("Enter the sizes of the layers: ").trim().split("\\s+");
                var neuronsFromLayers = new int[strArr.length];
                for (int l = 0; l < neuronsFromLayers.length; l++) {
                    neuronsFromLayers[l] = Integer.parseInt(strArr[l]);
                }

                println("Learning...");
                network.initNetwork(neuronsFromLayers);
                network.train(1000, 0.5);
                try {
                    SerializationUtils.serialize(network, FILE_NAME);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                println("Done! Saved to the file.");
            } else if (choice == 2) {
                try {
                    network = (NeuralNetwork) SerializationUtils.deserialize(FILE_NAME);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                var scanInputData = new Scanner(System.in).useDelimiter("\\s*");
                println("Input grid:");
                var inputData = new double[15];
                for (var j = 0; j < inputData.length; j++) {
                    inputData[j] = scanInputData.next().equals("X") ? 1 : 0;
                }
                scanInputData.close();

                network.setInputData(inputData);
                System.out.println("This number is " + network.recognition());
//            } else if (choice == 3) {
//                network.train(1000, 0.5);
//                try {
//                    SerializationUtils.serialize(network, FILE_NAME);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                println("Done! Saved to the file.");
            }
//        } while (choice != 4);
    }
}