import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static final int height = 20;
    static final int width = 80;
    private static final String path = "/Users/ignatzykov/IdeaProjects/lab/src/forlaba.txt"; // Путь к анализируемому файлу

    public static void main(String[] args) {
        try {
            horizont(sorter(counter(path)));
            vertical(sorter(counter(path)));

        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }


    private static Map<Character, Integer> counter(String filePath) throws IOException {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int current;
            while ((current = reader.read()) != -1) {
                char c = Character.toLowerCase((char) current);
                if (Character.isLetter(c)) {
                    frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
                }
            }
        }

        return frequencyMap;
    }


    private static List<Map.Entry<Character, Integer>> sorter(Map<Character, Integer> frequencyMap) {
        return frequencyMap.entrySet().stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()).collect(Collectors.toList());
    }


    static void horizont(List<Map.Entry<Character, Integer>> sorted) {
        int max = sorted.get(0).getValue();

        for (var entry : sorted) {
            char ch = entry.getKey();
            int count = entry.getValue();
            int barLength = Math.max(0, (int) ((count / (double) max) * (width - 15)));
            System.out.printf("%c: %7d |%s%n", ch, count, "█".repeat(barLength));
        }
    }

    static void vertical(List<Map.Entry<Character, Integer>> sorted) {
        int max = sorted.get(0).getValue();
        int min = sorted.get(sorted.size()-1).getValue();
        int count = sorted.size();

        char[] chars = new char[count];
        int[] values = new int[count];
        for (int i = 0; i < count; i++) {
            chars[i] = sorted.get(i).getKey();
            values[i] = sorted.get(i).getValue();
        }

        double step = ((double) ((max - min) + 1) / height);


        for (double i = max; i >= (min-1); i-=step) {

            if (Math.ceil(i)<Math.ceil(i+step)) { System.out.printf("%4d |", Math.round(Math.ceil(i)));}
            else {System.out.print("     |");}

            for (int j = 0; j < count; j++) {
                System.out.print(values[j] >= i ? "█ " : "  ");
            }
            System.out.println();
        }
        System.out.print("      ");
        for (int j = 0; j < count; j++) {
            System.out.print(chars[j]+" ");
        }
        System.out.println();
    }
}