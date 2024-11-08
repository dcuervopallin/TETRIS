package analysis;

import SI_V0.heuristic.*;
import SI_V0.modelsDecision.actionDecision;
import SI_V0.modelsDecision.decisionV1.decision1;
import SI_V0.modelsDecision.decisionV2.decision2;
import utils.configGame;
import utils.playerSI;
import game.elements.*;
import game.elements.pieces.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class analysis {
    private final int trainingEpochs, maxIterations, topToPrint;
    private final boolean toDocResults, printResults;
    private final String docPath;
    private final piece[] totalPieces;
    private final heuristic[] totalHeuristics;
    private final actionDecision[] totalDecisions;

    private analysis(analysisBuilder builder){
        this.trainingEpochs = builder.trainingEpochs;
        this.maxIterations = builder.maxIterations;
        this.topToPrint = builder.topToSee;
        this.toDocResults = builder.toDocResults;
        this.printResults = builder.printResults;
        this.docPath = builder.docOutput;
        this.totalHeuristics = builder.totalHeuristics;
        this.totalDecisions = builder.totalDecisions;
        this.totalPieces = builder.totalPieces;
    }

    public void analysisSystem(){
        playerSI player = new playerSI();
        analysisResult analyzerOverResults = new analysisResult();
        analysisResult analyzerOverTotalResults = new analysisResult();
        String resultsTraining = "\t\t\tSE MUESTRAN LOS RESULTADOS PARA TODAS LAS MÉTRICAS IMPLEMENTADAS\n";
        String resultsAnalysis = "\t\t\tSE MUESTRAN LOS ANÁLISIS SOBRE LOS RESULTADOS\n";

        for(piece piece : totalPieces){
            resultsTraining += "\n\t<---------> RESULTADADOS ANÁLISIS, unicamente usando la pieza con id: " + piece.getNumberPiece() + " <--------->\n";
            for(actionDecision decision : totalDecisions){
                for(heuristic heuristic : totalHeuristics){
                    long[] times = new long[trainingEpochs];
                    int[] scores = new int[trainingEpochs];
                    int[] iterations = new int[trainingEpochs];
                    resultsTraining += "\n-> Using: SI_V0.heuristic, " + heuristic.getName() + "; decision, " + decision.getName() + ".\n\n";
                    resultsTraining += "epoch\ttime\tscore\titeration\n-)\t-\t-\t-\n";
                    for(int i = 0; i < trainingEpochs; i++){
                        configGame config = new configGame.configBuilder(decision, heuristic)
                                .setPiece(piece)
                                .iterations(maxIterations)
                                .build();

                        long startTime = System.currentTimeMillis();

                        int[] resultInt = player.initGame(config);

                        long endTime = System.currentTimeMillis();

                        times[i] = endTime - startTime;
                        scores[i] = resultInt[0];
                        iterations[i] = resultInt[1];

                        resultsTraining += i + ")\t" + times[i] + "\t" + scores[i] + "\t" + resultInt[1] + "\n";
                    }
                    resultsTraining += analyzerOverResults.meanOverResults(trainingEpochs, heuristic.getName(), decision.getName(), scores, times, iterations);
                    analyzerOverTotalResults.calculateMeanAndStore(trainingEpochs, heuristic.getName(), decision.getName(), scores, times, iterations);
                }
            }
        }
        resultsAnalysis +=  analyzerOverResults.finalResults(topToPrint, "\t<---------> RESULTADOS PARA EL CONJUNTO DE TODAS LAS PIEZAS <--------->\n");
        analyzerOverResults.resetResult();

        resultsTraining += "\n\t<---------> RESULATADOS ANÁLISIS, simulación real <--------->\n";
        for(actionDecision decision : totalDecisions){
            for(heuristic heuristic : totalHeuristics){
                long[] times = new long[trainingEpochs];
                int[] scores = new int[trainingEpochs];
                int[] iterations = new int[trainingEpochs];
                resultsTraining += "\n* Using: SI_V0.heuristic, " + heuristic.getName() + "; decision, " + decision.getName() + ".\n\n";
                resultsTraining += "epoch\ttime\tscore\titeration\n-)\t-\t-\t-\n";
                for(int i = 0; i < trainingEpochs; i++){
                    configGame config = new configGame.configBuilder(decision, heuristic)
                            .iterations(maxIterations)
                            .build();

                    long startTime = System.currentTimeMillis();

                    int[] resultInt = player.initGame(config);

                    long endTime = System.currentTimeMillis();

                    times[i] = endTime - startTime;
                    scores[i] = resultInt[0];
                    iterations[i] = resultInt[1];

                    resultsTraining += i + ")\t" + times[i] + "\t" + scores[i] + "\t" + resultInt[1] + "\n";
                }
                resultsTraining += analyzerOverResults.meanOverResults(trainingEpochs, heuristic.getName(), decision.getName(), scores, times, iterations);
                analyzerOverTotalResults.calculateMeanAndStore(trainingEpochs, heuristic.getName(), decision.getName(), scores, times, iterations);
            }
        }
        resultsAnalysis += analyzerOverResults.finalResults(topToPrint,  "\n\n\t\tRESULTADOS MODO ALEATORIO\n");
        if (printResults){
            System.out.println(resultsTraining);
        }
        if (toDocResults){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(docPath + ".log"))) {
                writer.write(resultsTraining);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(docPath + ".resul"))) {
                writer.write(resultsAnalysis);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static class analysisBuilder{
        private int trainingEpochs = 10, maxIterations = 10, topToSee = 3;
        private boolean toDocResults = false;
        private boolean printResults = false;
        private String docOutput = getName();
        private heuristic[] totalHeuristics = { new heurisHorizontalScoreOverMatrix(),
                new heurisScoreBasic(),
                new heurisScoreByNeighbor(),
                new heurisScoreOverMatrix(),
                new heurisVerticalMaskOverMatrixWithLoss(),
                new huerisVerticalMaskOverMatrix()
        };
        private piece[] totalPieces= {new piece1(),
                new piece2(),
                new piece3(),
                new piece4(),
                new piece5(),
                new piece6(),
                new piece7()};
        private actionDecision[] totalDecisions = { new decision1(),
                new decision2()};

        private String getName(){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" yyyyMMdd_HHmm");
            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(formatter);
            return System.getProperty("user.home") +  File.separator + "TETRIS_PRUEBAS" + File.separator + "Analys" + formattedDateTime;
        }

        public analysisBuilder setPieces(piece[] data) {
            this.totalPieces = data;
            return this;
        }

        public analysisBuilder setActionDecision(actionDecision[] decision){
            this.totalDecisions = decision;
            return this;
        }

        public analysisBuilder setHeuristic(heuristic[] data){
            this.totalHeuristics = data;
            return this;
        }

        public analysisBuilder setTopToSee(int topToSee){
            this.topToSee = topToSee;
            return this;
        }

        public analysisBuilder setTrainingEpochs(int trainingEpochs){
            this.trainingEpochs = trainingEpochs;
            return this;
        }

        public analysisBuilder setMaxIterations(int maxIterations){
            this.maxIterations = maxIterations;
            return this;
        }

        public analysisBuilder setToDocResults(boolean toDocResults){
            this.toDocResults = toDocResults;
            return this;
        }

        public analysisBuilder setPrintResults(boolean printResults){
            this.printResults = printResults;
            return this;
        }
        public analysisBuilder setDocOutput(String docOutput){
            this.docOutput = docOutput;
            return this;
        }

        public analysis build() { return new analysis(this); }
    }
}
