package com.mahesh.arch.service;


import com.sun.org.apache.xml.internal.utils.UnImplNode;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class GitService {
    private final String BASE_DIR = "/tmp/repos";
    public Path cloneOrPull(String repoUrl,String branch) throws Exception{
       
    }
}
