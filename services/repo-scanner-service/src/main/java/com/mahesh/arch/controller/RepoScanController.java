package com.mahesh.arch.controller;


import com.mahesh.arch.model.RepoScanRequest;
import com.mahesh.arch.service.RepoScanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scan")
public class RepoScanController{
    private final RepoScanService repoScanService;

    public RepoScanController(RepoScanService repoScanService) {
        this.repoScanService = repoScanService;
    }

    @GetMapping("/get")
    public String getsS(){
        return "Hello";
    }

    @PostMapping("/repo")
    public ResponseEntity<String> scanRepo(@RequestBody RepoScanRequest request){
        repoScanService.scan(request);
        return ResponseEntity.ok("Scan Started");
    }
}