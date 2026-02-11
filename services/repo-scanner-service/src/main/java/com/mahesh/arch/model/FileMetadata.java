package com.mahesh.arch.model;

public class FileMetadata {
    private final String finalPath;
    private final long fileSize;
    private final String sha256Hash;


    public FileMetadata(String finalPath, long fileSize, String sha256Hash) {
        this.finalPath = finalPath;
        this.fileSize = fileSize;
        this.sha256Hash = sha256Hash;
    }
}
