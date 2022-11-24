import java.util.*;

public final class GameBoard {
    private GameBoard() {
    }
    private static final Checker[][] checkers = new Checker[8][8];
    private static final Map<Coordinates, ArrayList<Change>> Possibilities = new HashMap<>();
    private static final ArrayList<Move> moves = new ArrayList<>(60);
    public static void refresh() {
        Possibilities.clear();
        checkers[4][5] = new Checker(Coloration.WHITE);
        checkers[5][4] = new Checker(Coloration.WHITE);
        checkers[4][4] = new Checker(Coloration.BLACK);
        checkers[5][5] = new Checker(Coloration.BLACK);
    }
    // TODO
    public static void addMove(Move move) {
        moves.add(move);
    }
    public static Map<Coordinates, ArrayList<Change>> getPossibilities() {
        return Possibilities;
    }
}