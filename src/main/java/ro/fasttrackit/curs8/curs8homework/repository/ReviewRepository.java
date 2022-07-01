package ro.fasttrackit.curs8.curs8homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs8.curs8homework.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByRoomId(Integer roomId);
}
