package ro.fasttrackit.curs8.curs8homework.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CleanUpModel(int id,
                           LocalDate date,
                           String procedure,
                           CleaningProcedureModel cleaningProcedure) {
}
