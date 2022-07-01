package ro.fasttrackit.curs8.curs8homework.entity;

import lombok.*;
import ro.fasttrackit.curs8.curs8homework.entity.enums.Outcome;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CleaningProcedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Outcome outcome;
}
