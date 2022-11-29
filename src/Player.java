import java.util.ArrayList;
import java.util.Map;

abstract class Player {
    protected final Coloration coloration;
    protected Player (Coloration coloration) {
        this.coloration = coloration;
    }
    public Coloration getColoration() {
        return coloration;
    }
    abstract Move makeAMove(Map<Coordinate, ArrayList<Change>> possibilities);
}