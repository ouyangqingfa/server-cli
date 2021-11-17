package com.ad.core.system.common;

/**
 * @author CoderYoung
 */
public class CustomException extends RuntimeException {

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException() {
        super();
    }
}
