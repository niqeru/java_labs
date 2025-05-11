import java.util.Objects;

public final class Line {
    private final Point start;
    private final Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public double length() {
        return new Vector(
                end.getX() - start.getX(),
                end.getY() - start.getY()
        ).length();
    }

    public Line move(Vector vector) {
        return new Line(start.move(vector), end.move(vector));
    }


    public Line scale(double factor) {
        Vector center = new Vector(
                (start.getX() + end.getX()) / 2,
                (start.getY() + end.getY()) / 2
        );

        Point newStart = new Point(
                center.getX() + (start.getX() - center.getX()) * factor,
                center.getY() + (start.getY() - center.getY()) * factor
        );

        Point newEnd = new Point(
                center.getX() + (end.getX() - center.getX()) * factor,
                center.getY() + (end.getY() - center.getY()) * factor
        );

        return new Line(newStart, newEnd);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line that = (Line) o;
        return start.equals(that.start) && end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}