package cz.vsb.magistri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GradeDto {
    private Integer id;
    private StudentDto studentDto;
    private SubjectDto subjectDto;
    private Integer studentId;
    private Integer subjectId;
    private String topic;
    private int mark;

}
