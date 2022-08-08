package ru.netology.std;

import ru.netology.std.NotFaundException;
import ru.netology.std.Product;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void save(Product product) {
        Product newProd = findById(product.getId());
        if (newProd != null) {
            throw new AlreadyExistsException(
                    "Продукт с данным ID: " + product.getId() + "уже добавлен" );
        }
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public void removeById(int id) {
        Product verifyId = findById(id);
        if (verifyId == null) {
            throw new NotFaundException( "Продукт с данным ID: " +id + "не найден" );
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    public Product[] getProducts() {
        return products;
    }
}
