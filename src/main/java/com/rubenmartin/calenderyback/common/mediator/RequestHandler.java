package com.rubenmartin.calenderyback.common.mediator;

/**
 * Interfaz genérica que recibe un tipo de request y devuelve una respuesta.
 *
 * @param <T> Tipo de request
 * @param <R> Respuesta a devlover
 */
public interface RequestHandler<T extends Request<R>, R> {

    /**
     * Gestiona las requests
     *
     * @param request Request a gestionar
     * @return Devuelve la respuesta en función del request
     */
    R handle(T request);

    /**
     * @return Devuelve la clase del request que se está realizando
     */
    Class<T> getRequestType();
}
