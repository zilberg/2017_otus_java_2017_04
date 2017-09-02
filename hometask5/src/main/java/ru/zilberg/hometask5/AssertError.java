package ru.zilberg.hometask5;

public class AssertError extends Error {
    public AssertError(){

    }
    private AssertError(String detailMessage){
        super(detailMessage);
    }

    public AssertError(Object detailMessage) {
        this(String.valueOf(detailMessage));
        if (detailMessage instanceof Throwable)
            initCause((Throwable) detailMessage);
    }

    public AssertError(String message, Throwable cause) {
        super(message, cause);
    }

}
