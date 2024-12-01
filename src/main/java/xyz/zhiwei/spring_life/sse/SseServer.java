package xyz.zhiwei.spring_life.sse;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author zhiweicoding.xyz
 * @date 01/12/2024
 * @email diaozhiwei2k@gmail.com
 */
public interface SseServer {

    SseEmitter connect(String userId);

    boolean send(String userId, String content);

    boolean close(String userId);

}