package com.AU.Exam.App.Model; import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "papers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String college;
    private String course;
    private int semester;
    private String subject;
    private String description;
    private String fileUrl;
    private String previewImageUrl;
}
