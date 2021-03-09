package com.example.util;

import java.io.InputStream;

public interface FileStorageUtil {
	
	void upload(String fromFile, String toFile) throws FileStorageException;
	
	void upload(InputStream stream, String toFile) throws FileStorageException;
	
	void download(String fromFile, InputStream stream) throws FileStorageException; 
	
	void download(String fromFile, String toFile) throws FileStorageException;
	
	boolean exist(String file) throws FileStorageException;
	
	void remove(String file) throws FileStorageException;
	
	public static class FileStorageException extends Exception {
		
		private static final long serialVersionUID = -4622795088704441234L;

		public FileStorageException() {
			super();
		}
		public FileStorageException(String message) {
			super(message);
		}
		
		public FileStorageException(String message, Throwable cause) {
			super(message, cause);
		}
		
		public FileStorageException(Throwable cause) {
			super(cause);
		}
	}

}
