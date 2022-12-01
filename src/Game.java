public final class Game {
    private static final Player[] players = new Player[2];

    private Game() {
    }

    public static void start() {
        boolean isRealPlayerFirst;
        boolean isAdvanced;
        String input;
        System.out.println("За какой цвет вы хотите играть? (белый = W / чёрный = B)");
        input = Main.scanner.nextLine();
        while (true) {
            switch (input) {
                case "W" -> isRealPlayerFirst = true;
                case "B" -> isRealPlayerFirst = false;
                default -> {
                    System.out.println("Неверный ввод. Попробуйте ещё раз.");
                    input = Main.scanner.nextLine();
                    continue;
                }
            }
            break;
        }
        System.out.println("На каком уровне сложности вы хотите играть? (обычный = N / продвинутый = A)");
        input = Main.scanner.nextLine();
        while (true) {
            switch (input) {
                case "N" -> isAdvanced = false;
                case "A" -> isAdvanced = true;
                default -> {
                    System.out.println("Неверный ввод. Попробуйте ещё раз.");
                    input = Main.scanner.nextLine();
                    continue;
                }
            }
            break;
        }
        if (isRealPlayerFirst) {
            players[0] = new RealPlayer(Coloration.WHITE);
            players[1] = new AIPlayer(Coloration.BLACK, isAdvanced);
        } else {
            players[0] = new AIPlayer(Coloration.WHITE, isAdvanced);
            players[1] = new RealPlayer(Coloration.BLACK);
        }
    }

    public static void play() {
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
