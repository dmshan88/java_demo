package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.Files;


public class FileStorageSystem implements FileStorageUtil {
	
	private String uploadPath;
	
	public FileStorageSystem(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	@Override
	public void upload(String fromFile, String toFile) throws FileStorageException {
		checkFile(toFile);
		try {
			Files.copy(new File(fromFile), new File(uploadPath, toFile));
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public void upload(InputStream stream, String toFile) throws FileStorageException {
		checkFile(toFile);
		OutputStream os = null;
			try {
				os = new FileOutputStream(new File(uploadPath, toFile));
				int len = 0;
				byte[] buffer = new byte[8192];
				while ((len = stream.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
			} catch (Exception e) {
				throw new FileStorageException(e);
			} finally {
				try {
					os.close();
					stream.close();
				} catch (IOException e) {
					throw new FileStorageException(e);
				}
			}
	
	}

	@SuppressWarnings("resource")
	@Override
	public void download(String fromFile, InputStream stream) throws FileStorageException {
		checkFile(fromFile);
		try {
			File file = new File(uploadPath, fromFile);
			stream = new FileInputStream(file);
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
		
	}

	@Override
	public void download(String fromFile, String toFile) throws FileStorageException {
		checkFile(fromFile);
		try {
			Files.copy(new File(uploadPath, fromFile), new File(toFile));
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public boolean exist(String filename) throws FileStorageException {
		File file = new File(uploadPath, filename);
		return file.exists();
	}

	@Override
	public void remove(String filename) throws FileStorageException {
		File file = new File(uploadPath, filename);
		file.delete();
	}
	
	private void checkFile(String filename) {
		File file = new File(uploadPath, filename);
		if (!file.exists()) {
			File path = file.getParentFile();
			if (!path.exists()) {				
				path.mkdirs();
			}
		}
	}

}
