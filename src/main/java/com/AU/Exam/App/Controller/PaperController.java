package com.AU.Exam.App.Controller;

import com.AU.Exam.App.DTO.PaperDTO;
import com.AU.Exam.App.Model.Paper;
import com.AU.Exam.App.Service.CloudinaryService;
import com.AU.Exam.App.Service.PaperService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/papers")
@CrossOrigin(origins = {"http://localhost:5173","https://auexamweb.netlify.app"})
public class PaperController {

    @Autowired
    private PaperService service;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<PaperDTO> papers = service.getAllPapers();
            return ResponseEntity.ok(papers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch papers");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            PaperDTO paper = service.getPaperById(id);
            if (paper != null) {
                return ResponseEntity.ok(paper);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paper not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch paper");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String subject) {
        try {
            List<PaperDTO> results = service.searchBySubject(subject);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Search failed");
        }
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadPaperWithFiles(
            @RequestPart("data") String json,
            @RequestPart("preview") MultipartFile previewImage,
            @RequestPart("file") MultipartFile paperFile) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            PaperDTO dto = mapper.readValue(json, PaperDTO.class);

            String previewUrl = cloudinaryService.uploadImage(previewImage);
            dto.setPreviewImageUrl(previewUrl);

            String fileUrl = cloudinaryService.uploadFile(paperFile);
            dto.setFileUrl(fileUrl);

            Paper paper = service.convertToEntity(dto);
            PaperDTO saved = service.savePaper(paper);

            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed: " + e.getMessage());
        }
    }


    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> updatePaperWithFiles(
            @PathVariable Long id,
            @RequestPart("data") String json,
            @RequestPart(value = "preview", required = false) MultipartFile previewImage,
            @RequestPart(value = "file", required = false) MultipartFile paperFile) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            PaperDTO dto = mapper.readValue(json, PaperDTO.class);

            if (previewImage != null && !previewImage.isEmpty()) {
                String previewUrl = cloudinaryService.uploadImage(previewImage);
                dto.setPreviewImageUrl(previewUrl);
            }

            if (paperFile != null && !paperFile.isEmpty()) {
                String fileUrl = cloudinaryService.uploadFile(paperFile);
                dto.setFileUrl(fileUrl);
            }

            PaperDTO updated = service.updatePaper(dto, id);
            if (updated != null) {
                return ResponseEntity.ok(updated);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paper not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed: " + e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            boolean deleted = service.deletePaper(id);
            if (deleted) {
                return ResponseEntity.ok("Paper deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paper not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed");
        }
    }
}
