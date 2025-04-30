import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class GraphWriter extends Writer {
    protected final Writer wrappedWriter;
    protected static final int DEFAULT_WIDTH = 80;
    protected static final int DEFAULT_HEIGHT = 20;

    protected GraphWriter(Writer wrappedWriter) {
        this.wrappedWriter = wrappedWriter;
    }

    public abstract void writeAnalysisResult(TextAnalyzer.AnalysisResult result, boolean count, boolean desc) throws IOException;

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        wrappedWriter.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        wrappedWriter.flush();
    }

    @Override
    public void close() throws IOException {
        wrappedWriter.close();
    }

    protected List<Map.Entry<Character, Integer>> sortEntries(Map<Character, Integer> frequencyMap, boolean count, boolean desc) {
        return frequencyMap.entrySet().stream().sorted((e1, e2) -> {
                    int comparison;
                    if (count) {
                        comparison = Integer.compare(e2.getValue(), e1.getValue());
                    } else {
                        comparison = Character.compare(e2.getKey(), e1.getKey());
                    }
                    if (!desc) {
                        comparison = -comparison;
                    }
                    return comparison;
                })
                .collect(Collectors.toList());
    }


    protected String formatChar(char c) {
        return switch (c) {
            case ' ' -> "SPACE";
            case ' ' -> "NBSP";
            default -> Character.toString(c);
        };
    }
}

class HorizontalGraphWriter extends GraphWriter {
    private final int width;

    public HorizontalGraphWriter(Writer wrappedWriter) {
        this(wrappedWriter, DEFAULT_WIDTH);
    }

    public HorizontalGraphWriter(Writer wrappedWriter, int width) {
        super(wrappedWriter);
        this.width = width;
    }

    @Override
    public void writeAnalysisResult(TextAnalyzer.AnalysisResult result, boolean count, boolean desc) throws IOException {
        Map<Character, Integer> frequencyMap = result.getfrequencyMap();
        if (frequencyMap.isEmpty()) {
            wrappedWriter.write("No data to display\n");
            return;
        }

        List<Map.Entry<Character, Integer>> sorted = sortEntries(frequencyMap, count, desc);
        List<Map.Entry<Character, Integer>> sortedfr = sortEntries(frequencyMap, true, true);
        int max = sortedfr.get(0).getValue();

        for (var entry : sorted) {
            String ch = formatChar(entry.getKey());
            int value = entry.getValue();
            int barLength = (int) ((value / (double) max) * (width - 15));
            String line = String.format("%-5s: %7d |%s%n",
                    ch, value, "█".repeat(barLength));
            wrappedWriter.write(line);
        }
    }
}

class VerticalGraphWriter extends GraphWriter {
    private final int height;

    public VerticalGraphWriter(Writer wrappedWriter) {
        this(wrappedWriter, DEFAULT_HEIGHT);
    }

    public VerticalGraphWriter(Writer wrappedWriter, int height) {
        super(wrappedWriter);
        this.height = height;
    }

    @Override
    public void writeAnalysisResult(TextAnalyzer.AnalysisResult result, boolean count, boolean desc) throws IOException {
        Map<Character, Integer> frequencyMap = result.getfrequencyMap();
        if (frequencyMap.isEmpty()) {
            wrappedWriter.write("No data to display\n");
            return;
        }

        List<Map.Entry<Character, Integer>> sorted = sortEntries(frequencyMap, count, desc);
        List<Map.Entry<Character, Integer>> sortedfr = sortEntries(frequencyMap, true, true);
        int max = sortedfr.get(0).getValue();
        int min = sortedfr.get(sorted.size()-1).getValue();
        int count2 = sorted.size();

        char[] chars = new char[count2];
        int[] values = new int[count2];
        for (int i = 0; i < count2; i++) {
            chars[i] = sorted.get(i).getKey();
            values[i] = sorted.get(i).getValue();
        }

        double step = ((double) ((max - min) + 1) / height);

        for (double i = max; i >= (min-1); i-=step) {
            if (Math.ceil(i)<Math.ceil(i+step)) {
                wrappedWriter.write(String.format("%4d |", Math.round(Math.ceil(i))));
            } else {
                wrappedWriter.write("     |");
            }

            for (int j = 0; j < count2; j++) {
                wrappedWriter.write(values[j] >= i ? "█ " : "  ");
            }
            wrappedWriter.write("\n");
        }

        wrappedWriter.write("      ");
        for (int j = 0; j < count2; j++) {
//            wrappedWriter.write(formatChar(chars[j])+" ");
            wrappedWriter.write(chars[j]+" ");
        }
        wrappedWriter.write("\n");
    }
}