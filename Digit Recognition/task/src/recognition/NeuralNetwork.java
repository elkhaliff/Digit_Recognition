package recognition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork implements Serializable {
    private final List<Layer> layers = new ArrayList<>();

    public void initNetwork(int[] neuronsFromLayers) {
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

    private Layer getInputLayer() {
        return layers.get(0);
    }

    private Layer getOutputLayer() {
        return layers.get(layers.size() - 1);
    }

    private void activationNetwork() {
        for (int currLayer=1; currLayer<layers.size(); currLayer++) {
            layers.get(currLayer).activationFromPrevLayer(layers.get(currLayer - 1));
        }
    }

    public int calc() {
        double maxValue = Double.MIN_VALUE;
        int bestNeuron = 0;

        activationNetwork();
        List<Neuron> neurons = getOutputLayer().getNeurons();
        for (int i = 0; i < neurons.size(); i++) {
            if (neurons.get(i).getValue() > maxValue) {
                bestNeuron = i;
                maxValue = neurons.get(i).getValue();
            }
        }
        return bestNeuron;
    }


    void trainSample(double[] input, double[] output, double[][][] gradWeight, double[][] gradBias) {
        setInputData(input);
        activationNetwork(); // в алгоритме это заново расчитанный массив значений нейронов, нужно подумать - подойдут ли имеющиеся? а веса остались предыдущими
                            // может задействовать матрицу?
                                // и еще нужна psi !! --- проверять оба механизма
        var outLayer = getOutputLayer();
        var outNeurons = getOutputLayer().getNeurons();
        var outNeurCnt = outLayer.getNeuronCount();

        var error = new double[outNeurCnt];
        for (var i = 0; i < error.length; i++) {
            error[i] = (outNeurons.get(i).getValue() - output[i]) / (outNeurons.get(i).getValue() * (1 - outNeurons.get(i).getValue()));
        }
        var size = layers.size() - 1;

        for (var l = size - 1; l >= 0; l--) {
            var currLayer = layers.get(l);
            var layerNeurons = currLayer.getNeurons();
            var nextLayer = layers.get(l + 1);
            var nextLayerNeurons = nextLayer.getNeurons();
            var nextError = new double[currLayer.getNeurons().get(0).getWeights().length];

            for (var n = 0; n < currLayer.getNeuronCount(); n++) {
                error[n] *= nextLayerNeurons.get(n).getValue() * (1 - nextLayerNeurons.get(n).getValue());
                var currNeuron = layerNeurons.get(n);
                var currWeights = currNeuron.getWeights();
                for (var w = 0; w < currWeights.length; w++) {
                    gradWeight[l][n][w] += error[n] * currNeuron.getValue();
                    nextError[w] += error[n] * currWeights[w];
                }
                gradBias[l][n] += error[n];
            }
            error = nextError;
        }
    }

    void trainBatch(double[][] inputs, double[][] outputs, double psi) {
        var size = layers.size() - 1;
        var gradWeight = new double[size][][];
        var gradBias = new double[size][];
        for (var l = 1; l < size; l++) {
            var currLayer = layers.get(l);
            gradWeight[l] = new double[currLayer.getNeuronCount()][currLayer.getNeurons().get(0).getWeights().length];
            gradBias[l] = new double[currLayer.getNeuronCount()];
        }
        for (var i = 0; i < inputs.length; i++) {
            trainSample(inputs[i], outputs[i], gradWeight, gradBias);
        }
        for (var l = 1; l < size; l++) {
            var currLayer = layers.get(l);
            var layerNeurons = currLayer.getNeurons();
            for (var n = 0; n < currLayer.getNeuronCount(); n++) {
                var currNeuron = layerNeurons.get(n);
                var currWeights = currNeuron.getWeights();
                var bias = currNeuron.getBias();
                for (var w = 0; w < currWeights.length; w++) {
                    currWeights[w] -= (gradWeight[l][n][w] * psi) / inputs.length;
                }
                currNeuron.setWeights(currWeights);
                currNeuron.setBias(bias - (bias * psi) / inputs.length);
            }
        }
    }

    public void train(int epoch, double psi) {
        for (var i = 0; i < epoch; i++) {
            trainBatch(Ideal.getInputs(), Ideal.getOutputs(), psi);
        }
    }
}
