package cz.vsb.magistri.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "subject")
@Getter
@Setter

public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;
     String name;
}
