package utils;
import modelsDecision.actionDecision;
import heuristic.heuristic;
import game.elements.piece;

public class configGame {
    private final actionDecision actionDecision;
    private final heuristic heuristic;
    private final int iterations, scoreGapToStore;
    private final boolean printMatrix;
    private final piece pieceUsed;
    private final String pathStoreTrain;

    private configGame(configBuilder builder) {
        this.actionDecision = builder.actionDecision;
        this.heuristic = builder.heuristic;
        this.iterations = builder.iterations;
        this.printMatrix = builder.printMatrix;
        this.pieceUsed = builder.pieceUsed;
        this.scoreGapToStore = builder.scoreGapToStore;
        this.pathStoreTrain = builder.pathTrainRNNA;
    }

    public String getPathStoreTrain() { return pathStoreTrain;}

    public int getScoreGapToStore() { return scoreGapToStore;}

    public boolean isPrintMatrix() {
        return printMatrix;
    }

    public actionDecision getActionDecision() {
        return actionDecision;
    }

    public heuristic getHeuristic() { return heuristic; }

    public piece getPieceUsed() {
        return pieceUsed;
    }

    public int getIterations() {
        return iterations;
    }

    public static class configBuilder {
        private final actionDecision actionDecision;
        private final heuristic heuristic;
        private int iterations = 500, scoreGapToStore = 500; // default value
        private boolean printMatrix = false; // default value
        private piece pieceUsed = null;
        private String pathTrainRNNA = null;

        public configBuilder(modelsDecision.actionDecision actionDecision, heuristic heuristic) {
            this.actionDecision = actionDecision;
            this.heuristic = heuristic;
        }

        public configBuilder trainPath(String pathTrainRNNA) {
            this.pathTrainRNNA = pathTrainRNNA;
            return this;
        }

        public configBuilder trainScoreGapToStore(int scoreGapToStore) {
            this.scoreGapToStore = scoreGapToStore;
            return this;
        }

        public configBuilder setPiece(piece piece) {
            this.pieceUsed = piece;
            return this;
        }

        public configBuilder iterations(int iterations) {
            this.iterations = iterations;
            return this;
        }

        public configBuilder printMatrix(boolean printMatrix) {
            this.printMatrix = printMatrix;
            return this;
        }

        public configGame build() {
            return new configGame(this);
        }
    }
}
