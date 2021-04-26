package com.example.model;

public class CkEditorUploadImageResponse {
    
    private Integer uploaded;
    
    private String fileName;
    
    private String url;
    
    private CkEditorUploadImageResponse.Error error;
    
    public static CkEditorUploadImageResponse success(String fileName, String url) {
        CkEditorUploadImageResponse response = new CkEditorUploadImageResponse();
        response.setUploaded(1);
        response.setFileName(fileName);
        response.setUrl(url);
        return response;
    }
    
    public static CkEditorUploadImageResponse warn(String fileName, String url, String message) {
        CkEditorUploadImageResponse response = new CkEditorUploadImageResponse();
        response.setUploaded(1);
        response.setFileName(fileName);
        response.setUrl(url);
        CkEditorUploadImageResponse.Error error = new Error(message);
        response.setError(error);
        return response;
    }
    
    public static CkEditorUploadImageResponse error(String message) {
        CkEditorUploadImageResponse response = new CkEditorUploadImageResponse();
        response.setUploaded(0);
        CkEditorUploadImageResponse.Error error = new Error(message);
        response.setError(error);
        return response;
    }
    
    public Integer getUploaded() {
        return uploaded;
    }

    public void setUploaded(Integer uploaded) {
        this.uploaded = uploaded;
    }

    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CkEditorUploadImageResponse.Error getError() {
        return error;
    }

    public void setError(CkEditorUploadImageResponse.Error error) {
        this.error = error;
    }

    public static class Error {
        
        private String message;
        
        public Error(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        
    }
    

}
