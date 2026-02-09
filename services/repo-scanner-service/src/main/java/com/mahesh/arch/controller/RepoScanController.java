package com.mahesh.arch.controller;


import com.mahesh.arch.model.RepoScanRequest;
import com.mahesh.arch.service.RepoScanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scan")
public class RepoScanController{
    private final RepoScanService repoScanService;

    public RepoScanController(RepoScanService repoScanService) {
        this.repoScanService = repoScanService;
    }

    @PostMapping
    public ResponseEntity<String> scanRepo(@RequestBody RepoScanRequest request){
        repoScanService.scan(request);
        return ResponseEntity.ok("Scan Started");
    }
}