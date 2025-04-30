import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class TextAnalyzer {
    private final AnalyzerConfig config;

    public TextAnalyzer(AnalyzerConfig config) {
        this.config = config;
    }

    public AnalysisResult analyze(Reader reader) throws IOException {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        int current;
        while ((current = reader.read()) != -1) {
            char c = (char) current;

            if (!config.isAll() && !Character.isLetter(c)) {
                continue;
            }
            if (config.isIgnoreCase()) {
                c = Character.toLowerCase(c);
            }
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        return new AnalysisResult(frequencyMap);
    }

    public static class AnalysisResult {
        private final Map<Character, Integer> frequencyMap;

        public AnalysisResult(Map<Character, Integer> frequencyMap) {
            this.frequencyMap = frequencyMap;
        }

        public Map<Character, Integer> getfrequencyMap() {
            return frequencyMap;
        }
    }
}


