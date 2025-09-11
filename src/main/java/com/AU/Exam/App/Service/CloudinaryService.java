package com.AU.Exam.App.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {
        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return result.get("secure_url").toString();
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String mimeType = file.getContentType();
        String resourceType = "auto";

        if (mimeType != null) {
            if (mimeType.startsWith("image/")) {
                resourceType = "image";
            } else if (mimeType.equals("application/pdf") ||
                       mimeType.equals("application/msword") ||
                       mimeType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
                resourceType = "raw";
            }
        }

        Map<String, Object> options = new HashMap<>();
        options.put("resource_type", resourceType);

        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), options);
        return result.get("secure_url").toString();
    }
}
