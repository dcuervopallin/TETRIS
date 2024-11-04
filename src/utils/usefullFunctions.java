package utils;

import SI_V1.RNA.activationFuncs.*;
import SI_V1.RNA.elements.neuron;
import SI_V1.RNA.elements.neuronType;
import SI_V1.RNA.elements.synapse;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class usefullFunctions {
    public static functionActivationNeuron getActivationFuncByString(String input) {
        if(input.equals(new funcLeakyReLU().toString())){
            return new funcLeakyReLU();
        } else if(input.equals(new funcMish().toString())){
            return new funcMish();
        }else if(input.equals(new funcReLU().toString())){
            return new funcReLU();
        }else if(input.equals(new funcSigmoidLog().toString())){
            return new funcSigmoidLog();
        }else if(input.equals(new funcSoftPlus().toString())){
            return new funcSoftPlus();
        }else if(input.equals(new funcTangenteHiperbolica().toString())){
            return new funcTangenteHiperbolica();
        } else if(input.startsWith("funcSwish")){
            Pattern pattern = Pattern.compile("funcSwish\\{(.*?)\\}");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                double beta = Double.parseDouble(matcher.group(1));
                return new funcSwish(beta);
            }
        }else if(input.startsWith("funcParametricReLU")){
            Pattern pattern = Pattern.compile("funcParametricReLU\\{(.*?)\\}");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                double beta = Double.parseDouble(matcher.group(1));
                return new funcParametricReLU(beta);
            }
        }
        return null;
    }

    private static neuronType getNeuronTypeByString(String input) {
        if(input.equals(neuronType.IN)){
            return neuronType.IN;
        } else if(input.equals(neuronType.OUT)){
            return neuronType.OUT;
        } else if (input.equals(neuronType.HIDE)){
            return neuronType.HIDE;
        }
        return null;
    }

    public static synapse getSynapseByString(String input) {
        Pattern pattern = Pattern.compile("\\((\\d+),(\\d+),(\\d+\\.\\d+)\\)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            int firstInteger = Integer.parseInt(matcher.group(1));
            int secondInteger = Integer.parseInt(matcher.group(2));
            double doublePart = Double.parseDouble(matcher.group(3));

            return null;
            //return new synapse(firstInteger, secondInteger, doublePart);
        } else {
            throw new IllegalArgumentException("El formato del input no es válido.");
        }
    }

    public static neuron getNeuronByString(String input) {
        Pattern pattern = Pattern.compile("(\\d+)\\((\\w+),(\\d+\\.\\d+)\\)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            int integerPart = Integer.parseInt(matcher.group(1));
            String stringPart = matcher.group(2);
            double doublePart = Double.parseDouble(matcher.group(3));

            return new neuron(integerPart, getNeuronTypeByString(stringPart), doublePart);
        } else {
            throw new IllegalArgumentException("El formato del input no es válido.");
        }

    }

    public static int[] getArquitectureByString(String input) {
        String aux = input.substring(1, input.length() - 1);

        String[] stringNumbers = aux.split(", ");

        return Arrays.stream(stringNumbers)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
