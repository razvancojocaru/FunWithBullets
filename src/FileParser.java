import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Opens a file for reading and provides a stream of words resulting from its parsing.
 */
public final class FileParser {

	String filePath;
	BufferedReader reader;

	/**
	 * Constructor that initializes the file path and buffered reader
	 * @param filePath	string that contains the input file name
	 */
	public FileParser(String filePath) {
		this.filePath = filePath;
		this.reader = null;
	}

	/**
	 * Opens the file for reading.
	 * <p>
	 * This operation must be performed before calling any other methods on this object.
	 */
	public void open() {
		if (reader != null) {
			throw new IllegalStateException("File already opened for reading");
		}
		try {
			reader = new BufferedReader(new FileReader(filePath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Closes the file opened for reading.
	 * <p>
	 * Should be called after finishing reading.
	 */
	public void close() {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Reads a line from the input file and splits it by delimitors ":" and " "
	 * @return an array containing the words in the line
	 */
	public String[] parseNextLine() {
		String line;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (line == null) {
			return null;
		}
		return line.toLowerCase().split("[ :]");
	}
}
