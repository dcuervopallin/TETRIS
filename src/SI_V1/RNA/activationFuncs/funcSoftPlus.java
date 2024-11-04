package SI_V1.RNA.activationFuncs;

public class funcSoftPlus implements functionActivationNeuron{
    @Override
    public double funcActivation(double data) {
        return Math.log(1 + Math.exp(data));
    }

    @Override
    public double funcActDerived(double data) {
        return 1 / (1 + Math.exp(-data));
    }

    @Override
    public String toString() {
        return "funcSoftPlus";
    }
}
