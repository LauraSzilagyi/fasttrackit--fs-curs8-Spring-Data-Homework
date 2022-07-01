package ro.fasttrackit.curs8.curs8homework.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mesaj;
    private int rating;
    private String turist;

    @ManyToOne
    private Room room;
}
