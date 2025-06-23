public final class Point implements Drawable {
    private final Vector position;

    public Point(Vector position) {
        this.position = position;
    }

    public Point(double x, double y) {
        this(new Vector(x, y));
    }

    public double getX() { return position.getX(); }
    public double getY() { return position.getY(); }

    @Override
    public void draw() {
        // Пока пусто
    }

    @Override
    public Point move(Vector vector) {
        return new Point(position.plus(vector));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return position.equals(point.position);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }
}