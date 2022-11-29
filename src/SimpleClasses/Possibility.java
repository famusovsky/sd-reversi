package SimpleClasses;

import java.util.ArrayList;

public class Possibility {
    private final Coordinate coordinate;
    private final ArrayList<Change> changes;

    public Possibility(Coordinate coordinate, ArrayList<Change> changes) {
        this.coordinate = coordinate;
        this.changes = changes;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public ArrayList<Change> getChanges() {
        return this.changes;
    }
}
