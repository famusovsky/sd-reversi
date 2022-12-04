/**
 * Класс, описывающий шашку.
 */
public class Checker {
    private final Coloration coloration;

    /**
     * Конструктор класса, описывающего шашку.
     *
     * @param coloration Цвет шашки.
     */
    public Checker(Coloration coloration) {
        this.coloration = coloration;
    }

    /**
     * Метод, возвращающий цвет шашки.
     *
     * @return Цвет шашки.
     */
    public Coloration getColoration() {
        return this.coloration;
    }
}
