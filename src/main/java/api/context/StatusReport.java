package api.context;


import util.Pair;

import java.util.ArrayList;
import java.util.List;

public class StatusReport {

    //https://florimond.dev/en/posts/2018/08/restful-api-design-13-best-practices-to-make-your-users-happy/
    //https://phauer.com/2015/restful-api-design-best-practices/

    private StatusLevel status = StatusLevel.OK;
    private List<Pair<String,String>> meta = new ArrayList<>();
    private List<Pair<String,String>> message = new ArrayList<>();
    private Object payload = null;

    public StatusReport() {
    }

    public StatusLevel getStatus() {
        return status;
    }

    public void setStatus(StatusLevel status) {
        this.status = status;
    }
    public List<Pair<String, String>> getMeta() {
        return meta;
    }
    public void setMeta(List<Pair<String, String>> meta) {
        this.meta = meta;
    }
    public List<Pair<String, String>> getMessage() {
        return message;
    }
    public void setMessage(List<Pair<String, String>> message) {
        this.message = message;
    }
    public void addMessage(String key, String msg) {
        Pair<String,String> newMessage = new Pair<>(key,msg);
        message.add(newMessage);
    }
    public Object getPayload() {
        return payload;
    }
    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public void setResponseToSuccess(Object payload) {
        setStatus(StatusLevel.OK);
        setPayload(payload);
    }

    public void setResponseToError(Object payload, StatusMessage message) {
        setResponseToError(payload,message.getCode(), message.getDescription());
    }
    public void setResponseToError(Object payload, String messageKey, String message) {
        setStatus(StatusLevel.ERROR);
        addMessage(messageKey, message);
        setPayload(payload);
    }

}
