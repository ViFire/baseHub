package api.resources;

import api.context.BasicWebServiceOperation;
import api.security.UserRoles;
import database.dao.ProductDao;
import entities.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/test")
public class TestService extends BasicWebServiceOperation {

    @Inject
    private ProductDao repo;

    @Inject
    private ProductDao dao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UserRoles(values = {UserRole.PUBLIC})
    public Response getAccountById() {

        Product newProduct1 = new Product();
        Product newProduct2 = new Product();
        Product newProduct3 = new Product();
        newProduct1.setName("Product 1");
        newProduct2.setName("Product 2");
        newProduct3.setName("Product 3");

        Identifier isin = new Identifier();
        isin.setIdentName("ISIN");

        Identifier wkn = new Identifier();
        wkn.setIdentName("WKN");

        ProductIdentifier ident1 = new ProductIdentifier();
        ident1.setIdentName(isin);
        ident1.setIdentValue("DE0001");

        ProductIdentifier ident2 = new ProductIdentifier();
        ident2.setIdentName(isin);
        ident2.setIdentValue("DE0002");

        ProductIdentifier ident3 = new ProductIdentifier();
        ident3.setIdentName(wkn);
        ident3.setIdentValue("000000");


        newProduct1.addIdentifierMap(ident1);
        newProduct2.addIdentifierMap(ident2);
        newProduct3.addIdentifierMap(ident3);



        dao.persist(newProduct1);
        dao.persist(newProduct3);
        dao.persist(newProduct2);

        return createAndSendResponse();
    }
}
