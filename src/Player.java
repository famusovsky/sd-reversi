import java.util.ArrayList;

/**
 * Абстрактный класс, представляющий игрока.
 */
abstract class Player {
    protected final Coloration coloration;

    protected Player(Coloration coloration) {
        this.coloration = coloration;
    }

    /**
     * Метод, возвращающий цвет за который играет игрок.
     *
     * @return Цвет игрока.
     */
    public Coloration getColoration() {
        return coloration;
    }

    /**
     * Метод, возвращающий ход, который сделает игрок.
     *
     * @param possibilities Возможные ходы.
     * @return Ход, который сделает игрок.
     */
    public abstract Move makeAMove(ArrayList<Possibility> possibilities);
}