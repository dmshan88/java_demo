package com.example.util;

import java.io.InputStream;

import io.minio.DownloadObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.UploadObjectArgs;

public class FileStorageMinio implements FileStorageUtil {
	
	private MinioClient minioClient;
	
	private String bucket;
	
	public FileStorageMinio(String endpoint, String accessKey, String secretKey, String bucket) {
		this.minioClient = MinioClient.builder().endpoint(endpoint)
				.credentials(accessKey, secretKey).build();
		this.bucket = bucket;
	}

	@Override
	public void upload(String fromFile, String toFile) throws FileStorageException {
		try {
			minioClient.uploadObject(UploadObjectArgs.builder().bucket(bucket).object(toFile).filename(fromFile).build());
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
		
	}

	@Override
	public void upload(InputStream stream, String toFile) throws FileStorageException {
		try {
			minioClient.putObject(PutObjectArgs.builder().bucket(bucket).object(toFile).stream(stream, -1, 10485760).build());//TODO size?
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public void download(String fromFile, InputStream stream) throws FileStorageException {
		try {
			stream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(fromFile).build());
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public void download(String fromFile, String toFile) throws FileStorageException {
		try {
			minioClient.downloadObject(DownloadObjectArgs.builder().bucket(bucket).object(fromFile).filename(toFile).build());
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public boolean exist(String file) throws FileStorageException {
		try {
			minioClient.statObject(StatObjectArgs.builder().bucket(bucket).object(file).build());
			return true;
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public void remove(String file) throws FileStorageException {
		try {
			minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(file).build());
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
	}

}
