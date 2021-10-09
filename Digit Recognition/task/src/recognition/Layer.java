package recognition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Layer implements Serializable {
    private final int neuronCount;
    private final List<Neuron> neurons = new ArrayList<>();

    public int getNeuronCount() {
        return neuronCount;
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public Layer(int neuronCount) {
        this.neuronCount = neuronCount;
        for (int i = 0; i < neuronCount; i++) {
            neurons.add(i, new Neuron());
        }
    }

    public void activationFromPrevLayer(Layer prevLayer) {
        for (Neuron neuron : neurons) {
            neuron.activationFromPrevLayer(prevLayer);
        }
    }

    public void gaussianRandom(int countNeuronPrevLayer) {
        for (Neuron neuron : neurons) {
            neuron.gaussianRandom(countNeuronPrevLayer);
        }
    }

    public void setNeuronsValue(double[] dataArray) {
        for (int currNeuron=0; currNeuron<neurons.size(); currNeuron++) {
            neurons.get(currNeuron).setValue(dataArray[currNeuron]);
        }
    }
}
