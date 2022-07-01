package ro.fasttrackit.curs8.curs8homework.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Integer roomId) {
        super("Room with id %s doesn't exist".formatted(roomId));
    }
}
