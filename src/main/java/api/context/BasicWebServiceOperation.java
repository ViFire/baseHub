package api.context;

import entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

public class BasicWebServiceOperation implements IBasicWebServiceOperation {

    @Context
    private ResourceInfo resourceInfo;

    @Context
    private ContainerRequestContext context;

    protected User claimedUser;
    protected StatusReport response = new StatusReport();

    public StatusReport getStatus() {
        return response;
    }
    public void setStatus(StatusReport status) {
        this.response = status;
    }
    public void setPayload(Object payload) {
        response.setPayload(payload);
    }
    public Response createAndSendResponse() {
        if (response.getStatus() == StatusLevel.OK || response.getStatus() == StatusLevel.WARNING) {
            return Response.status(Response.Status.OK).entity(getStatus()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(getStatus()).build();
        }
    }

    @PostConstruct
    public void initResource() {
        this.claimedUser = context.getProperty("claimedUser") != null ? (User) context.getProperty("claimedUser") : null;
    }

}
