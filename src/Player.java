abstract class Player {
    private final Coloration coloration;
    protected Player (Coloration coloration) {
        this.coloration = coloration;
    }
    protected Coloration getColoration() {
        return coloration;
    }
    abstract Move makeAMove();
}