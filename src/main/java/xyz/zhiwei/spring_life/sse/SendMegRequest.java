package xyz.zhiwei.spring_life.sse;

/**
 * @author zhiweicoding.xyz
 * @date 01/12/2024
 * @email diaozhiwei2k@gmail.com
 */
public class SendMegRequest {

    private String userId;

    private String msg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSendMsg(){
        return userId + " send send to you : " + msg;
    }

}