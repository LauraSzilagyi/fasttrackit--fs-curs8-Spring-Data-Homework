package ro.fasttrackit.curs8.curs8homework.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs8.curs8homework.entity.Room;
import ro.fasttrackit.curs8.curs8homework.model.CleanUpModel;
import ro.fasttrackit.curs8.curs8homework.model.ReviewModel;
import ro.fasttrackit.curs8.curs8homework.model.RoomFilter;
import ro.fasttrackit.curs8.curs8homework.model.RoomModel;
import ro.fasttrackit.curs8.curs8homework.service.ReviewService;
import ro.fasttrackit.curs8.curs8homework.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService service;
    private final ReviewService reviewService;


    @GetMapping
    List<RoomModel> getAll(RoomFilter filters) {
        return service.getAllFiltered(filters);
    }

    @GetMapping("{roomId}/reviews")
    List<ReviewModel> getReviewsForRoom(@PathVariable Integer roomId) {
        return reviewService.getReviewsForRoom(roomId);
    }

    @GetMapping("{roomId}/cleanUps")
    List<CleanUpModel> getCleanUpsForRoom(@PathVariable Integer roomId) {
        return service.getCleanupForRoom(roomId);
    }

    @GetMapping("{roomId}")
    RoomModel getRoomById(@PathVariable Integer roomId) {
        return service.getRoomById(roomId);
    }

    @PatchMapping("{roomId}")
    RoomModel updateRoom(@PathVariable Integer roomId, @RequestBody JsonPatch model) {
        return service.updateRoom(roomId, model);
    }

    @DeleteMapping("{roomId}")
    Room deleteRoomById(@PathVariable Integer roomId) {
        return service.deleteRoomById(roomId).orElse(null);
    }


}
