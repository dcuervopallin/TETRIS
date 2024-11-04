package SI_V1.RNA.activationFuncs;

public class funcSwish implements functionActivationNeuron{
    private double beta;

    public funcSwish(double beta){
        this.beta = beta;
    }

    @Override
    public double funcActivation(double data) {
        return data * sigmoid(data * beta);
    }

    @Override
    public double funcActDerived(double data) {
        double sigmoid = sigmoid(data * beta);
        return sigmoid + data * beta * sigmoid * (1 - sigmoid);
    }

    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    @Override
    public String toString() {
        return "funcSwish{" + beta + '}';
    }
}
