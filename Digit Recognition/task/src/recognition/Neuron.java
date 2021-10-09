package recognition;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Neuron implements Serializable {
    private double[] weights;
    private double bias;
    private double value;

    public double[] getWeights() {
        return weights;
    }

    public double getBias() {
        return bias;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void gaussianRandom(int countNeuronPrevLayer){
        weights = new double[countNeuronPrevLayer];
        Random random = new Random();
        for(int i = 0; i < weights.length; i++){
                weights[i] = random.nextGaussian();
        }
        bias = random.nextGaussian();
    }

    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public void activationFromPrevLayer(Layer prevLayer) {
        List<Neuron> prevNeurons = prevLayer.getNeurons();
        value = 0;
        for (int w = 0; w < prevNeurons.size(); w++) {
            value += prevNeurons.get(w).getValue() * weights[w];
        }
        value = sigmoid(value + bias);
    }
}