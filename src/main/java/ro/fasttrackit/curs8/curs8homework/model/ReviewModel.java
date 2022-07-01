package ro.fasttrackit.curs8.curs8homework.model;

import lombok.Builder;

@Builder
public record ReviewModel(Integer id,
                          String mesaj,
                          Integer rating,
                          String turist) {
}
