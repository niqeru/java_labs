import java.util.List;
import java.util.ArrayList;

public class Generator {
    public static Drawable triangle() {
        return new Triangle(new Vector(0, 0), new Vector(1, 0), new Vector(0, 1));
    }

    public static Drawable rectangle() {
        return new Rectangle(new Vector(1, 1), 2, 3, 0);
    }

    public static Drawable circle() {
        return new Circle(new Vector(2, 2), 1.5);
    }

    public static Drawable triangle2() {
        return new Triangle(new Vector(1, 1), new Vector(2, 1), new Vector(1, 2));
    }

    public static Drawable rectangle2() {
        return new Rectangle(new Vector(3, 3), 4, 2, 0.5);
    }

    public static Drawable circle2() {
        return new Circle(new Vector(5, 5), 2.5);
    }

    public static List<Drawable> drawableList1() {
        List<Drawable> list = new ArrayList<>();
        list.add(triangle());
        list.add(rectangle());
        list.add(circle());
        return list;
    }

    public static List<Drawable> drawableList2() {
        List<Drawable> list = new ArrayList<>();
        list.add(triangle2());
        list.add(rectangle2());
        list.add(circle2());
        return list;
    }

    public static Group flatGroup() {
        Group group = new Group();
        for (Drawable d : drawableList1()) {
            if (d instanceof Shape) {
                group.add((Shape) d);
            }
        }
        return group;
    }

    public static Group nestedGroup() {
        Group outer = new Group();
        Group inner = new Group();
        for (Drawable d : drawableList1()) {
            if (d instanceof Shape) {
                outer.add((Shape) d);
            }
        }
        for (Drawable d : drawableList2()) {
            if (d instanceof Shape) {
                inner.add((Shape) d);
            }
        }
        outer.add(inner);
        return outer;
    }
}