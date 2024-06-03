package baksakcci.shoppingmall.common.response;

import lombok.Getter;

@Getter
public class Response<T> {

    private final String status;
    private final int code;
    private final String msg;
    private final T data;

    private Response(String status, int code, String msg, T data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> success(int code, String msg, T data) {
        return new Response<>("SUCCESS", code, msg, data);
    }

    public static <T> Response<T> failure(int code, String msg, T data) {
        return new Response<>("FAILURE", code, msg, data);
    }

    public static <T> Response<T> error(int code, String msg) {
        return new Response<>("ERROR", code, msg, null);
    }

}