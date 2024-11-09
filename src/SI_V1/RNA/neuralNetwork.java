package SI_V1.RNA;

import SI_V1.RNA.activationFuncs.functionActivationNeuron;
import SI_V1.RNA.elements.neuralException;
import SI_V1.RNA.elements.neuron;
import SI_V1.RNA.elements.neuronType;
import SI_V1.RNA.elements.synapse;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static utils.usefullFunctions.*;

public class neuralNetwork {
    private final neuron[][] totalNeural;
    private final synapse[][] synapsesNet;
    private final int[] architecture;
    private final functionActivationNeuron funcAct;

    public neuralNetwork(String path){
        int[] newArchitecture = null;
        functionActivationNeuron functionActivationNeuron = null;
        neuron[][] newNeurons = null;
        synapse[][] newSynapses = null;

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;

            if ((line = br.readLine()) != null){
                functionActivationNeuron = getActivationFuncByString(line);
            }
            br.readLine();

            if((line = br.readLine()) != null){
                newArchitecture = getArquitectureByString(line);
            }
            br.readLine();

            newNeurons = new neuron[newArchitecture.length][];
            newSynapses = new synapse[newArchitecture.length - 1][];
            ArrayList<ArrayList<neuron>> neuron = new ArrayList<>();

            for(int layerSize = 0; layerSize < newArchitecture.length; layerSize++){
                line = br.readLine();
                String[] tokens = line.split("\t");
                ArrayList<neuron> neuronList = new ArrayList<>();

                for(String token : tokens){
                    neuronList.add(getNeuronByString(token));
                }
                neuron.add(neuronList);
            }

            br.readLine();

            for (int i = 0; i < neuron.size(); i++) {
                ArrayList<neuron> layer = neuron.get(i);
                newNeurons[i] = layer.toArray(new neuron[newArchitecture[i]]);
            }

            ArrayList<ArrayList<synapse>> synapseList = new ArrayList<>();
            for(int layerSize = 0; layerSize < newArchitecture.length - 1; layerSize++){
                line = br.readLine();
                String[] tokens = line.split("\t");
                ArrayList<synapse> synapse = new ArrayList<>();
                for(String token : tokens){
                    synapse.add(getSynapseByString(token, newNeurons[layerSize], newNeurons[layerSize + 1]));
                }
                synapseList.add(synapse);
            }

            for (int i = 0; i < newArchitecture.length - 1; i++) {
                ArrayList<synapse> layer = synapseList.get(i);
                newSynapses[i] = layer.toArray(new synapse[layer.size()]);
            }
        }catch (IOException e){
            System.out.println(e);
        }
        this.funcAct = functionActivationNeuron;
        this.architecture = newArchitecture;

