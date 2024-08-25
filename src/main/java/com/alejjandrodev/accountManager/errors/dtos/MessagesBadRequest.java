package com.alejjandrodev.accountManager.errors.dtos;

import java.util.List;

public class MessagesBadRequest extends  MessageError{
    public List<String> errors;

    public MessagesBadRequest(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

}
