import java.util.ArrayList;

/**
 * Класс, представляющий возможный ход.
 */
public class Possibility {
    private final Coordinate coordinate;
    private final ArrayList<Change> changes;

    /**
     * Конструктор класса, представляющего возможный ход.
     *
     * @param coordinate Координата, с которой начинается ход.
     * @param changes    Изменения, произошедшие в ходе.
     */
    Possibility(Coordinate coordinate, ArrayList<Change> changes) {
        this.coordinate = coordinate;
        this.changes = changes;
    }

    /**
     * Метод, возвращающий координату, с которой начинается ход.
     *
     * @return Координата, с которой начинается ход.
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    /**
     * Метод, возвращающий список изменений, произошедших в ходе.
     *
     * @return Список изменений, произошедших в ходе.
     */
    public ArrayList<Change> getChanges() {
        return this.changes;
    }
}
