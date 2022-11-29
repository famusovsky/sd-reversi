import SimpleClasses.Coloration;
import SimpleClasses.Move;
import SimpleClasses.Possibility;

import java.util.ArrayList;

abstract class Player {
    protected final Coloration coloration;
    protected Player (Coloration coloration) {
        this.coloration = coloration;
    }
    public Coloration getColoration() {
        return coloration;
    }
    abstract Move makeAMove(ArrayList<Possibility> possibilities);
}