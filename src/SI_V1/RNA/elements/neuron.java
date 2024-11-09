package SI_V1.RNA.elements;

import java.util.Random;

public class neuron {
    private int idNeron;
    private neuronType type;
    private double value, sesge;

    public neuron(int id, neuronType type, double sesge) {
        this.idNeron = id;
        this.type = type;
        this.sesge = sesge;
    }

    public neuron(int id, neuronType type){
        this.idNeron = id;
        this.type = type;
        this.sesge = new Random().nextDouble();
    }

    public void setValue(double inValue) {
        this.value = inValue;
    }

    public double getSesge(){
        return sesge;
    }

    public void setSesge(double inValue) {
        this.sesge = inValue;
    }

    public double getValue(){
        return this.value;
    }

    public int getID(){
        return this.idNeron;
    }

    @Override
    public String toString(){
        return idNeron + "(" + type +"," +String.format("%.10f", sesge)+")";
    }
}