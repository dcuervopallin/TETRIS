package SI_V1.RNA.activationFuncs;

public class funcReLU implements functionActivationNeuron{
    @Override
    public double funcActivation(double data) {
        return Math.max(0,data);
    }

    @Override
    public double funcActDerived(double data) {
        return data > 0 ? 1 : 0;
    }

    @Override
    public String toString() {
        return "funcReLU";
    }
}
