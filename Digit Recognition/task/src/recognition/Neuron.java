package recognition;

import java.util.ArrayList;
import java.util.List;

public class Neuron {
    private List<Integer> weights = new ArrayList<>();
    private int bias;
    private int value;

    public void setWeights(List<Integer> weights) {
        this.weights = weights;
    }

    public void setBias(int bias) {
        this.bias = bias;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getBias() {
        return bias;
    }

    public void makeDataFromPrevLayer(Layer layer) {
        List<Neuron> neurons = layer.getNeurons();
        value = 0;
        for (int w = 0; w < neurons.size(); w++) {
            value += neurons.get(w).getValue() * weights.get(w);
        }
        value += bias;
    }
}