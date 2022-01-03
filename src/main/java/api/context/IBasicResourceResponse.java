package api.context;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

public interface IBasicResourceResponse {

    public StatusReport getStatus();
    public void setStatus(StatusReport status);
    public void setPayload(Object payload);
    public Response createAndSendResponse();

    @PostConstruct
    public void initResource();
}
