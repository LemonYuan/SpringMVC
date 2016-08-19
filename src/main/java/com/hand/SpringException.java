package com.hand;

/**
 * Created by lemon on 2016/8/18.
 */
public class SpringException extends RuntimeException{
    private String exceptionMsg;
    public SpringException(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
    public String getExceptionMsg(){
        return this.exceptionMsg;
    }
    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}