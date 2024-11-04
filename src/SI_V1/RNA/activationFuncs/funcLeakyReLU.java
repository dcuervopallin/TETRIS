package SI_V1.RNA.activationFuncs;

public class funcLeakyReLU implements functionActivationNeuron{
    @Override
    public double funcActivation(double data) {
        return Math.max(0.01*data, data);
    }

    @Override
    public double funcActDerived(double data) {
        return data < 0 ? 0.01 : 1;
    }

    @Override
    public String toString() {
        return "funcLeakyReLU";
    }
}
