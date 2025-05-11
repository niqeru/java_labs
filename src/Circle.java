import java.util.Objects;

public final class Circle {
    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public Circle move(Vector vector) {
        return new Circle(center.move(vector), radius);
    }

    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    public Circle scale(double factor) {
        return new Circle(center, radius * factor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0 &&
                center.equals(circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }
}