public class Checker {
    private final Coloration coloration;

    public Checker(Coloration coloration) {
        this.coloration = coloration;
    }

    public Coloration getColoration() {
        return this.coloration;
    }
    public Coloration changeColoration() {
        if (this.coloration == Coloration.BLACK) {
            return Coloration.WHITE;
        } else {
            return Coloration.BLACK;
        }
    }
}
