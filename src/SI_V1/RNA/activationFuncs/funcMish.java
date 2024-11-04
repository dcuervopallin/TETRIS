package SI_V1.RNA.activationFuncs;

public class funcMish implements functionActivationNeuron{
    @Override
    public double funcActivation(double data) {
        functionActivationNeuron softPlus = new funcSoftPlus();
        functionActivationNeuron tangenHiper = new funcTangenteHiperbolica();
        return data * tangenHiper.funcActivation(softPlus.funcActivation(data));
    }

    @Override
    public double funcActDerived(double data) {
        funcSoftPlus softPlus = new funcSoftPlus();
        funcTangenteHiperbolica tangenHiper = new funcTangenteHiperbolica();

        double softPlusResult = softPlus.funcActivation(data);
        double tanhResult = tangenHiper.funcActivation(softPlusResult);
        double tanhDerived = tangenHiper.funcActDerived(softPlusResult);

        return tanhResult + data * softPlus.funcActDerived(data) * tanhDerived;
    }

    @Override
    public String toString() {
        return "funcMish";
    }
}
