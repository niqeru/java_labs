//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int height = 100;
        int width = 100;

        int All = height * width;
        double step = Math.PI / (All - 1);

        double[][] table = new double[height][width];
        double[][] table_x = new double[height][width];
        double x = -step;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                x += step;
                table_x[j][i] = x;
                table[j][i] = Math.abs(Math.sin(x));
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double orig = Math.asin(table[i][j]);
                System.out.printf("sin(%.4f)=%.4f   ", table_x[i][j], table[i][j]);
            }
            System.out.println();
        }
    }
}