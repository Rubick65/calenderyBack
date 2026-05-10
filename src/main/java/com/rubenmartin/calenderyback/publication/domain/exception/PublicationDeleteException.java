package com.rubenmartin.calenderyback.publication.domain.exception;

public class PublicationDeleteException extends RuntimeException {
    public PublicationDeleteException(Long userId, Long publicationId) {
        super("User with " + userId + " ID can't delete publication with " + publicationId + " ID");
    }
}
