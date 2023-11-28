package com.ll.sb20231117.global.reData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class RsData<T> {
    private final String resultCode;
    private final String msg;
    private T data;

    public boolean isSuccess() {
        return resultCode.startsWith("S-");
    }

    public boolean isFail() {
        return isSuccess() == false;
    }
}
