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
        String originalFilename = file.getOriginalFilename();
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
        
        if (resourceType.equals("raw") && originalFilename != null) {
            String publicId = originalFilename.replaceAll("\\s+", "_").replaceAll("\\.[^.]+$", "");
            options.put("public_id", "papers/" + publicId); // optional folder prefix

            if (originalFilename.endsWith(".docx")) {
                options.put("format", "docx");
            } else if (originalFilename.endsWith(".doc")) {
                options.put("format", "doc");
            } else if (originalFilename.endsWith(".pdf")) {
                options.put("format", "pdf");
            }
        }

        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), options);
        return result.get("secure_url").toString();
    }
}
