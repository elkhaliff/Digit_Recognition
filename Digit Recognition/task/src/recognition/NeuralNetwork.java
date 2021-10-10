package recognition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork implements Serializable {
    private final List<Layer> layers = new ArrayList<>();
    private int idealNum = 0;

    public void initNetwork(int[] neuronsFromLayers) {
        layers.clear();
        int length = neuronsFromLayers.length;
        if (length < 2) return;
        for (int i = 0; i < length; i++) {
            layers.add(createLayer(neuronsFromLayers[i]));
            if (i > 0) {
                layers.get(i).gaussianRandom(neuronsFromLayers[i - 1]);
            }
        }
    }

    private Layer createLayer(int countNeuron) {
        return new Layer(countNeuron);
    }

    public void setInputData(double[] dataArray) {
        layers.get(0).setNeuronsValue(dataArray);
    }

    private Layer getOutputLayer() {
        return layers.get(layers.size() - 1);
    }

    private void activationNetwork() {
        for (int currLayer = 1; currLayer < layers.size(); currLayer++) {
            layers.get(currLayer).activationFromPrevLayer(layers.get(currLayer - 1));
        }
    }

    void trainOne(double[] input, double[] output, double[][][] gradWeight, double[][] gradBias) {
        setInputData(input);
        activationNetwork();
        var outLayer = getOutputLayer();
        var outNeurons = outLayer.getNeurons();
        var outNeurCnt = outLayer.getNeuronCount();

        var error = new double[outNeurCnt];
        for (var i = 0; i < error.length; i++) {
            var value = outNeurons.get(i).getValue();
            error[i] = (value - output[i]) / (value * (1 - value));
        }
        var size = layers.size() - 1;

        for (var l = size; l >= 1; l--) {
            var currLayer = layers.get(l);
            var layerNeurons = currLayer.getNeurons();

            var prevLayer = layers.get(l - 1);
            var prevNeuron = prevLayer.getNeurons();
            var nextError = new double[prevLayer.getNeuronCount()];

            for (var n = 0; n < currLayer.getNeuronCount(); n++) {
                var currNeuron = layerNeurons.get(n);
                var currNeuronValue = currNeuron.getValue();
                error[n] *= currNeuronValue * (1 - currNeuronValue);
                var currWeights = currNeuron.getWeights();
                for (var w = 0; w < currWeights.length; w++) {
                    gradWeight[l][n][w] += error[n] * prevNeuron.get(w).getValue(); //-- !!!!
                    nextError[w] += error[n] * currWeights[w];
                }
                gradBias[l][n] += error[n];
            }
            error = nextError;
        }
    }

    void trainEpoch(double[][] inputs, double[][] outputs, double psi) {
        var size = layers.size();
        var gradWeight = new double[size][][];
        var gradBias = new double[size][];
        for (var l = 0; l < size; l++) {
            var currLayer = layers.get(l);
            var cntW = l == 0 ? 0 : currLayer.getNeurons().get(0).getWeights().length;
            gradWeight[l] = new double[currLayer.getNeuronCount()][cntW];
            gradBias[l] = new double[currLayer.getNeuronCount()];
        }
        for (double[] input : inputs) {
            int len = input.length;
            idealNum = (int) input[len - 1];
            trainOne(input, outputs[idealNum], gradWeight, gradBias);
        }
        for (var l = 1; l < size; l++) {
            var currLayer = layers.get(l);
            var layerNeurons = currLayer.getNeurons();
            for (var n = 0; n < currLayer.getNeuronCount(); n++) {
                var currNeuron = layerNeurons.get(n);
                var currWeights = currNeuron.getWeights();
                var currBias = currNeuron.getBias();

                for (var w = 0; w < currWeights.length; w++) {
                    currWeights[w] -= (gradWeight[l][n][w] * psi) / inputs.length;
                }
                currBias -= (gradBias[l][n] * psi) / inputs.length;

                currNeuron.setWeights(currWeights);
                currNeuron.setBias(currBias);
            }
        }
    }

    public void train(double[][] inputs, int epoch, double psi) {

        for (var i = 0; i < epoch; i++) {
            trainEpoch(inputs, Ideal.getOutputs(), psi);
            if (i % 10 == 0) {
                guessTestData(inputs);
            }
        }
        System.out.printf("Num of Ideal Array: %d \n", idealNum);
        recognition(true);
    }

    public void guessTestData(double[][] inputs) {
        int count = 0;
        for (double[] input: inputs) {
            int len = input.length;
            idealNum = (int)input[len - 1];
            setInputData(input);
            if(recognition(false) == idealNum) count++;
        }
        int percent = count*100/inputs.length;
        System.out.printf("The network prediction accuracy: %d/%d, %d \n", count, inputs.length, percent);
    }

    public int recognition(boolean print) {
        double maxValue = Double.MIN_VALUE;
        int bestNeuron = 0;

        activationNetwork();
        List<Neuron> neurons = getOutputLayer().getNeurons();
        for (int i = 0; i < neurons.size(); i++) {
            double currValue = neurons.get(i).getValue();
//            if (print)
//                System.out.printf(" (%d : %.3f)", i, currValue);

            if (currValue > maxValue) {
                bestNeuron = i;
                maxValue = currValue;
            }
        }
//        System.out.println();
        return bestNeuron;
    }
}
