package ro.fasttrackit.curs8.curs8homework.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs8.curs8homework.entity.Room;
import ro.fasttrackit.curs8.curs8homework.exceptions.EntityNotFoundException;
import ro.fasttrackit.curs8.curs8homework.mapper.EntityMapper;
import ro.fasttrackit.curs8.curs8homework.model.CleanUpModel;
import ro.fasttrackit.curs8.curs8homework.model.RoomFilter;
import ro.fasttrackit.curs8.curs8homework.model.RoomModel;
import ro.fasttrackit.curs8.curs8homework.repository.RoomDao;
import ro.fasttrackit.curs8.curs8homework.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;
    private final RoomDao dao;
    private final EntityMapper mapper;

    public List<RoomModel> getAllFiltered(RoomFilter filters) {
        return dao.getAll(filters).stream()
                .map(mapper::mapToRoomModel)
                .toList();
    }

    public List<CleanUpModel> getCleanupForRoom(Integer roomId) {
        Room room = findRoomById(roomId);
        return mapper.mapCleanups(ofNullable(room.getCleanUps()).orElse(new ArrayList<>()));
    }

    public RoomModel getRoomById(Integer roomId) {
        Room room = findRoomById(roomId);
        return mapper.mapToRoomModel(room);
    }

    private Room findRoomById(Integer roomId) {
        return repository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException(roomId));
    }

    public Optional<Room> deleteRoomById(Integer roomId) {
        Optional<Room> roomById = repository.findById(roomId);
        roomById.ifPresent(repository::delete);
        return roomById;
    }

    public RoomModel updateRoom(Integer roomId, JsonPatch jsonPatch) {
        Room room = repository.findById(roomId)
                .map(dbEntity -> applyPatch(dbEntity, jsonPatch))
                .orElseThrow(() -> new EntityNotFoundException(roomId));

        repository.save(room);
        return mapper.mapToRoomModel(room);
    }


    private Room applyPatch(Room dbEntity, JsonPatch jsonPatch) {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.findAndRegisterModules();
            JsonNode jsonNode = jsonMapper.convertValue(dbEntity, JsonNode.class);
            JsonNode patchedJson = jsonPatch.apply(jsonNode);
            return jsonMapper.treeToValue(patchedJson, Room.class);
        } catch (JsonProcessingException | JsonPatchException e) {
            throw new RuntimeException(e);
        }
    }
}
