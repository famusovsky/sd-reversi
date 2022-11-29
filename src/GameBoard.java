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
                checkers[i][j] = new Checker(Coloration.NONE);
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
    // TODO: Current version is written by Copilot. It is not tested.
    public static Map<Coordinate, ArrayList<Change>> getPossibilities(Coloration coloration) {
        Map<Coordinate, ArrayList<Change>> Possibilities = new HashMap<>();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (checkers[i][j].getColoration() == Coloration.NONE) {
                    ArrayList<Change> changes = new ArrayList<>();
                    for (int k = -1; k <= 1; ++k) {
                        for (int l = -1; l <= 1; ++l) {
                            if (k != 0 || l != 0) {
                                int x = i + k;
                                int y = j + l;
                                while (x >= 0 && x < 8 && y >= 0 && y < 8 && checkers[x][y].getColoration() != Coloration.NONE && checkers[x][y].getColoration() != coloration) {
                                    x += k;
                                    y += l;
                                }
                                if (x >= 0 && x < 8 && y >= 0 && y < 8 && checkers[x][y].getColoration() != Coloration.NONE && checkers[x][y].getColoration() == coloration) {
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
                        changes.add(new Change(i, j, Coloration.NONE, coloration));
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
                if (checker.getColoration() != Coloration.NONE) {
                    if (checker.getColoration() == Coloration.WHITE) {
                        ++white;
                    } else {
                        ++black;
                    }
                }
            }
        }
        return white > black ? Coloration.WHITE : (black > white ? Coloration.BLACK : Coloration.NONE);
    }
    public static String getString() {
        StringBuilder result = new StringBuilder();
        for (int y = 7; y >= 0; --y) {
            result.append((y + 1) + " ");
            for (int x = 0; x < 8; ++x) {
                if (checkers[x][y].getColoration() == Coloration.NONE) {
                    result.append("[ ]");
                } else {
                    result.append(checkers[x][y].getColoration() == Coloration.WHITE ? "[○]" : "[◙]");
                }
            }
            result.append("\n");
        }
        result.append("  ");
        for (int x = 0; x < 8; ++x) {
            result.append(" " + (x + 1) + " ");
        }
        result.append("\n");
        return result.toString();
    }
}