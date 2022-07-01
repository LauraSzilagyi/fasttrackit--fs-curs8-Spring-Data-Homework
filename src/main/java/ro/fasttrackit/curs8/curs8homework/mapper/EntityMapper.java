package ro.fasttrackit.curs8.curs8homework.mapper;

import org.springframework.stereotype.Component;
import ro.fasttrackit.curs8.curs8homework.entity.CleanUp;
import ro.fasttrackit.curs8.curs8homework.entity.CleaningProcedure;
import ro.fasttrackit.curs8.curs8homework.entity.Room;
import ro.fasttrackit.curs8.curs8homework.entity.RoomFacilities;
import ro.fasttrackit.curs8.curs8homework.model.CleanUpModel;
import ro.fasttrackit.curs8.curs8homework.model.CleaningProcedureModel;
import ro.fasttrackit.curs8.curs8homework.model.RoomFacilitiesModel;
import ro.fasttrackit.curs8.curs8homework.model.RoomModel;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class EntityMapper {

    public RoomModel mapToRoomModel(Room room) {
        return RoomModel.builder()
                .id(room.getId())
                .number(room.getNumber())
                .etaj(room.getEtaj())
                .hotelName(room.getHotelName())
                .roomFacilities(ofNullable(room.getFacilities()).map(this::mapToRoomFacilitiesModel).orElse(null))
                .cleanUps(mapCleanups(ofNullable(room.getCleanUps()).orElse(new ArrayList<>())))
                .build();
    }

    private RoomFacilitiesModel mapToRoomFacilitiesModel(RoomFacilities roomFacilities) {
        return RoomFacilitiesModel.builder()
                .id(roomFacilities.getId())
                .tv(roomFacilities.isTv())
                .doubleBed(roomFacilities.isDoubleBed())
                .build();
    }

    public List<CleanUpModel> mapCleanups(List<CleanUp> cleanUps) {
        return cleanUps.stream().map(this::mapCleanup).toList();
    }

    public CleanUpModel mapCleanup(CleanUp cleanUp) {
        return CleanUpModel.builder()
                .id(cleanUp.getId())
                .date(cleanUp.getDate())
                .procedure(cleanUp.getProcedure())
                .cleaningProcedure(ofNullable(cleanUp.getCleaningProcedure())
                        .map(this::mapCleaningProcedure)
                        .orElse(null))
                .build();
    }

    private CleaningProcedureModel mapCleaningProcedure(CleaningProcedure cleaningProcedure) {
        return CleaningProcedureModel.builder()
                .id(cleaningProcedure.getId())
                .name(cleaningProcedure.getName())
                .outcome(cleaningProcedure.getOutcome())
                .build();
    }
}
