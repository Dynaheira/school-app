package cz.vsb.magistri.service;

import cz.vsb.magistri.dto.GradeDto;
import cz.vsb.magistri.entity.GradeEntity;
import cz.vsb.magistri.entity.SubjectEntity;
import cz.vsb.magistri.mapper.GradeMapper;
import cz.vsb.magistri.repository.GradeRepository;
import cz.vsb.magistri.repository.StudentRepository;
import cz.vsb.magistri.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    GradeMapper gradeMapper;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;

    public GradeDto addGrade(GradeDto gradeDto) {
        GradeEntity gradeToAdd = gradeMapper.toEntity(gradeDto);
        gradeToAdd.setStudent(studentRepository.getReferenceById(gradeDto.getStudentId()));
        gradeToAdd.setSubject(subjectRepository.getReferenceById(gradeDto.getSubjectId()));
        return gradeMapper.toDto(gradeRepository.save(gradeToAdd));
    }

    public List<GradeDto> getAllGrades() {
        List<GradeDto> allGrades = new ArrayList<>();
        for (GradeEntity gradeEntity : gradeRepository.findAll()) {
            //GradeDto gradeDto = new GradeDto();
            //gradeDto.setStudentDto(studentRepository.getReferenceById(gradeEntity.getStudentId()));
            allGrades.add(gradeMapper.toDto(gradeEntity));

        }
        return allGrades;
    }

    public GradeDto getGrade(int gradeId) {
        return gradeMapper.toDto(gradeRepository.getReferenceById(gradeId));
    }

    public GradeDto editGrade(int gradeId, GradeDto gradeDto) {
        gradeDto.setId(gradeId);
        GradeEntity gradeEntity = gradeRepository.getReferenceById(gradeId);
        gradeMapper.updateEntity(gradeDto, gradeEntity);
        gradeEntity.setSubject(subjectRepository.getReferenceById(gradeDto.getSubjectId()));
        gradeEntity.setStudent(studentRepository.getReferenceById(gradeDto.getStudentId()));
        return gradeMapper.toDto(gradeRepository.save(gradeEntity));
    }
    public GradeDto deleteGrade(int gradeId){
        GradeEntity gradeEntity = gradeRepository.getReferenceById(gradeId);
        GradeDto deleteGrade = gradeMapper.toDto(gradeEntity);
        gradeRepository.delete(gradeEntity);
        return deleteGrade;
    }
}
