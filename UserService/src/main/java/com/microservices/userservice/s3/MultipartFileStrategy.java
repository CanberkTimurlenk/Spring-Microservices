package com.microservices.userservice.s3;

import org.common.s3.FileStrategy;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class MultipartFileStrategy implements FileStrategy {

    private final MultipartFile multipartFile;

    public MultipartFileStrategy(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public String getName() {
        return multipartFile.getName();
    }

    @Override
    public InputStream getInputStream() {
        try {
            return multipartFile.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
