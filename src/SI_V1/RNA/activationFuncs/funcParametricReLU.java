package SI_V1.RNA.activationFuncs;

public class funcParametricReLU implements functionActivationNeuron{
    private final double param;
    public funcParametricReLU(double param){
        this.param = param;
    }
    @Override
    public double funcActivation(double data) {
        if(data < 0){
            return data;
        }else{
            return data * param;
        }
    }

    @Override
    public double funcActDerived(double data) {
        return 0;
    }

    @Override
    public String toString() {
        return "funcParametricReLU{" + param + '}';
    }
}
