import java.util.Objects;

public final class Ellipse implements Shape {
    private final Vector center;
    private final double a; // большая полуось
    private final double b; // малая полуось
    private final double angleRadians; // угол поворота

    public Ellipse(Vector center, double a, double b, double angleRadians) {
        this.center = center;
        this.a = a;
        this.b = b;
        this.angleRadians = angleRadians;
    }

    public Ellipse(Vector f1, Vector f2, double a) {
        this.center = f1.plus(f2).multiply(0.5);
        this.a = a;
        double c = f1.minus(f2).length() / 2;
        this.b = Math.sqrt(a * a - c * c);
        this.angleRadians = f2.minus(f1).angleX();
    }

    @Override
    public void draw() {
        // Пока пусто
    }

    @Override
    public Ellipse move(Vector v) {
        return new Ellipse(center.plus(v), a, b, angleRadians);
    }

    @Override
    public double area() {
        return Math.PI * a * b;
    }

    @Override
    public double perimeter() {
        // Приближённая формула Раману
        return Math.PI * (3 * (a + b) - Math.sqrt((3 * a + b) * (a + 3 * b)));
    }

    @Override
    public Ellipse scale(double factor) {
        return new Ellipse(center, a * factor, b * factor, angleRadians);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ellipse ellipse = (Ellipse) obj;
        return Double.compare(ellipse.a, a) == 0 &&
                Double.compare(ellipse.b, b) == 0 &&
                Double.compare(ellipse.angleRadians, angleRadians) == 0 &&
                center.equals(ellipse.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, a, b, angleRadians);
    }
}