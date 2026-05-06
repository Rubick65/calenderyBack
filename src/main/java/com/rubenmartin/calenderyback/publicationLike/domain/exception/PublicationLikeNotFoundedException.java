package com.rubenmartin.calenderyback.publicationLike.domain.exception;

public class PublicationLikeNotFoundedException extends RuntimeException {
    public PublicationLikeNotFoundedException(Long userId) {
        super("This publication dose not have any like from a user with " + userId + " id.");
    }
}
