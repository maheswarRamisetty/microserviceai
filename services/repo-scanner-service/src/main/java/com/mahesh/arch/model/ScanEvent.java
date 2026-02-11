package com.mahesh.arch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.util.List;

@Data
@Getter
@Setter
public class ScanEvent {
    private Path repo;
    private String branch;
    private List<FileMetadata> files;
}
