import java.util.Objects;

public final class Line {
    private final Vector start;
    private final Vector end;

    public Line(Vector start, Vector end) {
        this.start = start;
        this.end = end;
    }

    public double length() {
        return end.minus(start).length();
    }

    public Line move(Vector vector) {
        return new Line(start.plus(vector), end.plus(vector));
    }

    public Line scale(double factor) {
        Vector center = new Vector(
            (start.getX() + end.getX()) / 2,
            (start.getY() + end.getY()) / 2
        );
        Vector newStart = center.plus(start.minus(center).multiply(factor));
        Vector newEnd = center.plus(end.minus(center).multiply(factor));
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