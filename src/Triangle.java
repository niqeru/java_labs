import java.util.Objects;

public final class Triangle {
    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        Vector ab = new Vector(b.getX() - a.getX(), b.getY() - a.getY());
        Vector ac = new Vector(c.getX() - a.getX(), c.getY() - a.getY());
        return ab.triangleArea(ac);
    }

    public Triangle move(Vector vector) {
        return new Triangle(
                a.move(vector),
                b.move(vector),
                c.move(vector)
        );
    }

    public double perimeter() {
        double a1 = new Vector((b.getX() - a.getX()), (b.getY() - a.getY())).length();
        double b1 = new Vector(c.getX() - b.getX(), c.getY() - b.getY()).length();
        double c1 = new Vector(a.getX() - c.getX(), a.getY() - c.getY()).length();
        return a1 + b1 + c1;
    }


    private Point scalePoint(Point p, Vector center, double factor) {
        return new Point(
                center.getX() + (p.getX() - center.getX()) * factor,
                center.getY() + (p.getY() - center.getY()) * factor
        );
    }

    public Triangle scale(double factor) {
        Vector centroid = new Vector(
                (a.getX() + b.getX() + c.getX()) / 3,
                (a.getY() + b.getY() + c.getY()) / 3
        );

        return new Triangle(
                scalePoint(a, centroid, factor),
                scalePoint(b, centroid, factor),
                scalePoint(c, centroid, factor)
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
