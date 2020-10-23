package cl.ccastillo.recyclothes.exception;

public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = -3478045565910939328L;

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}

}
