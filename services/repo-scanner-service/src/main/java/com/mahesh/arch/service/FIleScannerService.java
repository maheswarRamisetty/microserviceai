package com.mahesh.arch.service;

import com.mahesh.arch.model.FileMetadata;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import com.mahesh.arch.utils.HashUtils;

@Service
public class FIleScannerService {
    public List<FileMetadata> scan(Path repoPath) throws Exception{
        List<FileMetadata> files = new ArrayList<>();
        Files.walk(repoPath)
                .filter(Files::isRegularFile)
                .forEach(path -> {
                    try{
                        files.add(new FileMetadata(path.toString(),
                                Files.size(path),
                                HashUtils.sha256(path)));
                    } catch (IOException | NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                });
        return files;
    }

}
