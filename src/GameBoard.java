import java.util.*;

/**
 * Класс, представляющий игровое поле для игры в 'Реверси'.
 */
public final class GameBoard {
    private GameBoard() {
    }

    private static int currentMove = 0;
    private static final Checker[][] checkers = new Checker[8][8];
    private static final ArrayList<Move> moves = new ArrayList<>(60);
    private static final Map<Coloration, Integer> checkersCount = new EnumMap<>(Coloration.class);

    /**
     * Метод, подготавливающий игровое поле к началу игры в 'Реверси'.
     */
    public static void refresh() {
        moves.clear();
        checkersCount.clear();
        checkersCount.put(Coloration.WHITE, 2);
        checkersCount.put(Coloration.BLACK, 2);
        checkersCount.put(Coloration.NONE, 60);
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

    /**
     * Метод, добавляющий совершённый ход в список ходов и применяющий к игоровой доске изменения, включённые в него.
     *
     * @param move Совершённый ход.
     */
    public static void addMove(Move move) {
        for (var change : move.getChanges()) {
            checkers[change.getX()][change.getY()] = new Checker(change.getNewColoration());
            checkersCount.put(change.getOldColoration(), checkersCount.get(change.getOldColoration()) - 1);
            checkersCount.put(change.getNewColoration(), checkersCount.get(change.getNewColoration()) + 1);
        }
        if (currentMove < moves.size()) {
            moves.subList(currentMove, moves.size()).clear();
        }
        moves.add(move);
        ++currentMove;
    }

    /**
     * Метод, позволяющий перейти к состоянию игровой доски, соответствующей предыдущему ходу (при его наличии).
     */
    public static void goBack() {
        if (currentMove > 0) {
            --currentMove;
            for (var change : moves.get(currentMove).getChanges()) {
                checkers[change.getX()][change.getY()] = new Checker(change.getOldColoration());
                checkersCount.put(change.getOldColoration(), checkersCount.get(change.getOldColoration()) + 1);
                checkersCount.put(change.getNewColoration(), checkersCount.get(change.getNewColoration()) - 1);
            }
        } else throw new IndexOutOfBoundsException();
    }

    /**
     * Метод, позволяющий перейти к состоянию игровой доски, соответствующей следующему ходу (при его наличии).
     */
    public static void goForward() {
        if (currentMove < moves.size()) {
            for (var change : moves.get(currentMove).getChanges()) {
                checkers[change.getX()][change.getY()] = new Checker(change.getNewColoration());
                checkersCount.put(change.getOldColoration(), checkersCount.get(change.getOldColoration()) - 1);
                checkersCount.put(change.getNewColoration(), checkersCount.get(change.getNewColoration()) + 1);
            }
            ++currentMove;
        } else throw new IndexOutOfBoundsException();
    }

    /**
     * Метод, возвращающий список возможных ходов для игрока, играющего за указанный цвет.
     *
     * @param coloration Цвет данного игрока.
     * @return Список возможных ходов для данного игрока.
     */
    public static ArrayList<Possibility> getPossibilities(Coloration coloration) {
        ArrayList<Possibility> possibilities = new ArrayList<>();
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
                        possibilities.add(new Possibility(new Coordinate(i, j), changes));
                    }
                }
            }
        }
        return possibilities;
    }

    /**
     * Метод, возвращающий цвет игрока, имеющего большее число шашек на игровой доске.
     *
     * @return Цвет игрока, имеющего большее число шашек на игровой доске.
     */
    public static Coloration getCurrentWinner() {
        return checkersCount.get(Coloration.WHITE) > checkersCount.get(Coloration.BLACK) ? Coloration.WHITE :
                checkersCount.get(Coloration.WHITE) < checkersCount.get(Coloration.BLACK) ? Coloration.BLACK :
                        Coloration.NONE;
    }

    /**
     * Метод, возвращающий количество шашек данного цвета на игровой доске.
     *
     * @param coloration Цвет шашек.
     * @return Количество шашек данного цвета на игровой доске.
     */
    public static int getScore(Coloration coloration) {
        return checkersCount.get(coloration);
    }

    /**
     * Метод, возвращающий строковое представление игровой доски.
     *
     * @return Строковое представление игровой доски.
     */
    public static String getString() {
        StringBuilder result = new StringBuilder();
        for (int y = 7; y >= 0; --y) {
            result.append(y + 1).append(" ");
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
            result.append(" ").append(x + 1).append(" ");
        }
        result.append("\n");
        return result.toString();
    }
}