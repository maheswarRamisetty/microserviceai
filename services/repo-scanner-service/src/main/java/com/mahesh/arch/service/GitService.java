package com.mahesh.arch.service;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GitService {

    @Value("${git.base-dir:C:/tmp/repos}")
    private String BASE_DIR;

    @Value("${git.token}")
    private String gitToken;

    public Path cloneOrPull(String repoUrl, String branch) throws Exception {
        String repoName = repoUrl.substring(repoUrl.lastIndexOf("/") + 1).replace(".git", "");
        Path repoPath = Paths.get(BASE_DIR, repoName, branch);
        File repoFile = repoPath.toFile();
        File dotGit = new File(repoFile, ".git");

        UsernamePasswordCredentialsProvider credentials =
                new UsernamePasswordCredentialsProvider("token", gitToken);

        if (dotGit.exists()) {
            try (Git git = Git.open(repoFile)) {
                git.pull()
                        .setCredentialsProvider(credentials)
                        .setRemote("origin")
                        .call();
                return repoPath;
            }
        } else {
            if (repoFile.exists()) {
                cleanDirectory(repoFile);
            }

            try (Git git = Git.cloneRepository()
                    .setURI(repoUrl)
                    .setDirectory(repoFile)
                    .setBranch(branch)
                    .setCredentialsProvider(credentials)
                    .setCloneAllBranches(false)
                    .setDepth(1)
                    .call()) {
                return repoPath;
            }
        }
    }

    private void cleanDirectory(File file) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                cleanDirectory(child);
            }
        }
        file.delete();
    }
}