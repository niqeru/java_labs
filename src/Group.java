import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group implements Shape {
    private final List<Shape> shapes = new ArrayList<>();

    public void add(Shape shape) {
        shapes.add(shape);
    }

    public void remove(Shape shape) {
        shapes.remove(shape);
    }

    public Shape get(int index) {
        return shapes.get(index);
    }

    public void remove(int index) {
        shapes.remove(index);
    }

    public List<Shape> getShapes() {
        return Collections.unmodifiableList(shapes);
    }

    @Override
    public void draw() {
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    @Override
    public Group move(Vector vector) {
        Group moved = new Group();
        for (Shape shape : shapes) {
            moved.add((Shape) shape.move(vector));
        }
        return moved;
    }

    @Override
    public double area() {
        double sum = 0;
        for (Shape shape : shapes) {
            sum += shape.area();
        }
        return sum;
    }

    @Override
    public double perimeter() {
        double sum = 0;
        for (Shape shape : shapes) {
            sum += shape.perimeter();
        }
        return sum;
    }

    @Override
    public Group scale(double factor) {
        Group scaled = new Group();
        for (Shape shape : shapes) {
            scaled.add(shape.scale(factor));
        }
        return scaled;
    }
}