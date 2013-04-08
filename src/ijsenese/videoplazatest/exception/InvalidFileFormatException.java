package ijsenese.videoplazatest.exception;

/**
 * Exception class used when the input file is not formatted according to specification.
 * 
 * @author ivo.senese
 */
public class InvalidFileFormatException extends Exception {
	public InvalidFileFormatException() {
		super("The file being access is not formatted correctly");
	}
}