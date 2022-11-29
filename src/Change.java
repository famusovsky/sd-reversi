public class Change {
    private final int x;
    private final int y;
    private final Coloration oldColoration;
    private final Coloration newColoration;

    public Change(int x, int y, Coloration oldColoration, Coloration newColoration) {
        this.x = x;
        this.y = y;
        this.oldColoration = oldColoration;
        this.newColoration = newColoration;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Coloration getOldColoration() {
        return this.oldColoration;
    }

    public Coloration getNewColoration() {
        return this.newColoration;
    }

    @Override
    public String toString() {
        return String.format("Change: (%d, %d) %s -> %s", this.x, this.y, this.oldColoration, this.newColoration);
    }
}
