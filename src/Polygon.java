import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Polygon {
    private final List<Point> vertices;

    public Polygon(List<Point> vertices) {
        this.vertices = new ArrayList<>(vertices);
    }

    public double area() {
        double sum = 0;
        int n = vertices.size();
        for (int i = 0; i < n; i++) {
            Point current = vertices.get(i);
            Point next = vertices.get((i + 1) % n);
            sum += current.getX() * next.getY() - current.getY() * next.getX();
        }
        return Math.abs(sum) / 2;
    }

    public Polygon move(Vector vector) {
        List<Point> newVertices = new ArrayList<>();
        for (Point vertex : vertices) {
            newVertices.add(vertex.move(vector));
        }
        return new Polygon(newVertices);
    }

    public static double calculatePerimeter(double[] vertices) {
        if (vertices.length < 4 || vertices.length % 2 != 0) {
            throw new IllegalArgumentException("Некорректное количество координат вершин");
        }

        double perimeter = 0.0;
        int n = vertices.length / 2;

        for (int i = 0; i < n-1; i++) {
            double x1 = vertices[2 * i];
            double y1 = vertices[2 * i + 1];

            double x2 = vertices[2 * (i + 1)];
            double y2 = vertices[2 * (i + 1) + 1];

            Vector side = new Vector(x2 - x1, y2 - y1);
            perimeter += side.length();

        }
        double x0 = vertices[0];
        double y0 = vertices[1];
        double xn = vertices[(n-1)*2];
        double yn = vertices[(n-1)*2+1];
        Vector side = new Vector(x0 - xn, y0 - yn);
        perimeter += side.length();

        return perimeter;
    }

    public Polygon scale(double factor) {
        Vector center = calculateCentroid();
        List<Point> newVertices = new ArrayList<>();
        for (Point vertex : vertices) {
            newVertices.add(new Point(
                    center.getX() + (vertex.getX() - center.getX()) * factor,
                    center.getY() + (vertex.getY() - center.getY()) * factor
            ));
        }
        return new Polygon(newVertices);
    }

    private Vector calculateCentroid() {
        double xSum = 0, ySum = 0;
        for (Point vertex : vertices) {
            xSum += vertex.getX();
            ySum += vertex.getY();
        }
        return new Vector(xSum / vertices.size(), ySum / vertices.size());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polygon polygon = (Polygon) o;
        return vertices.equals(polygon.vertices);
    }

    @Override
    public int hashCode() {
        return vertices.hashCode();
    }
}
