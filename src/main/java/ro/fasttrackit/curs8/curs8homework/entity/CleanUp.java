package ro.fasttrackit.curs8.curs8homework.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.PERSIST;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CleanUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;
    private String procedure;

    @ManyToOne(cascade = PERSIST)
    private CleaningProcedure cleaningProcedure;
}
