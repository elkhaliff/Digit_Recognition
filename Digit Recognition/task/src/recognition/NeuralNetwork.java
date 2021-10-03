package recognition;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private final List<Layer> layers = new ArrayList<>();

    private final int middleLayerCount;
    private final int middleLayerNeuronCount;
    private int inputNeurons;
    private final int outputNeurons;

    public NeuralNetwork(int outputNeurons, int middleLayerCount) {
        this.outputNeurons = outputNeurons;
        this.middleLayerCount = middleLayerCount;
        middleLayerNeuronCount = 10;
        for (int currLayer = 1; currLayer < middleLayerCount; currLayer++) {
            layers.add(createLayer(middleLayerNeuronCount));
        }
        layers.add(createLayer(outputNeurons));
    }

    private Layer createLayer(int countNeuron) {
        return new Layer(countNeuron);
    }

    /**
     * Create input layer with input data
     * @param dataArray - input data
     */
    public void setInputData(List<Integer> dataArray) {
        inputNeurons = dataArray.size();
        Layer layer = createLayer(inputNeurons);
        layer.setInputData(dataArray);
        layers.add(0, layer);
    }

    public void setNeuronWeight(int neuron, List<Integer> weightArray, int bias) {
        Layer outputLayer = getOutputLayer();
        outputLayer.setNeuronWeight(neuron, weightArray);
        outputLayer.setBias(neuron, bias);
    }

    private Layer getInputLayer() {
        return layers.get(0);
    }

    private Layer getOutputLayer() {
        return layers.get(layers.size() - 1);
    }

    public int calc() {
        int maxValue = Integer.MIN_VALUE;
        int bestNeuron = 0;
        for (int currLayer=1; currLayer<layers.size(); currLayer++) {
            layers.get(currLayer).makeDataFromPrevLayer(layers.get(currLayer - 1));
        }
        List<Neuron> neurons = getOutputLayer().getNeurons();
        for (int i = 0; i < neurons.size(); i++) {
            if (neurons.get(i).getValue() > maxValue) {
                bestNeuron = i;
                maxValue = neurons.get(i).getValue();
            }
        }
        return bestNeuron;
    }
}
