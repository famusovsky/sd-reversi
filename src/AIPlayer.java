import java.util.ArrayList;
import java.util.Map;

public class AIPlayer extends Player {
    protected AIPlayer(Coloration coloration) {
        super(coloration);
    }
    // TODO
    @Override
    public Move makeAMove(Map<Coordinate, ArrayList<Change>> possibilities) {
        return new Move(possibilities.get(possibilities.keySet().toArray()[0]));
    }
}
