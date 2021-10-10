package recognition;
import java.io.File;
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
        final String SERIAL_NETWORK_FILE_NAME = "d:\\test\\network.data";
        final String SERIAL_DATA_FILE_NAME = "d:\\test\\testdata.data";
        final String DATA_FILE_DIR = "d:\\test\\data";
        final String CHOICE = "1. Learn the network\n2. Guess all the numbers\n3. Guess number from text file\nYour choice: ";
        NeuralNetwork network = new NeuralNetwork();
        BigData bigData = new BigData();

        int choice;
//        do {
            choice = Integer.parseInt(getString(CHOICE));

            if (choice == 9) {
                // Get and serialize data files
                bigData.readDir(DATA_FILE_DIR);
                println("Get data from 70000 files");
                try {
                    SerializationUtils.serialize(bigData, SERIAL_DATA_FILE_NAME);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (choice == 0) {
                // Deserialize bigdata
                try {
                    bigData = (BigData) SerializationUtils.deserialize(SERIAL_DATA_FILE_NAME);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                println("Deserialize bigdata " + bigData.getData().length);
            } else if (choice == 1) {
                // Learn the network
                var strArr = getString("Enter the sizes of the layers: ").trim().split("\\s+");
                var neuronsFromLayers = new int[strArr.length];
                for (int l = 0; l < neuronsFromLayers.length; l++) {
                    neuronsFromLayers[l] = Integer.parseInt(strArr[l]);
                }

                println("Learning...");
                network.initNetwork(neuronsFromLayers);
                for (int i = 0; i < 10; i++) {
                    network.train(bigData.getData(), 1000, 0.5);
                }
                try {
                    SerializationUtils.serialize(network, SERIAL_NETWORK_FILE_NAME);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                println("Done! Saved to the file.");
            } else if (choice == 2) {
                // Guess all the numbers
                try {
                    network = (NeuralNetwork) SerializationUtils.deserialize(SERIAL_NETWORK_FILE_NAME);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                network.guessTestData(bigData.getAllData());
            } else if (choice == 3) {
                // Guess number from text file
                try {
                    network = (NeuralNetwork) SerializationUtils.deserialize(SERIAL_NETWORK_FILE_NAME);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                String fileName = getString("Enter filename: ");
                File file = new File(fileName);
                PartData partData = new PartData(file);
                network.setInputData(partData.getData());
                println("This number is " + network.recognition(true));
            }
            else if (choice == 4) {
                try {
                    network = (NeuralNetwork) SerializationUtils.deserialize(SERIAL_NETWORK_FILE_NAME);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    network.train(bigData.getData(), 100, 0.5);
                    try {
                        SerializationUtils.serialize(network, SERIAL_NETWORK_FILE_NAME);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                println("Done! Saved to the file.");
            }
//        } while (choice != 5);
    }
}