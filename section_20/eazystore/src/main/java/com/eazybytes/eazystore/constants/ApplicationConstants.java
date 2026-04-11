package com.eazybytes.eazystore.constants;

public class ApplicationConstants {
    private ApplicationConstants() {
        throw new AssertionError("Utility class cannot be instantiated.");
    }

    public static final String JWT_SECRET_KEY = "JWT_SECRET";
    public static final String JWT_SECRET_DEFAULT_VALUE = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    public static final String JWT_HEADER = "Authorization";

    public static final String ORDER_STATUS_CREATED = "CREATED";
    public static final String ORDER_STATUS_CONFIRMED = "CONFIRMED";
    public static final String ORDER_STATUS_CANCELLED = "CANCELLED";

    public static final String OPEN_MESSAGE = "OPEN";
    public static final String CLOSED_MESSAGE = "CLOSED";
    //public static final String JWT_SECRET_KEY = "uH4kYmxNAGoohuYHbYCM6akOrO7XMBavAvry83xOxpl";
    //public static final String JWT_SECRET_DEFAULT_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
}
