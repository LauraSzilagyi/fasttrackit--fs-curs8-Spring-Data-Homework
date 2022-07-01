package ro.fasttrackit.curs8.curs8homework.model;

import lombok.Builder;

import java.util.List;

@Builder
public record RoomModel(int id,
                        String number,
                        int etaj,
                        String hotelName,
                        RoomFacilitiesModel roomFacilities,
                        List<CleanUpModel> cleanUps) {
}
