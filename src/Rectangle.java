import java.util.Objects;

public final class Rectangle {
    private final Point topLeft;
    private final double width;
    private final double height;

    public Rectangle(Point topLeft, double width, double height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public Rectangle move(Vector vector) {
        return new Rectangle(topLeft.move(vector), width, height);
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    public Rectangle scale(double factor) {
        return new Rectangle(
                topLeft,
                width * factor,
                height * factor
        );
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.width, width) == 0 &&
                Double.compare(rectangle.height, height) == 0 &&
                topLeft.equals(rectangle.topLeft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, width, height);
    }
}