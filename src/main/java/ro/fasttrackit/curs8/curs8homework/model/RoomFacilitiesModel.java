package ro.fasttrackit.curs8.curs8homework.model;

import lombok.Builder;

@Builder
public record RoomFacilitiesModel(int id,
                                  boolean tv,
                                  boolean doubleBed) {
}
