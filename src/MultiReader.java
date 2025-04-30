import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class MultiReader extends Reader{
    private final Iterator<Reader> readers;
    private Reader currentReader;

    public MultiReader(List<Reader> readers) {
        if (readers == null) throw new IllegalArgumentException("readers cannot be null");
        this.readers = readers.iterator();
        if (this.readers.hasNext()) {
            this.currentReader = this.readers.next();
        }
        else this.currentReader = null;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (currentReader == null) {
            return -1;
        }

        int bytesRead = currentReader.read(cbuf, off, len);
        if (bytesRead == -1) {
            currentReader.close();
            if (readers.hasNext()) {
                currentReader = readers.next();
            }
            else currentReader =  null;
            return read(cbuf, off, len);
        }

        return bytesRead;
    }

    @Override
    public void close() throws IOException {
        while (currentReader != null) {
            currentReader.close();
            if(readers.hasNext()) currentReader = readers.next();
            else currentReader = null;
        }
    }
}

