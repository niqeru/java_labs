import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class GraphWriter extends Writer {
    protected final Writer wrappedWriter;

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


    protected String formatChar(char c) {
        return switch (c) {
            case '\n' -> "\\n";
            case '\r' -> "\\r";
            case '\t' -> "\\t";
            case ' ' -> "SPACE";
            case ' ' -> "NBSP";
            default -> Character.toString(c);
        };
    }
}

class HorizontalGraphWriter extends GraphWriter {
    public HorizontalGraphWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }
    @Override
    public void writeAnalysisResult(TextAnalyzer.AnalysisResult result, boolean count, boolean desc) throws IOException {
        int s = -1;
        Map<Character, Integer> counts = result.getfrequencyMap();
        for (int i : counts.values()) {
            if (i > s) {
                s = i;
            }
        }
        int l = Integer.toString(s).length();

        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(counts.entrySet());
        if(count){
            if(desc){
                entryList.sort(Map.Entry.comparingByValue());
            }else{
                entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
            }
        }else{
            if(desc){
                entryList.sort(Map.Entry.comparingByKey());
            }else{
                entryList.sort((entry1, entry2) -> entry2.getKey().compareTo(entry1.getKey()));
            }
        }

        String[][] matrix = new String[21][counts.size()+1];
        matrix[20][0]=" ".repeat(l+2);
        int c = 1;
        for (Map.Entry<Character, Integer> i : entryList) {
            int countR = (int) Math.ceil((double) i.getValue() / s * 20);
            matrix[20][c] = formatChar( i.getKey());
            int l_ch = formatChar( i.getKey()).toString().length();
            int left = (int) Math.floor( (double) l_ch /2);
            int right = l_ch -(int) Math.floor( (double) l_ch /2)-1;
            for (int j = 1; j <= 20; j++) {
                if (j<=countR) matrix[20 - j][c] = " ".repeat( left)+"▉"+" ".repeat(right);
                else matrix[20 - j][c] = " ".repeat(l_ch);
                if (j==countR){
                    matrix[20-j][0] = String.format("%"+l+"d| ", i.getValue());
                }if((matrix[20-j][0] == null)){
                    matrix[20-j][0] = String.format(" ".repeat(l)+"| ");
                }
            }
            c+=1;

        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j]==null){
                    wrappedWriter.write(" ");
                }else {
                    wrappedWriter.write(matrix[i][j]);
                }
            }
            wrappedWriter.write("\n");
        }

    }

}

class VerticalGraphWriter extends GraphWriter {

    protected VerticalGraphWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void writeAnalysisResult(TextAnalyzer.AnalysisResult result, boolean count, boolean desc) throws IOException {
        Integer s = -1;
        Map<Character, Integer> counts = result.getfrequencyMap();

        for (Map.Entry<Character, Integer> i : counts.entrySet()) {
            if (i.getValue() > s) {
                s = i.getValue();
            }
        }
        int l = s.toString().length();

        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(counts.entrySet());
        if(count){
            if(desc){
                entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
            }else{
                entryList.sort(Map.Entry.comparingByValue());
            }
        }else{
            if(desc){
                entryList.sort((entry1, entry2) -> entry2.getKey().compareTo(entry1.getKey()));
            }else{
                entryList.sort(Map.Entry.comparingByKey());
            }
        }


        int l_ch = formatChar(entryList.get(0).getKey()).toString().length();

        for (Map.Entry<Character, Integer> i : counts.entrySet()) {
            if (formatChar(i.getKey()).toString().length() > l_ch) {
                l_ch = formatChar(i.getKey()).toString().length();
            }
        }
        for (Map.Entry<Character, Integer> i : entryList) {
            int countR = (int) Math.ceil((double) i.getValue() / s * 80);
            String string = String.format(" ".repeat(l_ch - formatChar(i.getKey()).toString().length()) + formatChar(i.getKey()) + ":\t" + "%" + -l + "d" + "\t|" + "▉".repeat(countR) + "\n", i.getValue());
            wrappedWriter.write(string);
        }
    }
}
