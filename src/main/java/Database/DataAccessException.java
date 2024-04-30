package Database;

public class DataAccessException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constructor that accepts a message and the original exception
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}