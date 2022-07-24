package database.dao;

import entities.Product;
import jakarta.inject.Named;

@Named
public class ProductDao extends BasicDao<Product> {

    public ProductDao() {
        super(Product.class);
    }

    public ProductDao(Class<Product> inferredClass) {
        super(inferredClass);
    }

}
