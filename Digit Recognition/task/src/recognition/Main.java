package recognition;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder inputLine = new StringBuilder();
        System.out.println("Input grid:");
        for(int i=0; i<5; i++) {
            inputLine.append(scanner.nextLine());
        }
        char[] inputChar = inputLine.toString().toCharArray();

        List<Integer> inputData = new ArrayList<>();
        for (char inp: inputChar) {
            inputData.add(inp=='X' ? 1 : 0);
        }
        NeuralNetwork network = new NeuralNetwork(10, 0);
        network.setInputData(inputData);
        network.setNeuronWeight(0, List.of(1, 1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, 1, 1), -1);
        network.setNeuronWeight(1, List.of(-1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1), 6);
        network.setNeuronWeight(2, List.of(1, 1, 1, -1, -1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1), 1);
        network.setNeuronWeight(3, List.of(1, 1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1), 0);
        network.setNeuronWeight(4, List.of(1, -1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, -1, -1, 1), 2);
        network.setNeuronWeight(5, List.of(1, 1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, 1, 1, 1), 0);
        network.setNeuronWeight(6, List.of(1, 1, 1, 1, -1, -1, 1, 1, 1, 1, -1, 1, 1, 1, 1), -1);
        network.setNeuronWeight(7, List.of(1, 1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1), 3);
        network.setNeuronWeight(8, List.of(1, 1, 1, 1, -1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1), -2);
        network.setNeuronWeight(9, List.of(1, 1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1), -1);

        int result = network.calc();

        System.out.println("This number is " + result);
    }
}