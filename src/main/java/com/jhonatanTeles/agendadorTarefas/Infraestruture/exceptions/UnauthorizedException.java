package com.jhonatanTeles.agendadorTarefas.Infraestruture.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {
    public UnauthorizedException(String message) {
        super(message);
    }
    public UnauthorizedException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
