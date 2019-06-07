package com.example.sskey.dms.Utils;

public interface CallbackSSk<T> {
    void Success(T response, String message);
    void Fail(Throwable throwable);
}
