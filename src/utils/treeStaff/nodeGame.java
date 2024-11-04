package utils.treeStaff;

public class nodeGame {
    final private stateGame state1;
    final private transitionGame transition;
    final private stateGame state2;

    public nodeGame(stateGame state1, transitionGame transition, stateGame state2) {
        this.state1 = state1;
        this.transition = transition;
        this.state2 = state2;
    }

    public stateGame getState1() {
        return state1;
    }

    public transitionGame getTransition() {
        return transition;
    }

    public stateGame getState2() {
        return state2;
    }

    @Override
    public String toString() {
        return "nodeGame{" +
                "state1=\n" + state1 +
                ", transition=" + transition +
                ", state2=\n" + state2 +
                '}';
    }
}
