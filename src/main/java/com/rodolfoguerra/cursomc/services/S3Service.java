package com.rodolfoguerra.cursomc.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.rodolfoguerra.cursomc.services.exceptions.FileException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@Log4j2
public class S3Service {

    private final AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return uploadFile(is, fileName, contentType);
        } catch (IOException e) {
            throw new FileException("Error de IO " + e.getMessage());
        }
    }

    private URI uploadFile(InputStream is, String fileName, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            log.info("Iniciado");
            s3client.putObject(bucketName,fileName,is,metadata);
            log.info("Finalizado");
            return s3client.getUrl(bucketName,fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Error ao conveter URL para URI");
        }
    }
}