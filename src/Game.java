public class Game {
    private final Player[] players = new Player[2];

    // TODO advanced or not
    protected Game() {
        System.out.println("За какой цвет вы хотите играть? (белый = W / чёрный = B)");
        boolean isRealPlayerFirst;
        var input = Main.scanner.nextLine();
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
        if (isRealPlayerFirst) {
            players[0] = new RealPlayer(Coloration.WHITE);
            players[1] = new AIPlayer(Coloration.BLACK, false);
        } else {
            players[0] = new AIPlayer(Coloration.WHITE, false);
            players[1] = new RealPlayer(Coloration.BLACK);
        }
    }

    protected Coloration play() {
        GameBoard.refresh();
        int i = 0;
        while (true) {
            System.out.println(GameBoard.getString());
            System.out.println("Ходит " + (players[i % 2].getColoration() == Coloration.WHITE ? "белый" : "чёрный") + " игрок.");
            var possibilities = GameBoard.getPossibilities(players[i % 2].getColoration());
            if (possibilities.isEmpty()) {
                if (GameBoard.getPossibilities(players[(i + 1) % 2].getColoration()).isEmpty()) {
                    break;
                }
            } else {
                var move = players[i % 2].makeAMove(possibilities);
                GameBoard.addMove(move);
            }
            ++i;
        }
        System.out.println(GameBoard.getString());
        return GameBoard.getCurrentWinner();
    }
}
