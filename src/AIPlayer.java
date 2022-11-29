import java.util.ArrayList;
import java.util.Map;

public class AIPlayer extends Player {
    protected AIPlayer(Coloration coloration) {
        super(coloration);
    }
    // TODO
    @Override
    public Move makeAMove(ArrayList<Possibility> possibilities) {
        return new Move(possibilities.get(0).getChanges());
    }
}
