/**
 * Класс, описывающий изменение, произошедшее на игровой доске.
 */
public class Change {
    private final int x;
    private final int y;
    private final Coloration oldColoration;
    private final Coloration newColoration;

    /**
     * Конструктор класса, описывающего изменение, произошедшее на игровой доске.
     *
     * @param x             Координата x.
     * @param y             Координата y.
     * @param oldColoration Цвет шашки на данных (x, y) до изменения.
     * @param newColoration Цвет шашки на данных (x, y) после изменения.
     */
    public Change(int x, int y, Coloration oldColoration, Coloration newColoration) {
        this.x = x;
        this.y = y;
        this.oldColoration = oldColoration;
        this.newColoration = newColoration;
    }

    /**
     * Метод, возвращающий координату x.
     *
     * @return Координата x.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Метод, возвращающий координату y.
     *
     * @return Координата y.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Метод, возвращающий цвет шашки на данных (x, y) до изменения.
     *
     * @return Цвет шашки на данных (x, y) до изменения.
     */
    public Coloration getOldColoration() {
        return this.oldColoration;
    }

    /**
     * Метод, возвращающий цвет шашки на данных (x, y) после изменения.
     *
     * @return Цвет шашки на данных (x, y) после изменения.
     */
    public Coloration getNewColoration() {
        return this.newColoration;
    }

    /**
     * Метод, возвращающий строковое представление объекта класса Change.
     *
     * @return Строковое представление объекта класса Change.
     */
    @Override
    public String toString() {
        return String.format("Change: (%d, %d) %s -> %s", this.x, this.y, this.oldColoration, this.newColoration);
    }
}
