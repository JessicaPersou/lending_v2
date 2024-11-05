package com.postech.lending.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ErrorMessageException extends RuntimeException {

    public static final long SERIAL_VERSION_UUID = 1L;

    public ErrorMessageException(String message) {
        super(message);
    }

    @Getter
    @AllArgsConstructor
    public enum Message {
        CLIENT_PROFILE_DISABLED("Cliente não pode ser atualizado, perfil está desabilitado."),
        CLIENT_DOCUMENT_EMPTY("Não temos cliente com esse documento."),
        CLIENT_NOT_FOUND("Cliente não encontrado: "),
        ADDRESS_EXCEEDED_LIMIT("Endereço não pode ser cadastrado, limite excedido. "),
        ADDRESS_NOT_UPDATE("Endereço não pode ser atualizado, houve um erro de processamento."),
        ADDRESS_NOT_DELETED("Endereço não apagado, verifique as informações e tente novamente.");


        private String messageEnum;
    }

}
