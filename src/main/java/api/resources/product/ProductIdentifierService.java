package api.resources.product;

import api.context.BasicWebServiceOperation;
import api.security.UserRoles;
import entities.ProductIdentifier;
import entities.UserRole;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.Set;

@Path("/identifier")
public class ProductIdentifierService extends BasicWebServiceOperation {

    @Inject
    private ProductIdentifierDao repo;

    @GET
    @Path("id/{productIdentifierId}")
    @Produces(MediaType.APPLICATION_JSON)
    @UserRoles(values = {UserRole.ADMIN})
    public Response getProductIdentifierById(@PathParam("productIdentifierId") int id) {
        Optional<ProductIdentifier> identifier = repo.find(id);

        if (identifier.isPresent()) {
            response.setPayload(identifier.get());
        } else {
            response.setResponseToError(null, "No product identifier exist", String.format("No user with id: %s exist", id));
        }
        return createAndSendResponse();
    }

    @POST
    @Produces("application/json")
    @UserRoles(values = {UserRole.ADMIN})
    public Response createOrUpdateProductIdentifier(ProductIdentifierDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ProductIdentifier ident = modelMapper.map(dto, ProductIdentifier.class);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ProductIdentifier>> constraintViolations = validator.validate(ident);

        if (!constraintViolations.isEmpty()) {
            response.setResponseToError(ident, "INVALID_PRODUCT_IDENTIFIER", "Product identifier with constraint violations");
        }

        try {
            repo.persist(ident);
        } catch (Exception e) {
            response.setResponseToError(ident, "Error", "Could not update or create product identifier");
        }

        return createAndSendResponse();
    }
}
