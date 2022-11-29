import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class RealPlayer extends Player {
    public RealPlayer(Coloration coloration) {
        super(coloration);
    }

    @Override
    public Move makeAMove(ArrayList<Possibility> possibilities) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < possibilities.size(); ++i) {
            output.append(String.format("%d. %s%n", i + 1, possibilities.get(i).getCoordinate()));
        }
        output.append("Выберите ход: ");
        System.out.println(output);
        while (true) {
            int input = Main.scanner.nextInt();
            if (input > 0 && input <= possibilities.size()) {
                return new Move(possibilities.get(input - 1).getChanges());
            }
            System.out.println("Неверный ввод. Попробуйте ещё раз.");
        }
    }
}