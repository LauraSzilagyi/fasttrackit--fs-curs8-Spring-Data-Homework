package ro.fasttrackit.curs8.curs8homework.repository;

import org.springframework.stereotype.Repository;
import ro.fasttrackit.curs8.curs8homework.entity.Room;
import ro.fasttrackit.curs8.curs8homework.entity.RoomFacilities;
import ro.fasttrackit.curs8.curs8homework.model.RoomFilter;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
public class RoomDao {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public RoomDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }


    public List<Room> getAll(RoomFilter filters) {
        CriteriaQuery<Room> criteria = criteriaBuilder.createQuery(Room.class);
        Root<Room> roomRoot = criteria.from(Room.class);
        Join<Room, RoomFacilities> facilitiesRoot = roomRoot.join("facilities", JoinType.LEFT);

        List<Predicate> whereClause = new ArrayList<>();
        ofNullable(filters.hotelName())
                .ifPresent(hotel -> whereClause.add(criteriaBuilder.equal(roomRoot.get("hotelName"), hotel)));
        ofNullable(filters.number())
                .ifPresent(number -> whereClause.add(criteriaBuilder.equal(roomRoot.get("number"), number)));
        ofNullable(filters.etaj())
                .ifPresent(etaj -> whereClause.add(criteriaBuilder.equal(roomRoot.get("etaj"), etaj)));
        ofNullable(filters.tv())
                .ifPresent(tv -> whereClause.add(criteriaBuilder.equal(facilitiesRoot.get("tv"), tv)));
        ofNullable(filters.doubleBed())
                .ifPresent(bed -> whereClause.add(criteriaBuilder.equal(facilitiesRoot.get("doubleBed"), bed)));

        CriteriaQuery<Room> query = criteria.select(roomRoot).where(whereClause.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
