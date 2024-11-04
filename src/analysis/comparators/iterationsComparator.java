package analysis.comparators;
import java.util.Comparator;

public class iterationsComparator implements Comparator<meanDesvNode> {
    @Override
    public int compare(meanDesvNode o1, meanDesvNode o2) {
        return Double.compare(o2.getMeanIteration(), o1.getMeanIteration());
    }
}
