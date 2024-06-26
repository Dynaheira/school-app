package cz.vsb.magistri.service;

import cz.vsb.magistri.dto.StudentDto;
import cz.vsb.magistri.entity.StudentEntity;
import cz.vsb.magistri.mapper.StudentMapper;
import cz.vsb.magistri.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    public List<StudentDto> getStudents(){
        List<StudentEntity> studentEnities = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for(StudentEntity studentEntity : studentEnities){
            studentDtos.add(studentMapper.toDto(studentEntity));
        }
        return studentDtos;
    }
    public StudentDto addStudents (StudentDto studentDto){
        StudentEntity studentEntity = studentMapper.toEntity(studentDto);
        studentRepository.save(studentEntity);
        StudentEntity savedEntity = studentRepository.save(studentEntity);
        return studentMapper.toDto(savedEntity);
    }

    public StudentDto getStudent (Integer id){
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
       return studentMapper.toDto(studentEntity);
    }

    public StudentDto editStudent(Integer id, StudentDto studentDto){
        // StudentEntity studentToEdit = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (!studentRepository.existsById(id)){
            throw new EntityNotFoundException();
        }
        StudentEntity studentEntity = studentMapper.toEntity(studentDto);
        studentEntity.setId(id);
        StudentEntity savedStudent = studentRepository.save(studentEntity);
        return studentMapper.toDto(savedStudent);
    }

    public StudentDto deleteStudent(Integer id){
        StudentEntity studentToDelete = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        StudentDto deleteStudent = studentMapper.toDto(studentToDelete);
            studentRepository.delete(studentToDelete);
            return deleteStudent;
        }
    }

