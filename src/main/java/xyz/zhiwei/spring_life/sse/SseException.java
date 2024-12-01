package xyz.zhiwei.spring_life.sse;

/**
 * @author zhiweicoding.xyz
 * @date 01/12/2024
 * @email diaozhiwei2k@gmail.com
 */
public class SseException extends RuntimeException{

    public SseException() {
    }

    public SseException(String message) {
        super(message);
    }

    public SseException(Throwable cause) {
        super(cause);
    }

    public SseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}