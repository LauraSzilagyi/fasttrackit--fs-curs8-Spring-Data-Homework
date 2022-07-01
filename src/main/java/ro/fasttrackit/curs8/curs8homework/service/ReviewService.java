package ro.fasttrackit.curs8.curs8homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs8.curs8homework.entity.Review;
import ro.fasttrackit.curs8.curs8homework.exceptions.EntityNotFoundException;
import ro.fasttrackit.curs8.curs8homework.model.ReviewModel;
import ro.fasttrackit.curs8.curs8homework.repository.ReviewRepository;
import ro.fasttrackit.curs8.curs8homework.repository.RoomRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repository;
    private final RoomRepository roomRepository;

    public List<ReviewModel> getReviewsForRoom(Integer roomId) {
        verifyIfRoomExists(roomId);
        return repository.findByRoomId(roomId).stream()
                .map(this::mapToReviewModel)
                .toList();
    }

    private ReviewModel mapToReviewModel(Review review) {
        return ReviewModel.builder()
                .id(review.getId())
                .mesaj(review.getMesaj())
                .rating(review.getRating())
                .turist(review.getTurist())
                .build();
    }

    private void verifyIfRoomExists(Integer roomId) {
        roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException(roomId));
    }
}
