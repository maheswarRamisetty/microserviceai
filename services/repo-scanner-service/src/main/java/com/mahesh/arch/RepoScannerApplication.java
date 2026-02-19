package com.mahesh.arch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.mahesh.arch.config")
public class RepoScannerApplication {
    public static void main(String[] args){
        SpringApplication.run(RepoScannerApplication.class,args);
    }
}
