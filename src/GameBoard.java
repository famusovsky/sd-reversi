import java.util.*;

public final class GameBoard {
    private GameBoard() {
    }

    private static int currentMove = 0;
    private static final Checker[][] checkers = new Checker[8][8];
    private static final ArrayList<Move> moves = new ArrayList<>(60);

    public static void refresh() {
        moves.clear();
        currentMove = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                checkers[i][j] = null;
            }
        }
        checkers[3][4] = new Checker(Coloration.WHITE);
        checkers[4][3] = new Checker(Coloration.WHITE);
        checkers[3][3] = new Checker(Coloration.BLACK);
        checkers[4][4] = new Checker(Coloration.BLACK);
    }

    public static void addMove(Move move) {
        for (var change : move.getChanges()) {
            checkers[change.getX()][change.getY()] = new Checker(change.getNewColoration());
        }
        if (currentMove < moves.size()) {
            moves.subList(currentMove, moves.size()).clear();
        }
        moves.add(move);
        ++currentMove;
    }

    public static void goBack() {
        if (currentMove > 0) {
            --currentMove;
            for (var change : moves.get(currentMove).getChanges()) {
                checkers[change.getX()][change.getY()] = new Checker(change.getOldColoration());
            }
        }
    }

    public static void goForward() {
        if (currentMove < moves.size()) {
            for (var change : moves.get(currentMove).getChanges()) {
                checkers[change.getX()][change.getY()] = new Checker(change.getNewColoration());
            }
            ++currentMove;
        }
    }
    // TODO: Current vesrion is written by Copilot. It is not tested.
    public static Map<Coordinate, ArrayList<Change>> getPossibilities(Coloration coloration) {
        Map<Coordinate, ArrayList<Change>> Possibilities = new HashMap<>();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (checkers[i][j] == null) {
                    ArrayList<Change> changes = new ArrayList<>();
                    for (int k = -1; k <= 1; ++k) {
                        for (int l = -1; l <= 1; ++l) {
                            if (k != 0 || l != 0) {
                                int x = i + k;
                                int y = j + l;
                                while (x >= 0 && x < 8 && y >= 0 && y < 8 && checkers[x][y] != null && checkers[x][y].getColoration() != coloration) {
                                    x += k;
                                    y += l;
                                }
                                if (x >= 0 && x < 8 && y >= 0 && y < 8 && checkers[x][y] != null && checkers[x][y].getColoration() == coloration) {
                                    x -= k;
                                    y -= l;
                                    while (x != i || y != j) {
                                        changes.add(new Change(x, y, checkers[x][y].getColoration(), coloration));
                                        x -= k;
                                        y -= l;
                                    }
                                }
                            }
                        }
                    }
                    if (!changes.isEmpty()) {
                        changes.add(new Change(i, j, null, coloration));
                        Possibilities.put(new Coordinate(i, j), changes);
                    }
                }
            }
        }
        return Possibilities;
    }

    public static Coloration getCurrentWinner() {
        int white = 0;
        int black = 0;
        for (var row : checkers) {
            for (var checker : row) {
                if (checker != null) {
                    if (checker.getColoration() == Coloration.WHITE) {
                        ++white;
                    } else {
                        ++black;
                    }
                }
            }
        }
        return white > black ? Coloration.WHITE : Coloration.BLACK;
    }
    public static String getString() {
        StringBuilder result = new StringBuilder();
        for (int y = 7; y >= 0; --y) {
            for (int x = 0; x < 8; ++x) {
                if (checkers[x][y] == null) {
                    result.append("[ ]");
                } else {
                    result.append(checkers[x][y].getColoration() == Coloration.WHITE ? "[W]" : "[B]");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}