import java.util.List;

public final class CmdArgs {
    final List<String> inputFiles;
    final public boolean count, desc, horizontal, ignoreCase, all;
    final public String out;

    CmdArgs(Builder builder) {
        this.inputFiles = builder.inputFiles;
        this.count = builder.count;
        this.desc = builder.desc;
        this.horizontal = builder.horizontal;
        this.ignoreCase = builder.ignoreCase;
        this.all = builder.all;
        this.out = builder.out;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public boolean isCount() {
        return count;
    }

    public boolean isDesc() {
        return desc;
    }

    public String getOut() {
        return out;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public boolean isAll() {
        return all;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    static class Builder {
        private List<String> inputFiles;
        public boolean count = false, desc = false, horizontal = false, ignoreCase = false, all = false;
        public String out = null;

        public Builder inputFiles(List<String> inputFiles) {
            this.inputFiles = inputFiles;
            return this;
        }

        public void setCount(boolean count) {
            this.count = count;
        }

        public void setDesc(boolean desc) {
            this.desc = desc;
        }

        public void setHorizontal(boolean horizontal) {
            this.horizontal = horizontal;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public void setAll(boolean all) {
            this.all = all;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public CmdArgs build() {
            if (inputFiles == null || inputFiles.isEmpty()) {
                throw new IllegalArgumentException("Es gibt keine input files");
            }
            return new CmdArgs(this);
        }
    }
}

