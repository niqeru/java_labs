import java.util.*;

public class Main {

    static int getElementsN = 30_000;
    static int insertStartN = 200_000;
    static int insertMiddleN = 50_000;
    static int insertEndN = 3_000_000;
    static int DeleteStartN = 200_000;
    static int DeleteMiddleN = 100_000;
    static int DeleteEndN = 20_000_000;

    public static void main(String[] args) {
        List<TestResult> results = new ArrayList<>();

        results.add(testGetElements(getElementsN));
        results.add(testInsertStart(insertStartN));
        results.add(testInsertMiddle(insertMiddleN));
        results.add(testInsertEnd(insertEndN));
        results.add(testDeleteStart(DeleteStartN));
        results.add(testDeleteMiddle(DeleteMiddleN));
        results.add(testDeleteEnd(DeleteEndN));

        String[][] table = prepareResultsTable(results);
        printTable(table);
    }

    record TestResult(
            String operationName,
            long arrayTime,
            long arrayListTime,
            long arrayListWithSizeTime,
            long linkedListTime,
            long hashSetTime,
            long treeSetTime
    ) {
    }




    static TestResult testInsertStart(int n) {
        long linkedListTime = measureTime(n, () -> {
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                list.add(0, i);
            }
        });

        long arrayListTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(0, i);
            }
        });

        long arrayListWithSizeTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(0, i);
            }
        });

        return new TestResult(
                "InsertStart",
                -1,
                arrayListTime,
                arrayListWithSizeTime,
                linkedListTime,
                -1,
                -1
        );
    }




    static TestResult testInsertMiddle(int n) {
        long linkedListTime = measureTime(n, () -> {
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                list.add(list.size() / 2, i);
            }
        });

        long arrayListTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(list.size() / 2, i);
            }
        });

        long arrayListWithSizeTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(list.size() / 2, i);
            }
        });

        return new TestResult(
                "InsertMiddle",
                -1,
                arrayListTime,
                arrayListWithSizeTime,
                linkedListTime,
                -1,
                -1
        );
    }




    static TestResult testInsertEnd(int n) {
        long arrayTime = measureTime(n, () -> {
            Integer[] array = new Integer[n];
            for (int i = 0; i < n; i++) {
                array[i] = i;
            }
        });

        long linkedListTime = measureTime(n, () -> {
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                list.add(i);
            }
        });

        long arrayListTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(i);
            }
        });

        long arrayListWithSizeTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(i);
            }
        });

        long hashSetTime = measureTime(n, () -> {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                set.add(i);
            }
        });

        long treeSetTime = measureTime(n, () -> {
            Set<Integer> set = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                set.add(i);
            }
        });

        return new TestResult(
                "InsertEnd",
                arrayTime,
                arrayListTime,
                arrayListWithSizeTime,
                linkedListTime,
                hashSetTime,
                treeSetTime
        );
    }





    static TestResult testDeleteStart(int n) {
        long linkedListTime = measureTime(n, () -> {
            List<Integer> list = new LinkedList<>();
            fillList(list, n);
            while (!list.isEmpty()) {
                list.remove(0);
            }
        });

        long arrayListTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>();
            fillList(list, n);
            while (!list.isEmpty()) {
                list.remove(0);
            }
        });

        long arrayListWithSizeTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>(n);
            fillList(list, n);
            while (!list.isEmpty()) {
                list.remove(0);
            }
        });

        return new TestResult(
                "DeleteStart",
                -1,
                arrayListTime,
                arrayListWithSizeTime,
                linkedListTime,
                -1,
                -1
        );
    }






    static TestResult testDeleteMiddle(int n) {
        long linkedListTime = measureTime(n, () -> {
            List<Integer> list = new LinkedList<>();
            fillList(list, n);
            while (!list.isEmpty()) {
                list.remove(list.size() / 2);
            }
        });

        long arrayListTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>();
            fillList(list, n);
            while (!list.isEmpty()) {
                list.remove(list.size() / 2);
            }
        });

        long arrayListWithSizeTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>(n);
            fillList(list, n);
            while (!list.isEmpty()) {
                list.remove(list.size() / 2);
            }
        });

        return new TestResult(
                "DeleteMiddle",
                -1,
                arrayListTime,
                arrayListWithSizeTime,
                linkedListTime,
                -1,
                -1
        );
    }






    static TestResult testDeleteEnd(int n) {
        long linkedListTime = measureTime(n, () -> {
            List<Integer> list = new LinkedList<>();
            fillList(list, n);
            while (!list.isEmpty()) {
                list.remove(list.size() - 1);
            }
        });

        long arrayListTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>();
            fillList(list, n);
            while (!list.isEmpty()) {
                list.remove(list.size() - 1);
            }
        });

        long arrayListWithSizeTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>(n);
            fillList(list, n);
            while (!list.isEmpty()) {
                list.remove(list.size() - 1);
            }
        });

        long hashSetTime = measureTime(n, () -> {
            Set<Integer> set = new HashSet<>();
            fillSet(set, n);
            for (int i = n - 1; i >= 0; i--) {
                set.remove(i);
            }
        });

        long treeSetTime = measureTime(n, () -> {
            Set<Integer> set = new TreeSet<>();
            fillSet(set, n);
            for (int i = n - 1; i >= 0; i--) {
                set.remove(i);
            }
        });

        return new TestResult(
                "DeleteEnd",
                -1,
                arrayListTime,
                arrayListWithSizeTime,
                linkedListTime,
                hashSetTime,
                treeSetTime
        );
    }







    static TestResult testGetElements(int n) {
        long arrayTime = measureTime(n, () -> {
            Integer[] array = new Integer[n];
            for (int i = 0; i < n; i++) array[i] = i;
            for (int i = 0; i < n; i++) {
                Integer val = array[i];
            }
        });

        long linkedListTime = measureTime(n, () -> {
            List<Integer> list = new LinkedList<>();
            fillList(list, n);
            for (int i = 0; i < n; i++) {
                Integer val = list.get(i);
            }
        });

        long arrayListTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>();
            fillList(list, n);
            for (int i = 0; i < n; i++) {
                Integer val = list.get(i);
            }
        });

        long arrayListWithSizeTime = measureTime(n, () -> {
            List<Integer> list = new ArrayList<>(n);
            fillList(list, n);
            for (int i = 0; i < n; i++) {
                Integer val = list.get(i);
            }
        });

        long hashSetTime = measureTime(n, () -> {
            Set<Integer> set = new HashSet<>();
            fillSet(set, n);
            for (int i = 0; i < n; i++) {
                boolean contains = set.contains(i);
            }
        });

        long treeSetTime = measureTime(n, () -> {
            Set<Integer> set = new TreeSet<>();
            fillSet(set, n);
            for (int i = 0; i < n; i++) {
                boolean contains = set.contains(i);
            }
        });

        return new TestResult(
                "Get",
                arrayTime,
                arrayListTime,
                arrayListWithSizeTime,
                linkedListTime,
                hashSetTime,
                treeSetTime
        );
    }





    // вспомогательные функции
    static void fillList(List<Integer> list, int n) {
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
    }

    static void fillSet(Set<Integer> set, int n) {
        for (int i = 0; i < n; i++) {
            set.add(i);
        }
    }

    static long measureTime(int n, Runnable operation) {
        long start = System.currentTimeMillis();
        operation.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    static String[][] prepareResultsTable(List<TestResult> results) {
        String[] headers = {
                "",
                "Array",
                "ArrayList",
                "presizedArrayList",
                "LinkedList",
                "HashSet",
                "TreeSet"
        };

        String[][] table = new String[results.size() + 1][headers.length];
        table[0] = headers;

        for (int i = 0; i < results.size(); i++) {
            TestResult result = results.get(i);
            String operationName = result.operationName();
            int n = getNForOperation(operationName);

            table[i + 1][0] = operationName + " (" + formatNumber(n) + ")";
            table[i + 1][1] = result.arrayTime() == -1 ? "-" : formatNumber(result.arrayTime());
            table[i + 1][2] = result.arrayListTime() == -1 ? "-" : formatNumber(result.arrayListTime());
            table[i + 1][3] = result.arrayListWithSizeTime() == -1 ? "-" : formatNumber(result.arrayListWithSizeTime());
            table[i + 1][4] = result.linkedListTime() == -1 ? "-" : formatNumber(result.linkedListTime());
            table[i + 1][5] = result.hashSetTime() == -1 ? "-" : formatNumber(result.hashSetTime());
            table[i + 1][6] = result.treeSetTime() == -1 ? "-" : formatNumber(result.treeSetTime());
        }

        return table;
    }

    static int getNForOperation(String operationName) {
        return switch (operationName) {
            case "InsertStart" -> insertStartN;
            case "InsertMiddle" -> insertMiddleN;
            case "InsertEnd" -> insertEndN;
            case "DeleteStart" -> DeleteStartN;
            case "DeleteMiddle" -> DeleteMiddleN;
            case "DeleteEnd" -> DeleteEndN;
            case "Get" -> getElementsN;
            default -> 0;
        };
    }

    static String formatNumber(long number) {
        return String.format("%,d", number).replace(",", "_");
    }


    static void printTable(String[][] table) {
        int[] columnWidths = new int[table[0].length];
        for (String[] row : table) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] != null && row[i].length() > columnWidths[i]) {
                    columnWidths[i] = row[i].length();
                }
            }
        }
        printRow(table[0], columnWidths, true);
        printHorizontalBorder(columnWidths);

        for (int row = 1; row < table.length; row++) {
            printRow(table[row], columnWidths, true);
        }
    }

    static void printHorizontalBorder(int[] columnWidths) {
        System.out.print("|");
        for (int width : columnWidths) {
            System.out.print("-".repeat(width + 2) + "|");
        }
        System.out.println();
    }

    static void printRow(String[] row, int[] columnWidths, boolean center) {
        System.out.print("|");
        for (int i = 0; i < row.length; i++) {
            String cell = row[i] != null ? row[i] : "";
            System.out.print(" ");
            if (center) {
                System.out.print(centerString(cell, columnWidths[i]));
            } else {
                System.out.print(padRight(cell, columnWidths[i]));
            }
            System.out.print(" |");
        }
        System.out.println();
    }

    static String centerString(String s, int width) {
        if (s == null || s.length() >= width) {
            return s;
        }

        int leftPad = (width - s.length()) / 2;
        int rightPad = width - s.length() - leftPad;

        return " ".repeat(leftPad) + s + " ".repeat(rightPad);
    }

    static String padRight(String s, int width) {
        if (s == null) return " ".repeat(width);
        return s + " ".repeat(width - s.length());
    }
}
