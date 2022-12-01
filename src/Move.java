import java.util.ArrayList;

public class Move {
    private final ArrayList<Change> changes;

    Move(ArrayList<Change> changes) {
        this.changes = changes;
    }

    public ArrayList<Change> getChanges() {
        return this.changes;
    }

    public Coordinate getLastChangeCoordinate() {
        return new Coordinate(changes.get(changes.size() - 1).getX(), changes.get(changes.size() - 1).getY());
    }
}
