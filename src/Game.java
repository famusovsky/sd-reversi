/**
 * Статический класс, содержащий методы для работы с игрой 'Реверси'.
 */
public final class Game {
    private static final Player[] players = new Player[2];

    private Game() {
    }

    private static boolean getCorrectInput(String correct, String incorrect) {
        String input = Main.scanner.nextLine();
        while (true) {
            if (input.equals(incorrect)) {
                return false;
            }
            if (input.equals(correct)) {
                return true;
            }
            System.out.println("Неверный ввод. Попробуйте ещё раз.");
            input = Main.scanner.nextLine();
        }
    }

    /**
     * Метод, запускающий игру 'Реверси' и получающий данные о параметрах данной партии.
     */
    public static void start() {
        boolean isThereTwoPlayers;
        System.out.println("Вы хотете играть против компьютера или другого игрока? (1 / 2)");
        isThereTwoPlayers = getCorrectInput("2", "1");
        if (isThereTwoPlayers) {
            players[0] = new RealPlayer(Coloration.WHITE, false);
            players[1] = new RealPlayer(Coloration.BLACK, false);
        } else {
            boolean isRealPlayerFirst;
            boolean isAdvanced;
            System.out.println("За какой цвет вы хотите играть? (белый = W / чёрный = B)");
            isRealPlayerFirst = getCorrectInput("W", "B");
            System.out.println("C каким компьютерным игроком вы хотите играть? (обычный = N / продвинутый = A)");
            isAdvanced = getCorrectInput("A", "N");
            if (isRealPlayerFirst) {
                players[0] = new RealPlayer(Coloration.WHITE, true);
                players[1] = new AIPlayer(Coloration.BLACK, isAdvanced);
            } else {
                players[0] = new AIPlayer(Coloration.WHITE, isAdvanced);
                players[1] = new RealPlayer(Coloration.BLACK, true);
            }
        }
        play();
    }

    private static void play() {
        GameBoard.refresh();
        int i = 0;
        while (true) {
            System.out.println(GameBoard.getString());
            System.out.println("Ходит " + (players[i % 2].getColoration() == Coloration.WHITE ? "белый" : "чёрный") + " игрок.");
            var possibilities = GameBoard.getPossibilities(players[i % 2].getColoration());
            if (possibilities.isEmpty()) {
                System.out.println("Нет возможных ходов. Ход переходит к другому игроку.");
                if (GameBoard.getPossibilities(players[(i + 1) % 2].getColoration()).isEmpty()) {
                    break;
                }
            } else {
                var move = players[i % 2].makeAMove(possibilities);
                System.out.println("Игрок сделал ход: " + move.getLastChangeCoordinate() + "\n");
                GameBoard.addMove(move);
            }
            ++i;
        }
        System.out.println(GameBoard.getString());
        var winner = GameBoard.getCurrentWinner();
        if (winner == Coloration.NONE) {
            System.out.println("Ничья!");
        } else {
            System.out.println("Победил " + (winner == Coloration.WHITE ? "белый" : "чёрный") +
                    " игрок, набрав " + GameBoard.getScore(winner) + " баллов!");
        }
    }
}
