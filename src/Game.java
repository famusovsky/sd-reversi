public class Game {
    private final Player[] players = new Player[2];

    protected Game(boolean isRealPlayerFirst) {
        if (isRealPlayerFirst) {
            players[0] = new RealPlayer(Coloration.WHITE);
            players[1] = new AIPlayer(Coloration.BLACK);
        } else {
            players[0] = new AIPlayer(Coloration.WHITE);
            players[1] = new RealPlayer(Coloration.BLACK);
        }
    }

    protected Coloration play() {
        while (GameBoard.getPossibilities().size() != 0) {
            for (Player player : players) {
                GameBoard.addMove(player.makeAMove());
            }
        }
        return CheckWinner();
    }

    private Coloration CheckWinner() {
        // TODO
    }
}
