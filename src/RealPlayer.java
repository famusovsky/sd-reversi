import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class RealPlayer extends Player {
    public RealPlayer(Coloration coloration) {
        super(coloration);
    }
    @Override
    public Move makeAMove(Map<Coordinate, ArrayList<Change>> possibilities) {
        ArrayList<Coordinate> coordinates = new ArrayList<>(possibilities.keySet());
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < coordinates.size(); ++i) {
            output.append(String.format("%d. %s%n", i + 1, coordinates.get(i)));
        }
        output.append("Выберите ход: ");
        System.out.println(output);
        while (true) {
            int input = Main.scanner.nextInt();
            if (input > 0 && input <= coordinates.size()) {
                return new Move(possibilities.get(coordinates.get(input - 1)));
            }
            System.out.println("Неверный ввод. Попробуйте ещё раз.");
        }
    }
}
