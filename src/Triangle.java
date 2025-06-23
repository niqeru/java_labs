import java.util.Objects;

public final class Triangle {
    private final Vector a;
    private final Vector b;
    private final Vector c;

    public Triangle(Vector a, Vector b, Vector c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        Vector ab = b.minus(a);
        Vector ac = c.minus(a);
        return ab.triangleArea(ac);
    }

    public Triangle move(Vector vector) {
        return new Triangle(
                a.plus(vector),
                b.plus(vector),
                c.plus(vector)
        );
    }

    public double perimeter() {
        double ab = b.minus(a).length();
        double bc = c.minus(b).length();
        double ca = a.minus(c).length();
        return ab + bc + ca;
    }

    private Vector scaleVector(Vector p, Vector center, double factor) {
        return center.plus(p.minus(center).multiply(factor));
    }

    public Triangle scale(double factor) {
        Vector centroid = new Vector(
                (a.getX() + b.getX() + c.getX()) / 3,
                (a.getY() + b.getY() + c.getY()) / 3
        );
        return new Triangle(
                scaleVector(a, centroid, factor),
                scaleVector(b, centroid, factor),
                scaleVector(c, centroid, factor)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return a.equals(triangle.a) &&
                b.equals(triangle.b) &&
                c.equals(triangle.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
