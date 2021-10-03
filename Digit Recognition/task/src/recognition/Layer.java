package recognition;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private final List<Neuron> neurons = new ArrayList<>();

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setBias(int neuron, int bias) {
        neurons.get(neuron).setBias(bias);
    }

    public Layer(int neuronCount) {
        for (int i = 0; i < neuronCount; i++) {
            neurons.add(i, new Neuron());
        }
    }

    public void makeDataFromPrevLayer(Layer layer) {
        for (Neuron neuron : neurons) {
            neuron.makeDataFromPrevLayer(layer);
        }
    }

    public void setInputData(List<Integer> dataArray) {
        for (int currNeuron=0; currNeuron<neurons.size(); currNeuron++) {
            neurons.get(currNeuron).setValue(dataArray.get(currNeuron));
        }
    }

    public void setNeuronWeight(int neuron, List<Integer> weightArray) {
        neurons.get(neuron).setWeights(weightArray);
    }
}
