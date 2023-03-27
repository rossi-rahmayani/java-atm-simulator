package com.rossi.javasimulatoratm.exception;

public class ValidationException extends Throwable{
    private Boolean status;
    public ValidationException(String message){
        super(message);
        this.status = Boolean.FALSE;
    }
}
