import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите коэффициенты a, b, c: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        System.out.print("Введите границы интервала от и до: ");
        double xfrom = scanner.nextDouble();
        double xto = scanner.nextDouble();

        System.out.print("Введите размер таблицы: ");
        int tableSize = scanner.nextInt();

        System.out.print("Введите число разбиений отрезка: ");
        int stepsCount = scanner.nextInt();

        scanner.close();

        String[][] table = buildTable(a, b, c, xfrom, xto, tableSize, stepsCount);
        printTable(table);
    }

    public static double f(double a, double b, double c, double x) {
        return a * x * x + b * x + c;
    }

    public static double integral(double a, double b, double c, double x0, double x1, int steps) {
        if (x0 > x1) return -integral(a, b, c, x1, x0, steps);

        double n = (x1 - x0) / steps;
        double sum = (f(a, b, c, x0) + f(a, b, c, x1));
        for (int i = 1; i < steps; i++) {
            double xi = x0 + i * n;
            sum += 2 * f(a, b, c, xi);
        }
        return sum * n / 2;
    }


    public static String[][] buildTable(double a, double b, double c, double xfrom, double xto, int tableSize, int stepsCount) {
        String[][] table = new String[tableSize + 1][tableSize + 1];
        double[] xValues = new double[tableSize];
        double step = (xto - xfrom) / (tableSize - 1);

        for (int i = 0; i < tableSize; i++) {
            xValues[i] = xfrom + i * step;
            table[0][i + 1] = String.format("%.4f", xValues[i]);
            table[i + 1][0] = String.format("%.4f", xValues[i]);
        }
        table[0][0] = " ";

        for (int i = 1; i < tableSize + 1; i++) {
            for (int j = 1; j < tableSize + 1; j++) {
                double x0 = xValues[i - 1];
                double x1 = xValues[j - 1];
                table[i][j] = String.format("%.4f", integral(a, b, c, x0, x1, stepsCount));
            }
        }
        return table;
    }

    public static void printTable(String[][] table) {
        int cnt = 0;
        for (String[] row : table) {
            String row1 = row[0];
            System.out.printf("%10s |", row1);
            for (int i = 1; i < row.length; i++) {
                String cell = row[i];
                System.out.printf("%10s", cell);
            }
            System.out.println();
            cnt += 1;
            if (cnt < row.length) {
                System.out.print("-----------|");
                for (int i = 1; i < row.length; i++) {
                    System.out.print("----------");
                }
                System.out.println();
            }
        }
    }
}