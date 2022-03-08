package api.context;

import entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Transactional(Transactional.TxType.REQUIRED)
public class BasicWebServiceOperation implements IBasicWebServiceOperation {

    @Context
    private SecurityContext securityContext;

    protected User claimedUser = null;
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
        this.claimedUser = (User) securityContext.getUserPrincipal();
    }

}
