
import java.io.FileWriter;
import java.io.IOException;

/**
 * Opens a file for writing and writes a string in the output file.
 */
public class Writer {

	String filePath;
	FileWriter writer;
	
	public Writer(String path) {
		this.filePath = path;
		this.writer = null;
	}

	/**
	 * Opens the file for writing.
	 * <p>
	 * This operation must be performed before calling any other methods on this object.
	 */
	public void open() {
		if (writer != null) {
			throw new IllegalStateException("File already opened for writing");
		}
		try {
			writer = new FileWriter(filePath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Closes the file opened for writing.
	 * <p>
	 * Should be called after finishing writing.
	 */
	public void close() {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * Write a string to the opened file
	 * @param str to be written
	 */
	public void writeLine(String str) {
		try {
			writer.write(str);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
