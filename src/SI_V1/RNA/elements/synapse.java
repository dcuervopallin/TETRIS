package SI_V1.RNA.elements;

import java.util.Random;

public class synapse {
    private double weight;
    private final neuron first, second;

    public synapse(neuron first, neuron second, double weight) {
        this.first = first;
        this.second = second;
        this.weight = weight;
    }

    public synapse(neuron in, neuron out){
        this.first = in;
        this.weight = new Random().nextDouble();
        this.second = out;
    }

    @Override
    public String toString(){
        return "(" + first.getID() + "," + second.getID() + "," + String.format("%.10f",weight) + ")";
    }

    public int getFirstId(){
        return first.getID();
    }

    public double getWeight(){return weight;}

    public void setWeight(double weight){this.weight = weight;}

    public int getSecondId(){
        return second.getID();
    }

    public double getOutFirstN(){ return first.getValue();}

    public double propage() {
        return first.getValue() * weight;
    }
}
