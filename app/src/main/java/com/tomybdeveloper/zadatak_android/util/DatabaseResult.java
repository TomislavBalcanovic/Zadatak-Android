package com.tomybdeveloper.zadatak_android.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseResult {

    public final   Status status;

    @Nullable
    public String message;

    public DatabaseResult(Status status, @Nullable String message) {
        this.status = status;
        this.message = message;
    }



    public static DatabaseResult loading() {
        return new DatabaseResult(Status.LOADING, null);
    }

    public static DatabaseResult success(String message) {
        return new DatabaseResult(Status.SUCCESS, message);
    }

    public static DatabaseResult error(@NonNull String error) {
        return new DatabaseResult(Status.ERROR, error);
    }

    public static DatabaseResult idle() {
        return new DatabaseResult(Status.IDLE, null);
    }
}
