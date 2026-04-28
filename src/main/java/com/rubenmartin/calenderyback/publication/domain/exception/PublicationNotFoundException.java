package com.rubenmartin.calenderyback.publication.domain.exception;

public class PublicationNotFoundException extends RuntimeException {
    public PublicationNotFoundException(Long publicationId) {
        super("Publication with id " + publicationId + " not found");
    }
}
