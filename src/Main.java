import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try{
            CmdArgs pars = parseParameters(args);
            process(pars);
        }catch (IllegalArgumentException e){
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            System.exit(1);
        }
    }

    private static CmdArgs parseParameters(String[] args){
        CmdArgs.Builder builder = new CmdArgs.Builder();

        Iterator<String> iterator = Arrays.stream(args).iterator();
        List<String> inputFiles = new ArrayList<>();

        while (iterator.hasNext()) {
            String cur = iterator.next();

            switch (cur) {
                case "-count" -> builder.setCount(true);
                case "-desc" -> builder.setDesc(true);
                case "-horizontal" -> builder.setHorizontal(true);
                case "-ignoreCase" -> builder.setIgnoreCase(true);
                case "-all" -> builder.setAll(true);
                case "-out" -> {
                    if (!iterator.hasNext()) {
                        throw new IllegalArgumentException("No file after " + cur);
                    }else{
                        String valueString = iterator.next();
                        builder.setOut(valueString);
                    }
                }
                default -> {
                    if(cur.startsWith("-")){
                        throw new IllegalArgumentException("Unknown flag: " + cur);
                    }
                    inputFiles.add(cur);
                }
            }
        }
        return builder.inputFiles(inputFiles).build();
    }

    private static void process(CmdArgs pars) throws IOException{
        for (String filePath : pars.getInputFiles()) {
            if (!Files.exists(Paths.get(filePath))) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }
        }

        List<Reader> readers = new ArrayList<>();
        for (String filePath : pars.getInputFiles()) {
            Reader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
            readers.add(fileReader);
        }

        AnalyzerConfig.Builder analyzerConfigB = new AnalyzerConfig.Builder();
        analyzerConfigB.setAll(pars.isAll());
        analyzerConfigB.setIgnoreCase(pars.isIgnoreCase());
        AnalyzerConfig analyzerConfig = analyzerConfigB.build();

        TextAnalyzer analyzer = new TextAnalyzer(analyzerConfig);
        TextAnalyzer.AnalysisResult result;
        try (MultiReader multiReader = new MultiReader(readers)) {
            result = analyzer.analyze(multiReader);
        }

        Writer writer;

        if (pars.getOut() != null) {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pars.getOut()), StandardCharsets.UTF_8));
        } else {
            writer = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8)
            );
        }

        if (pars.isHorizontal()) {
            writer = new HorizontalGraphWriter(writer);
        } else {
            writer = new VerticalGraphWriter(writer);
        }

        try {
            GraphWriter decorator = (GraphWriter) writer;
            decorator.writeAnalysisResult(result, pars.isCount(), pars.isDesc()
            );
        } finally {
            writer.close();
        }
    }
}

