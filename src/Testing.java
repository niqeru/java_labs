import java.io.*;
import java.util.List;

public class Testing {
    public static void testFigure(Drawable d) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FigureOutput out = new FigureOutput(baos);
        out.writeDrawable(d);
        out.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        FigureInput in = new FigureInput(bais);
        Drawable d2 = in.readDrawable();
        boolean eq = d.equals(d2);
        System.out.println("Figure test: " + eq);
    }

    public static void testList(List<Drawable> list) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FigureOutput out = new FigureOutput(baos);
        out.writeDrawableList(list);
        out.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        FigureInput in = new FigureInput(bais);
        List<Drawable> list2 = in.readDrawableList();
        boolean eq = list.equals(list2);
        System.out.println("List test: " + eq);
    }

    public static void testGroup(Group group) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FigureOutput out = new FigureOutput(baos);
        out.writeDrawable(group);
        out.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        FigureInput in = new FigureInput(bais);
        Drawable d2 = in.readDrawable();
        boolean eq = false;
        if (d2 instanceof Group) {
            eq = group.contentEquals((Group) d2);
        }
        System.out.println("Group test: " + eq);
    }
}