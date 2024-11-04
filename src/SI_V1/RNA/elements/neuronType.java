package SI_V1.RNA.elements;

public enum neuronType {
    IN,
    OUT,
    HIDE;

    @Override
    public String toString() {
        switch (this) {
            case IN:
                return "Input";
            case OUT:
                return "Output";
            case HIDE:
                return "Hidden";
            default:
                return super.toString(); // Esto nunca debería ocurrir
        }
    }
}
