import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class RealPlayer extends Player {
    private final boolean isItPossibleToCheat;

    public RealPlayer(Coloration coloration, boolean isItPossibleToCheat) {
        super(coloration);
        this.isItPossibleToCheat = isItPossibleToCheat;
    }

    private void watchHistory() {
        while (true) {
            if (isItPossibleToCheat) {
                System.out.println("Чтобы сделать ход с текущего места введите 'M'. ");
            }
            System.out.println("Если вы хотите перейти на ход назад / вперёд -- введите 'B' / 'F'. " +
                    "Если хотите выйти из истории введите 'E'.");
            var input = Main.scanner.nextLine();
            switch (input) {
                case "M":
                    if (isItPossibleToCheat) {
                        System.out.println("Неверный ввод. Попробуйте ещё раз.");
                        continue;
                    }
                    break;
                case "B":
                    try {
                        GameBoard.goBack();
                        GameBoard.goBack();
                        System.out.println(GameBoard.getString());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Невозможно перейти на ход назад.");
                    }
                    continue;
                case "F":
                    try {
                        GameBoard.goForward();
                        GameBoard.goForward();
                        System.out.println(GameBoard.getString());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Невозможно перейти на ход вперёд.");
                    }
                    continue;
                case "E":
                    try {
                        while (true) {
                            GameBoard.goForward();
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(GameBoard.getString());
                        break;
                    }
                default:
                    System.out.println("Неверный ввод. Попробуйте ещё раз.");
                    continue;
            }
            break;
        }
    }

    private String possibilitiesToString(ArrayList<Possibility> possibilities) {
        StringBuilder output = new StringBuilder();
        output.append("Выберите ход: ");
        for (int i = 0; i < possibilities.size(); ++i) {
            output.append(String.format("%n%d. %s", i + 1, possibilities.get(i).getCoordinate()));
        }
        return output.toString();
    }

    @Override
    public Move makeAMove(ArrayList<Possibility> possibilities) {
        System.out.println(possibilitiesToString(possibilities));
        System.out.println("Чтобы посмотреть историю игры введите 'H'.");
        while (true) {
            var input = Main.scanner.nextLine();
            if (input.equals("H")) {
                watchHistory();
                possibilities = GameBoard.getPossibilities(getColoration());
                System.out.println(possibilitiesToString(possibilities));
                input = Main.scanner.nextLine();
            }
            try {
                var index = Integer.parseInt(input);
                if (index < 1 || index > possibilities.size()) {
                    System.out.println("Неверный ввод. Попробуйте ещё раз.");
                    continue;
                }
                return new Move(possibilities.get(index - 1).getChanges());
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Попробуйте ещё раз.");
            }
        }
    }
}