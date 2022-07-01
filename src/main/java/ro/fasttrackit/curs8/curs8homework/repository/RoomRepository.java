package ro.fasttrackit.curs8.curs8homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs8.curs8homework.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
