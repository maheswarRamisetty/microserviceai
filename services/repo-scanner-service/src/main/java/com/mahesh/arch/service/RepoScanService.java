package com.mahesh.arch.service;

import com.mahesh.arch.kafka.ScanEventProducer;
import com.mahesh.arch.model.FileMetadata;
import com.mahesh.arch.model.RepoScanRequest;
import com.mahesh.arch.model.ScanEvent;
import org.springframework.stereotype.Service;
import java.util.List;

import java.nio.file.Path;

@Service
public class RepoScanService {
    private final GitService gitService;
//    private final ScanEvent scanEvent;
    private final FIleScannerService fIleScannerService;
    private final ScanEventProducer scanEventProducer;


    public RepoScanService(GitService gitService, FIleScannerService fIleScannerService, ScanEventProducer scanEventProducer) {
        this.gitService = gitService;

        this.fIleScannerService = fIleScannerService;
        this.scanEventProducer = scanEventProducer;
    }

    public void scan(RepoScanRequest request){
        request.getBranches().forEach(branch -> {
            try{
                Path repoPath = gitService.cloneOrPull(request.getRepoUrl(),branch);
                List<FileMetadata> files = fIleScannerService.scan(repoPath);
                ScanEvent event = new ScanEvent();
                event.setRepo(repoPath);
                event.setBranch(branch);
                event.setFiles(files);

                scanEventProducer.publish(event);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
