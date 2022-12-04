import java.util.ArrayList;

/**
 * Класс, представляющий ход.
 */
public class Move {
    private final ArrayList<Change> changes;

    /**
     * Конструктор класса, представляющего ход.
     *
     * @param changes Изменения, произошедшие на игровой доске.
     */
    public Move(ArrayList<Change> changes) {
        this.changes = changes;
    }

    /**
     * Метод, возвращающий список изменений, произошедших в ходе.
     *
     * @return Список изменений, произошедших в ходе.
     */
    public ArrayList<Change> getChanges() {
        return this.changes;
    }

    /**
     * Метод, возвращающий последнее изменение, произошедшее в ходе.
     *
     * @return Последнее изменение, произошедшее в ходе.
     */
    public Coordinate getLastChangeCoordinate() {
        return new Coordinate(changes.get(changes.size() - 1).getX(), changes.get(changes.size() - 1).getY());
    }
}
