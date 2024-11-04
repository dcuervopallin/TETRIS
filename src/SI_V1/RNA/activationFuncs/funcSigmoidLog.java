package SI_V1.RNA.activationFuncs;

public class funcSigmoidLog implements functionActivationNeuron{
    // Buena clasificación binaria
    @Override
    public double funcActivation(double data) {
        return 1 / (1 + Math.exp(-data));
    }

    @Override
    public double funcActDerived(double data) {
        double sigmoid = funcActivation(data);
        return sigmoid * (1 - sigmoid);
    }

    @Override
    public String toString() {
        return "funcSigmoidLog";
    }
}
