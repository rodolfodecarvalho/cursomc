package com.rodolfoguerra.cursomc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Log4j2
public class S3Service {

    private final AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void uploadFile(String localFilePath) {
        try {
            File file = new File(localFilePath);
            log.info("Iniciando upload");
            s3client.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));
            log.info("Upload finalizado");
        } catch (AmazonServiceException e) {
            log.info("AmazonServiceException" + e.getErrorMessage());
            log.info("Status code" + e.getErrorCode());
        } catch (AmazonClientException e) {
            log.info("AmazonClientException" + e.getMessage());
        }
    }
}