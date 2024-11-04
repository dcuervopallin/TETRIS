package analysis.comparators;

import java.util.Comparator;

public class scoreNComparator implements Comparator<meanDesvNode> {
    @Override
    public int compare(meanDesvNode o1, meanDesvNode o2) {
        return Double.compare(o2.getNScore(), o1.getNScore());
    }
}
