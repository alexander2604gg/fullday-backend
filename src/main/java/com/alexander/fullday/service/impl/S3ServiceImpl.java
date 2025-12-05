package com.alexander.fullday.service.impl;

import com.alexander.fullday.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile file, String documentNumber, Integer paymentId) {

        String extension = Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf("."));

        String key = "payments/" + documentNumber + "/" + paymentId
                + "/voucher-" + System.currentTimeMillis() + extension;

        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .contentType(file.getContentType())
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

            s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

            return "https://" + bucket + ".s3.amazonaws.com/" + key;

        } catch (IOException e) {
            throw new RuntimeException("Error subiendo imagen a S3", e);
        }
    }


}
