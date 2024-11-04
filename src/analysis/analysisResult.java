package analysis;

import analysis.comparators.iterationsComparator;
import analysis.comparators.meanDesvNode;
import analysis.comparators.scoreComparator;
import analysis.comparators.scoreNComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class analysisResult {
    private ArrayList<meanDesvNode> totalMean = new ArrayList<>();

    public void resetResult(){
        totalMean.clear();
    }

    public void calculateMeanAndStore(int trainingEpochs, String h, String d, int[] scores,long[] times, int[] iterations){
        double meanScore = 0, desviacionScore = 0;
        double meanTime = 0, desviacionTime = 0;
        double meanIteration = 0, desviacionIteration = 0;

        for(int i = 0; i < trainingEpochs; i++){
            meanScore += scores[i];
            meanTime += times[i];
            meanIteration += iterations[i];
        }
        meanScore /= trainingEpochs;
        meanTime /= trainingEpochs;
        meanIteration /= trainingEpochs;

        for(int i = 0; i < trainingEpochs; i++){
            desviacionScore += Math.sqrt(Math.pow((meanScore - scores[i]), 2));
            desviacionTime += Math.sqrt(Math.pow((meanTime - times[i]), 2));
            desviacionIteration += Math.sqrt(Math.pow((meanIteration - iterations[i]), 2));
        }
        desviacionScore /= trainingEpochs;
        desviacionTime /= trainingEpochs;
        desviacionIteration /= trainingEpochs;

        new meanDesvNode(d, h, meanScore, desviacionScore, meanTime, desviacionTime, meanIteration, desviacionIteration);
    }

    public String meanOverResults(int trainingEpochs, String h, String d, int[] scores,long[] times, int[] iterations){
        double meanScore = 0, desviacionScore = 0;
        double meanTime = 0, desviacionTime = 0;
        double meanIteration = 0, desviacionIteration = 0;

        for(int i = 0; i < trainingEpochs; i++){
            meanScore += scores[i];
            meanTime += times[i];
            meanIteration += iterations[i];
        }
        meanScore /= trainingEpochs;
        meanTime /= trainingEpochs;
        meanIteration /= trainingEpochs;

        for(int i = 0; i < trainingEpochs; i++){
            desviacionScore += Math.sqrt(Math.pow((meanScore - scores[i]), 2));
            desviacionTime += Math.sqrt(Math.pow((meanTime - times[i]), 2));
            desviacionIteration += Math.sqrt(Math.pow((meanIteration - iterations[i]), 2));
        }
        desviacionScore /= trainingEpochs;
        desviacionTime /= trainingEpochs;
        desviacionIteration /= trainingEpochs;

        totalMean.add(new meanDesvNode(d, h, meanScore, desviacionScore, meanTime, desviacionTime, meanIteration, desviacionIteration));
        return "\nResultados finales del conjunto: \n\nmetrica\t\tmedia\t\tdesviación" +
                "\n-\t\t-\t\t-"+
                "\nScore\t\t" + meanScore + "\t\t" + desviacionScore +
                "\nTime ms\t\t" + meanTime + "\t\t" + desviacionTime +
                "\nIterat\t\t" + meanIteration + "\t\t" + desviacionIteration + "\n";
    }

    public String finalResults(int top, String init){
        String total = init, aux1, aux2;
        int j = 0;
        String[] cabeceras ={", ordenados por meanScore: \n",
                ", ordenados por meanIteration: \n",
                ", ordenados por meanNScore: \n"
        };
        
        @SuppressWarnings("unchecked")
        Comparator<meanDesvNode>[] comparators = new Comparator[]{
                new scoreComparator(),
                new iterationsComparator(),
                new scoreNComparator()
        };

        for(Comparator<meanDesvNode> comparator : comparators){
            Collections.sort(totalMean, comparator);
            aux1 = "";
            aux2 = "";
            for(int i = 0; i < top; i++){
                aux1 += "\n# " + (i + 1) + ") " + totalMean.get(i).toString();
                aux2 += "\n# " + (totalMean.size() - top + i) + ") " + totalMean.get(totalMean.size() - top + i).toString();
            }

            total += "\n -> Se muestran los #" + top +" mejores y peores heuristicas y decisiones" + cabeceras[j] + aux1 + "\n\t\t...\n" + aux2;
            j++;
        }
        return total;
    }
}
