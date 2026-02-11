package com.mahesh.arch.service;


import com.sun.org.apache.xml.internal.utils.UnImplNode;
import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GitService {
    private final String BASE_DIR = "/tmp/repos";
    public Path cloneOrPull(String repoUrl,String branch) throws Exception{
       String repoName = repoUrl.substring(repoUrl.lastIndexOf("/")+1);
       Path repoPath = Paths.get(BASE_DIR,repoName,branch);
       if(Files.exists(repoPath)){
           Git git = Git.open(repoPath.toFile());
           git.pull().call();
           return repoPath;
       }
       Files.createDirectories(repoPath);
       Git.cloneRepository()
               .setURI(repoUrl)
               .setDirectory(repoPath.toFile())
               .setBranch(branch)
               .setDepth(1)
               .call();

       return repoPath;
    }
}
