import java.util.ArrayList;

/**
 * Класс, описывающий компьютерного игорока.
 */
public class AIPlayer extends Player {
    private final boolean isAdvanced;

    /**
     * Конструктор класса, описывающего компьютерного игорока.
     *
     * @param coloration Цвет фишек компьютера.
     * @param isAdvanced Параметр, указывающий, будет ли компьютер использовать продвинутую стратегию.
     */
    public AIPlayer(Coloration coloration, boolean isAdvanced) {
        super(coloration);
        this.isAdvanced = isAdvanced;
    }

    private double countPoints(Coordinate coordinate, ArrayList<Change> changes) {
        double currentPoints;
        if ((coordinate.getX() == 0 || coordinate.getX() == 7) && (coordinate.getY() == 0 || coordinate.getY() == 7)) {
            currentPoints = 0.8;
        } else if (coordinate.getX() == 0 || coordinate.getX() == 7 || coordinate.getY() == 0 || coordinate.getY() == 7) {
            currentPoints = 0.4;
        } else {
            currentPoints = 0;
        }
        for (int i = 0; i < changes.size() - 1; ++i) {
            var change = changes.get(i);
            if (change.getX() == 0 || change.getX() == 7 || change.getY() == 0 || change.getY() == 7) {
                currentPoints += 2;
            } else {
                currentPoints += 1;
            }
        }
        return currentPoints;
    }

    private Move getBestNormalMove(ArrayList<Possibility> possibilities) {
        double maxPoints = -1;
        double currentPoints;
        Move bestMove = null;
        for (var possibility : possibilities) {
            var coordinate = possibility.getCoordinate();
            var changes = possibility.getChanges();
            currentPoints = countPoints(coordinate, changes);
            if (currentPoints > maxPoints) {
                maxPoints = currentPoints;
                bestMove = new Move(changes);
            }
        }
        return bestMove;
    }

    private Move getBestAdvancedMove(ArrayList<Possibility> possibilities) {
        double maxPoints = -1000;
        double currentPoints;
        Move bestMove = null;
        for (var possibility : possibilities) {
            var coordinate = possibility.getCoordinate();
            var changes = possibility.getChanges();
            var opponentsNextMoveMaxPoints = 0.0;
            currentPoints = countPoints(coordinate, changes);
            GameBoard.addMove(new Move(changes));
            for (var opponentsPossibility : GameBoard.getPossibilities(coloration == Coloration.BLACK ? Coloration.BLACK : Coloration.WHITE)) {
                var opponentsCoordinate = opponentsPossibility.getCoordinate();
                var opponentsChanges = opponentsPossibility.getChanges();
                var opponentsNextMoveCurrentPoints = countPoints(opponentsCoordinate, opponentsChanges);
                if (opponentsNextMoveCurrentPoints > opponentsNextMoveMaxPoints) {
                    opponentsNextMoveMaxPoints = opponentsNextMoveCurrentPoints;
                }
            }
            currentPoints -= opponentsNextMoveMaxPoints;
            GameBoard.goBack();
            if (currentPoints > maxPoints) {
                maxPoints = currentPoints;
                bestMove = new Move(changes);
            }
        }
        return bestMove;
    }

    @Override
    public Move makeAMove(ArrayList<Possibility> possibilities) {
        if (isAdvanced) {
            return getBestAdvancedMove(possibilities);
        } else {
            return getBestNormalMove(possibilities);
        }
    }
}
