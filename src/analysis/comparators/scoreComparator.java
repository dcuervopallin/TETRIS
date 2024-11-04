package analysis.comparators;

import java.util.Comparator;

public class scoreComparator implements Comparator<meanDesvNode> {
    @Override
    public int compare(meanDesvNode o1, meanDesvNode o2) {
        return Double.compare(o2.getMeanScore(), o1.getMeanScore());
    }
}
