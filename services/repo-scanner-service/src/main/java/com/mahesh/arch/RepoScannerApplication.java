package com.mahesh.arch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.mahesh.arch.config",
        "com.mahesh.arch"
})
public class RepoScannerApplication {
    public static void main(String[] args){
        try {

            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            } };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            SSLParameters sslParams = new SSLParameters();
            sslParams.setEndpointIdentificationAlgorithm(null);
            SpringApplication.run(RepoScannerApplication.class,args);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize insecure SSL context", e);
        }

    }
}
