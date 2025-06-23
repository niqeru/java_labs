import java.util.Objects;

public final class Circle implements Shape {
    private final Vector center;
    private final double radius;

    public Circle(Vector center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public void draw() {
        // Пока пусто
    }

    @Override
    public Circle move(Vector vector) {
        return new Circle(center.plus(vector), radius);
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
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