/**
 * Класс, описывающий координаты шашки на игровой доске.
 */
public class Coordinate {
    private final int x;
    private final int y;

    /**
     * Конструктор класса, описывающего координаты шашки на игровой доске.
     *
     * @param x Координата x.
     * @param y Координата y.
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод, возвращающий координату x.
     *
     * @return Координата x.
     */
    public int getX() {
        return x;
    }

    /**
     * Метод, возвращающий координату y.
     *
     * @return Координата y.
     */
    public int getY() {
        return y;
    }

    /**
     * Метод, возвращающий строковое представление координат.
     *
     * @return Строковое представление координат.
     */
    @Override
    public String toString() {
        return "(" + (x + 1) + ", " + (y + 1) + ")";
    }
}