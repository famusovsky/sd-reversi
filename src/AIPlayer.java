import java.util.ArrayList;
import java.util.Map;

public class AIPlayer extends Player {
    private boolean isAdvanced;

    protected AIPlayer(Coloration coloration, boolean isAdvanced) {
        super(coloration);
        this.isAdvanced = isAdvanced;
    }

    private Move getBestNormalMove(Map<Coordinate, ArrayList<Change>> possibilities) {
        double maxPoints = -1;
        double currentPoints;
        Move bestMove = null;
        for (var possibility : possibilities.entrySet()) {
            var coordinate = possibility.getKey();
            var changes = possibility.getValue();
            if ((coordinate.getX() == 0 || coordinate.getX() == 7) && (coordinate.getY() == 0 || coordinate.getY() == 7)) {
                currentPoints = 0.8;
            } else if (coordinate.getX() == 0 || coordinate.getX() == 7 || coordinate.getY() == 0 || coordinate.getY() == 7) {
                currentPoints = 0.4;
            } else {
                currentPoints = 0;
            }
            for (var change : changes) {
                if (change.getX() == 0 || change.getX() == 7 || change.getY() == 0 || change.getY() == 7) {
                    currentPoints += 2;
                } else {
                    currentPoints += 1;
                }
            }
            if (currentPoints > maxPoints) {
                maxPoints = currentPoints;
                bestMove = new Move(changes);
            }
        }
        return bestMove;
    }

    private Move getBestAdvancedMove(Map<Coordinate, ArrayList<Change>> possibilities) {
        double maxPoints = -1;
        double currentPoints;
        Move bestMove = null;
        for (var possibility : possibilities.entrySet()) {
            var coordinate = possibility.getKey();
            var changes = possibility.getValue();
            if ((coordinate.getX() == 0 || coordinate.getX() == 7) && (coordinate.getY() == 0 || coordinate.getY() == 7)) {
                currentPoints = 0.8;
            } else if (coordinate.getX() == 0 || coordinate.getX() == 7 || coordinate.getY() == 0 || coordinate.getY() == 7) {
                currentPoints = 0.4;
            } else {
                currentPoints = 0;
            }
            for (var change : changes) {
                if (change.getX() == 0 || change.getX() == 7 || change.getY() == 0 || change.getY() == 7) {
                    currentPoints += 2;
                } else {
                    currentPoints += 1;
                }
            }
            // Добавить ход в геймборд - посчитать баллы для противника вычесть - откатиться
            if (currentPoints > maxPoints) {
                maxPoints = currentPoints;
                bestMove = new Move(changes);
            }
        }
        return bestMove;
    }

    @Override
    public Move makeAMove(Map<Coordinate, ArrayList<Change>> possibilities) {
        if (isAdvanced) {
            return new Move(possibilities.get(possibilities.keySet().toArray()[0]));
        } else {
            return getBestNormalMove(possibilities);
        }
    }
}
