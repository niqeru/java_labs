import java.util.Objects;

public final class Ellipse {
    private final Vector center;
    private final double a; // большая полуось
    private final double b; // малая полуось
    private final double angleRadians; // угол поворота


    public Ellipse(Vector center, double a, double b, double angleRadians) {
        if (a <= 0 || b <= 0) throw new IllegalArgumentException("Полуоси должны быть положительными");
        this.center = center;
        this.a = a;
        this.b = b;
        this.angleRadians = angleRadians;
    }

    public Ellipse(Vector f1, Vector f2, double a) {
        double distance = f1.minus(f2).length();
        double c = distance / 2;
        if (a <= c) throw new IllegalArgumentException("Невозможно построить эллипс с такими параметрами");

        this.center = f1.plus(f2).multiply(0.5);
        this.a = a;
        this.b = Math.sqrt(a * a - c * c);
        this.angleRadians = f2.minus(f1).angleX();
    }

    public Vector getCenter() {
        return center;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getAngleRadians() {
        return angleRadians;
    }

    public Ellipse move(Vector v) {
        return new Ellipse(center.plus(v), a, b, angleRadians);
    }

    public Ellipse scale(double factor) {
        if (factor <= 0) throw new IllegalArgumentException("Фактор масштабирования должен быть > 0");
        return new Ellipse(center, a * factor, b * factor, angleRadians);
    }

    public double Area() {
        return Math.PI * a * b;
    }

    public double perimetr() {
        double h = Math.pow((a - b), 2) / Math.pow((a + b), 2);
        return Math.PI * (a + b) * (1 + (3 * h) / (10 + Math.sqrt(4 - 3 * h)));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ellipse)) return false;
        Ellipse other = (Ellipse) obj;
        return center.equals(other.center)
                && Math.abs(a - other.a) < 1e-6
                && Math.abs(b - other.b) < 1e-6
                && Math.abs(angleRadians - other.angleRadians) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, a, b, angleRadians);
    }
}