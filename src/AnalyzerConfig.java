public class AnalyzerConfig {
    private final boolean all;
    private final boolean ignoreCase;

    private AnalyzerConfig(Builder builder) {
        this.all = builder.all;
        this.ignoreCase = builder.ignoreCase;
    }

    public boolean isAll() {
        return all;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public static class Builder {
        private boolean all = false;
        private boolean ignoreCase = false;

        public void setAll(boolean all) {
            this.all = all;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public AnalyzerConfig build() {
            return new AnalyzerConfig(this);
        }
    }
}

