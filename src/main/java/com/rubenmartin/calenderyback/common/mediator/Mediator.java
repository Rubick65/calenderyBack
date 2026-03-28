package com.rubenmartin.calenderyback.common.mediator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
/**
 * Conecta las diferentes capas dominio, aplicación e infraestructura
 * y funciona como un mediador entre ellas
 */
public class Mediator {

    // Como clave tiene la clase de los diferentes request y como valor el handler
    Map<? extends Class<?>, RequestHandler<?, ?>> requestHandlerMap;

    public Mediator(List<RequestHandler<?, ?>> requestHandlers) {
        requestHandlerMap = requestHandlers.stream().collect(Collectors.toMap(RequestHandler::getRequestType, Function.identity()));
    }

    /**
     * En función del tipo de request que se haga devuelve una respuesta acorde
     *
     * @param request Request que se va a realizar
     * @param <R>     Respuesta que se va a devolver
     * @param <T>     Request que se realiza
     * @return Devuelve la respuesta a esa request
     */
    public <R, T extends Request<R>> R dispatch(T request) {
        // Saca el handler del par clave valor en función del request
        RequestHandler<T, R> handler = (RequestHandler<T, R>) requestHandlerMap.get(request.getClass());

        // En caso de que sea nulo
        if (handler == null)
            // Lanza una excepción
            throw new RuntimeException("No handler found for request type " + request.getClass());

        // Devuelve la respuesta
        return handler.handle(request);
    }
}
