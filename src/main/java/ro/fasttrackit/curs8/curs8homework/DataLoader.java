package ro.fasttrackit.curs8.curs8homework;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs8.curs8homework.entity.*;
import ro.fasttrackit.curs8.curs8homework.entity.enums.Outcome;
import ro.fasttrackit.curs8.curs8homework.repository.ReviewRepository;
import ro.fasttrackit.curs8.curs8homework.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public void run(String... args) {
        List<Room> rooms = roomRepository.saveAll(
                List.of(Room.builder()
                                .number("13b")
                                .etaj(2)
                                .hotelName("Hotel Marina")
                                .facilities(
                                        RoomFacilities.builder()
                                                .doubleBed(true)
                                                .tv(false)
                                                .build()
                                )
                                .cleanUps(List.of(
                                        CleanUp.builder()
                                                .date(LocalDate.of(2022, 6, 12))
                                                .procedure("big cleanup")
                                                .cleaningProcedure(
                                                        CleaningProcedure.builder()
                                                                .name("name1")
                                                                .outcome(Outcome.DONE)
                                                                .build())
                                                .build(),
                                        CleanUp.builder()
                                                .date(LocalDate.now())
                                                .procedure("fast cleanup")
                                                .cleaningProcedure(
                                                        CleaningProcedure.builder()
                                                                .name("name2")
                                                                .outcome(Outcome.INPROGRESS)
                                                                .build())
                                                .build()
                                ))
                                .build(),
                        Room.builder()
                                .number("9a")
                                .etaj(1)
                                .hotelName("Hotel FiveStar")
                                .facilities(
                                        RoomFacilities.builder()
                                                .doubleBed(true)
                                                .tv(true)
                                                .build()
                                )
                                .cleanUps(List.of(
                                        CleanUp.builder()
                                                .date(LocalDate.of(2022, 7, 15))
                                                .procedure("big cleanup")
                                                .cleaningProcedure(
                                                        CleaningProcedure.builder()
                                                                .name("name1")
                                                                .outcome(Outcome.NOSTARTED)
                                                                .build())
                                                .build(),
                                        CleanUp.builder()
                                                .date(LocalDate.now())
                                                .procedure("big cleanup")
                                                .cleaningProcedure(
                                                        CleaningProcedure.builder()
                                                                .name("name2")
                                                                .outcome(Outcome.INPROGRESS)
                                                                .build())
                                                .build()
                                ))
                                .build())
        );


        reviewRepository.saveAll(List.of(
                Review.builder()
                        .mesaj("Good")
                        .rating(5)
                        .turist("Kamilla")
                        .room(rooms.get(1))
                        .build(),
                Review.builder()
                        .mesaj("Not so good")
                        .rating(4)
                        .turist("Andrei")
                        .room(rooms.get(0))
                        .build(),
                Review.builder()
                        .mesaj("Horible")
                        .rating(1)
                        .turist("Andrea")
                        .room(rooms.get(0))
                        .build()
        ));

    }
}
