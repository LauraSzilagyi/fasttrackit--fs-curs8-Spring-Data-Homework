package ro.fasttrackit.curs8.curs8homework.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;
    private int etaj;
    private String hotelName;

    @OneToOne(cascade = PERSIST)
    private RoomFacilities facilities;

    @ManyToMany(cascade = PERSIST)
    List<CleanUp> cleanUps;

}
