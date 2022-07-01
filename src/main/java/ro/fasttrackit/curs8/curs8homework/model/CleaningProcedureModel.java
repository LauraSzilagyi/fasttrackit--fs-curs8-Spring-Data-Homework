package ro.fasttrackit.curs8.curs8homework.model;

import lombok.Builder;
import ro.fasttrackit.curs8.curs8homework.enums.Outcome;

@Builder
public record CleaningProcedureModel(Integer id, String name, Outcome outcome) {
}
