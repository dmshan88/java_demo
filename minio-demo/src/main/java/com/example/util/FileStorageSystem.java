package com.example.util;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

public class FileStorageSystem implements FileStorageUtil {
	
	private String uploadPath;
	
	public FileStorageSystem(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	@Override
	public void upload(String fromFile, String toFile) throws FileStorageException {
		try {
			FileUtils.copyFile(new File(fromFile), new File(uploadPath, toFile));
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public void upload(InputStream stream, String toFile) throws FileStorageException {
		try {
			FileUtils.copyInputStreamToFile(stream, new File(uploadPath, toFile));
			stream.close();
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public InputStream download(String fromFile) throws FileStorageException {
		try {		
			File file = new File(uploadPath, fromFile);
			InputStream stream = FileUtils.openInputStream(file);
			return stream;
		} catch (Exception e) {
			throw new FileStorageException(e);
		}		
	}

	@Override
	public void download(String fromFile, String toFile) throws FileStorageException {
		try {
			FileUtils.copyFile(new File(uploadPath, fromFile), new File(toFile));
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

}
