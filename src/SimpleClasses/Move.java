package SimpleClasses;

import java.util.ArrayList;

public class Move {
    private final ArrayList<Change> changes;

    public Move(ArrayList<Change> changes) {
        this.changes = changes;
    }

    public ArrayList<Change> getChanges() {
        return this.changes;
    }
}
