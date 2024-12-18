package xyz.zhiwei.spring_life.sse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author zhiweicoding.xyz
 * @date 01/12/2024
 * @email diaozhiwei2k@gmail.com
 */

@Controller
@RequestMapping(value = "sse")
@ResponseBody
@CrossOrigin
public class SseController {


    @Autowired
    private SseServer sseServer;


    @GetMapping(value = "/subscribe/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable("userId") String userId){
        return sseServer.connect(userId);
    }


    @PostMapping(value = "/send/{userId}")
    public String sendMessage(@PathVariable("userId") String userId ,@RequestBody SendMegRequest sendMegRequest){
        if(sseServer.send(sendMegRequest.getUserId(), sendMegRequest.getSendMsg())){
            return "Success";
        }
        return "Faild";
    }

    @GetMapping(value = "/close/{userId}")
    public void close(@PathVariable("userId") String userId){
        sseServer.close(userId);
    }

}