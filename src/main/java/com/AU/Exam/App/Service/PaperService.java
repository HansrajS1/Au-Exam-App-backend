package com.AU.Exam.App.Service;

import com.AU.Exam.App.Model.Paper;
import com.AU.Exam.App.Repository.PaperRepository;
import com.AU.Exam.App.DTO.PaperDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaperService {

    @Autowired
    private PaperRepository repo;

    @Autowired
    private ModelMapper mapper;

    public List<PaperDTO> getAllPapers() {
        return repo.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PaperDTO getPaperById(Long id) {
        return repo.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public List<PaperDTO> searchBySubject(String subject) {
        return repo.findBySubjectContainingIgnoreCase(subject).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PaperDTO savePaper(Paper paper) {
        return convertToDto(repo.save(paper));
    }

    public boolean deletePaper(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public PaperDTO updatePaper(PaperDTO dto, Long id) {
        return repo.findById(id).map(existing -> {
            mapper.map(dto, existing);
            existing.setId(id);
            Paper updated = repo.save(existing);
            return convertToDto(updated);
        }).orElse(null);
    }

    public Paper convertToEntity(PaperDTO dto) {
        return mapper.map(dto, Paper.class);
    }

    public PaperDTO convertToDto(Paper paper) {
        return mapper.map(paper, PaperDTO.class);
    }
}
