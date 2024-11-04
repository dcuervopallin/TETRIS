package SI_V1.RNA.activationFuncs;

public class funcTangenteHiperbolica implements functionActivationNeuron{

    @Override
    public double funcActivation(double data) {
        return Math.tanh(data);
    }

    @Override
    public double funcActDerived(double data) {
        double tanh = Math.tanh(data);
        return 1 - tanh * tanh;
    }

    @Override
    public String toString() {
        return "funcTangenteHiperbolica";
    }
}
