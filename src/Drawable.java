public interface Drawable {
    void draw();
    Drawable move(Vector vector);
    void writeToStream(FigureOutput out) throws java.io.IOException;
}