        this.totalNeural = newNeurons;
        this.synapsesNet = newSynapses;
    }

    public neuralNetwork(int[] architecture, functionActivationNeuron funcAct){
        this.architecture = architecture;
        this.totalNeural = new neuron[architecture.length][];
        this.synapsesNet = new synapse[architecture.length - 1][];
        this.funcAct = funcAct;

        for (int layerIdx = architecture.length - 1; layerIdx > -1; layerIdx--) {
            int numNeurons = architecture[layerIdx];
            neuron[] layer = new neuron[numNeurons];

            for (int neuronIdx = 0; neuronIdx < numNeurons; neuronIdx++) {
                neuronType type;
                if (layerIdx == 0) {
                    type = neuronType.IN;
                } else if (layerIdx == architecture.length - 1) {
                    type = neuronType.OUT;
                } else {
                    type = neuronType.HIDE;
                }
                layer[neuronIdx] = new neuron(neuronIdx, type);
            }
            if (layerIdx != architecture.length - 1){
                synapse[] layerSynapses = new synapse[architecture[layerIdx] * architecture[layerIdx + 1]];
                int synapsesCount = 0;
                for (neuron iNeuron:layer) {
                    for(neuron outNeuron:totalNeural[layerIdx + 1]){
                        layerSynapses[synapsesCount] = new synapse(iNeuron, outNeuron);
                        synapsesCount ++;
                    }
                }
                synapsesNet[layerIdx] = layerSynapses;
            }
            totalNeural[layerIdx] = layer;
        }
    }

    @Override
    public String toString(){
        String neuralNetString = funcAct.toString()+"\n\n"+ Arrays.toString(architecture) +"\n\n";
        for(int i = 0; i < totalNeural.length;i++){
            for(int j = 0; j < totalNeural[i].length;j++){
                neuralNetString += totalNeural[i][j] + "\t";
            }
            neuralNetString += "\n";
        }
        neuralNetString += "\n";
        for(int i = 0; i < synapsesNet.length;i++){
            for(int j = 0; j < synapsesNet[i].length;j++){
                neuralNetString += synapsesNet[i][j] + "\t";
            }
            neuralNetString += "\n";
        }

        return neuralNetString;
    }

    public void writeNN(String path){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write(toString());
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public double[] operacionalize(double[] data){
        double[] results = null;
        try{
            results = propage(data);
        }catch (neuralException e){
            System.out.println(e);
        }
        return results;
    }

    private double[] propage(double[] data) throws neuralException{
        if(data.length != architecture[0]){
            throw new neuralException("DataSet and NN in exception, " + data.length +" size in data in, " + architecture[0] + " size NN 0 layer");
        }else {
            for (neuron firstLayerN : this.totalNeural[0]) {
                firstLayerN.setValue(data[firstLayerN.getID()]);
            }
            for (int layerUpdate = 0; layerUpdate < synapsesNet.length; layerUpdate++) {
                double[][] results = new double[architecture[layerUpdate + 1]][architecture[layerUpdate]];
                for (synapse aux : synapsesNet[layerUpdate]) {
                    results[aux.getSecondId()][aux.getFirstId()] = aux.propage();
                }
                for (neuron neuronToUpdate : totalNeural[layerUpdate + 1]) {
                    double result = 0;
                    for (double value : results[neuronToUpdate.getID()]) {
                        result += value;
                    }
                    neuronToUpdate.setValue(funcAct.funcActivation(result + neuronToUpdate.getSesge()));
                }
            }
            double[] results = new double[architecture[architecture.length - 1]];
            String resultString = "";
            for (neuron aux : totalNeural[totalNeural.length - 1]) {
                results[aux.getID()] = aux.getValue();
                resultString += results[aux.getID()] + "\t";
            }
            System.out.println(resultString);
            return results;
        }
    }

    public void backpropagate(double[] input, double[] target, double learningRate) {
        try {
            backpropagateAux(input, target, learningRate);
        } catch (neuralException e) {
            System.out.println(e);
        }
    }

    private void backpropagateAux(double[] input, double[] target, double learningRate) throws neuralException {
        if (input.length != architecture[0]) {
            throw new neuralException("DataSet and NN in exception, " + input.length + " size input, " + architecture[0] + " size NN 0 layer");
        } else if (target.length != architecture[architecture.length - 1]) {
            throw new neuralException("DataSet and NN in exception, " + target.length + " size target, " + architecture[architecture.length - 1] + " size NN " + architecture[architecture.length - 1] + " layer");
        } else {
            // Realizar la propagación hacia adelante
            double[] output = propage(input);

            // Calcular el error de salida
            double[] errorOutputLayer = new double[output.length];
            for (int i = 0; i < output.length; i++) {
                errorOutputLayer[i] = target[i] - output[i];
            }

            // Inicializar los errores de las capas
            double[][] deltas = new double[architecture.length][];

            // Calcular el delta para la capa de salida
            deltas[architecture.length - 1] = new double[architecture[architecture.length - 1]];
            for (int i = 0; i < deltas[architecture.length - 1].length; i++) {
                deltas[architecture.length - 1][i] = errorOutputLayer[i] * funcAct.funcActDerived(output[i]);
            }

            // Calcular deltas para las capas ocultas
            for (int layer = architecture.length - 2; layer > 0; layer--) {
                deltas[layer] = new double[architecture[layer]];
                for (int i = 0; i < deltas[layer].length; i++) {
                    double deltaSum = 0;
                    for (int j = 0; j < architecture[layer + 1]; j++) {
                        deltaSum += deltas[layer + 1][j] * synapsesNet[layer][i * architecture[layer + 1] + j].getWeight();
                    }
                    deltas[layer][i] = deltaSum * funcAct.funcActDerived(totalNeural[layer][i].getValue());
                }
            }

            // Actualizar los pesos y sesgos
            for (int layer = 0; layer < synapsesNet.length; layer++) {
                for (synapse s : synapsesNet[layer]) {
                    // Actualizar peso
                    double deltaWeight = learningRate * deltas[layer + 1][s.getSecondId()] * totalNeural[layer][s.getFirstId()].getValue();
                    s.setWeight(s.getWeight() + deltaWeight);

                    // Actualizar sesgo
                    double deltaBias = learningRate * deltas[layer + 1][s.getSecondId()];
                    totalNeural[layer + 1][s.getSecondId()].setSesge(totalNeural[layer + 1][s.getSecondId()].getSesge() + deltaBias);
                }
            }
        }
    }
}