package com.mahesh.arch.service;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

@Service
public class GitService {

    @Value("${git.base-dir:C:/tmp/repos}")
    private String BASE_DIR;

    @Value("${git.token}")
    private String gitToken;

    public Path cloneOrPull(String repoUrl, String branch) throws Exception {
        String repoName = repoUrl.substring(repoUrl.lastIndexOf("/") + 1).replace(".git", "");
        Path repoRootPath = Paths.get(BASE_DIR, repoName);
        File repoRoot = repoRootPath.toFile();
        File dotGit = new File(repoRoot, ".git");

        UsernamePasswordCredentialsProvider credentials =
                new UsernamePasswordCredentialsProvider("token", gitToken);

        if (dotGit.exists()) {
            try (Git git = Git.open(repoRoot)) {
                git.checkout().setName(branch).call();
                git.pull().setCredentialsProvider(credentials).call();
                return repoRootPath;
            }
        } else {
            if (repoRoot.exists()) {
                deleteRecursively(repoRoot.toPath());
            }
            try (Git git = Git.cloneRepository()
                    .setURI(repoUrl)
                    .setDirectory(repoRoot)
                    .setBranch("refs/heads/" + branch)
                    .setCredentialsProvider(credentials)
                    .setCloneAllBranches(false)
                    .setDepth(1)
                    .call()) {
                git.checkout().setName(branch).call();
                return repoRootPath;
            }
        }
    }

    private void deleteRecursively(Path path) throws Exception {
        if (!Files.exists(path)) return;
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .forEach(p -> {
                    try { Files.deleteIfExists(p); }
                    catch (Exception e) { throw new RuntimeException("Failed to delete " + p, e); }
                });
    }

}