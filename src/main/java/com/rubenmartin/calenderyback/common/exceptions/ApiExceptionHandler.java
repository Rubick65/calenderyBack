package com.rubenmartin.calenderyback.common.exceptions;

import com.rubenmartin.calenderyback.chat.domain.exception.ChatNotFoundException;
import com.rubenmartin.calenderyback.follower.domain.exception.FollowerNotFoundException;
import com.rubenmartin.calenderyback.follower.domain.exception.UserNotFollowingException;
import com.rubenmartin.calenderyback.message.domain.exception.MessageNotFoundException;
import com.rubenmartin.calenderyback.message.domain.exception.MessageStateNoUpdatedException;
import com.rubenmartin.calenderyback.publication.domain.exception.PublicationDeleteException;
import com.rubenmartin.calenderyback.publication.domain.exception.PublicationNotFoundException;
import com.rubenmartin.calenderyback.publicationLike.domain.exception.PublicationLikeNotFoundedException;
import com.rubenmartin.calenderyback.rol.domain.exception.RolNotFoundException;
import com.rubenmartin.calenderyback.user.domain.exception.PublicKeyNotFoundException;
import com.rubenmartin.calenderyback.user.domain.exception.UserAlreadyExistException;
import com.rubenmartin.calenderyback.user.domain.exception.UserDisableAccountException;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.vertificationToken.domain.exception.ExpiredVerificationTokenException;
import com.rubenmartin.calenderyback.vertificationToken.domain.exception.TokenNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach((error) ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI(),
                errors
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ErrorMessage userNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseBody
    public ErrorMessage duplicatedEmail(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RolNotFoundException.class)
    @ResponseBody
    public ErrorMessage rolNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TokenNotFoundException.class)
    @ResponseBody
    public ErrorMessage tokenNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredVerificationTokenException.class)
    @ResponseBody
    public ErrorMessage expiredToken(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(DisabledException.class)
    @ResponseBody
    public ErrorMessage accountDisable(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserDisableAccountException.class)
    @ResponseBody
    public ErrorMessage userAccountNotActive(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MessageNotFoundException.class)
    @ResponseBody
    public ErrorMessage messageNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ChatNotFoundException.class)
    @ResponseBody
    public ErrorMessage chatNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PublicationNotFoundException.class)
    @ResponseBody
    public ErrorMessage publicationNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FollowerNotFoundException.class)
    @ResponseBody
    public ErrorMessage followerNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PublicationLikeNotFoundedException.class)
    @ResponseBody
    public ErrorMessage publicationLikeNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(UserNotFollowingException.class)
    @ResponseBody
    public ErrorMessage userNotFollowing(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PublicKeyNotFoundException.class)
    @ResponseBody
    public ErrorMessage publicKeyNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(MessageStateNoUpdatedException.class)
    @ResponseBody
    public ErrorMessage messageStateNoUpdated(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(PublicationDeleteException.class)
    @ResponseBody
    public ErrorMessage invalidDeletePublication(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                request.getRequestURI()
        );
    }


}
