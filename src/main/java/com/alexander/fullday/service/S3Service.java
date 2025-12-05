package com.alexander.fullday.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadImage(MultipartFile file, String folder, Integer paymentId);

}
