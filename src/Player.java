import java.util.ArrayList;
import java.util.Map;

abstract class Player {
    private final Coloration coloration;
    protected Player (Coloration coloration) {
        this.coloration = coloration;
    }
    protected Coloration getColoration() {
        return coloration;
    }
    abstract Move makeAMove(Map<Coordinate, ArrayList<Change>> possibilities);
}