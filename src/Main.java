public class Main {
    public static void main(String[] args) {
        double[] cords = {
                0.0, 0.0,
                2.0, 0.0,
                3.0, 2.0,
                1.0, 3.0,
                -1.0, 2.0
        };

        double perimeter = calculatePerimeter(cords);
        System.out.println("Периметр многоугольника: " + perimeter);

        double area = calculateArea(cords);
        System.out.println("Площадь многоугольника: " + area);
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


    public static double calculateArea(double[] vertices) {
        if (vertices.length < 6 || vertices.length % 2 != 0) {
            throw new IllegalArgumentException("Некорректное количество координат вершин");
        }

        double area = 0.0;
        int n = vertices.length / 2;

        double x0 = vertices[0];
        double y0 = vertices[1];

        for (int i = 1; i < n - 1; i++) {
            double x1 = vertices[2 * i];
            double y1 = vertices[2 * i + 1];

            double x2 = vertices[2 * (i + 1)];
            double y2 = vertices[2 * (i + 1) + 1];

            Vector v1 = new Vector(x1 - x0, y1 - y0);
            Vector v2 = new Vector(x2 - x0, y2 - y0);

            area += v1.triangleArea(v2);
        }

        return area;
    }
}