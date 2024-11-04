package analysis.comparators;

public class meanDesvNode {
    private final String decisionUsed;
    private final String heuristicUsed;
    private final double meanScore, desviacionScore, meanTime, desviacionTime, meanIteration, desviacionIteration;

    public double getMeanScore() {
        return meanScore;
    }

    public double getMeanIteration() {return meanIteration;}

    public double getNScore(){
        return meanScore - desviacionScore;
    }

    public meanDesvNode(String decisionUsed, String heuristicUsed, double meanScore, double desviacionScore, double meanTime, double desviacionTime, double meanIteration, double desviacionIteration) {
        this.decisionUsed = decisionUsed;
        this.heuristicUsed = heuristicUsed;
        this.meanScore = meanScore;
        this.desviacionScore = desviacionScore;
        this.meanTime = meanTime;
        this.desviacionTime = desviacionTime;
        this.desviacionIteration = desviacionIteration;
        this.meanIteration = meanIteration;
    }

    @Override
    public String toString() {
        return "Using: heuristic, " + heuristicUsed + "; action Decision, " + decisionUsed + "\n\nmetrica\t\tmedia\t\tdesviación" +
                "\n-\t\t-\t\t-"+
                "\nScore\t\t" + meanScore + "\t\t" + desviacionScore +
                "\nTime ms\t\t" + meanTime + "\t\t" + desviacionTime +
                "\nIterat\t\t" + meanIteration + "\t\t" + desviacionIteration + "\n";
    }
}
