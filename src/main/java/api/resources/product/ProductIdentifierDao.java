package api.resources.product;

import api.resources.BasicDao;
import entities.ProductIdentifier;
import jakarta.inject.Named;

@Named
public class ProductIdentifierDao extends BasicDao<ProductIdentifier> {

    public ProductIdentifierDao() {
        super(ProductIdentifier.class);
    }

    public ProductIdentifierDao(Class<ProductIdentifier> inferredClass) {
        super(inferredClass);
    }

}
