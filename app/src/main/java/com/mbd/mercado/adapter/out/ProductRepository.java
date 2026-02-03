package com.mbd.mercado.adapter.out;

import com.github.javafaker.Faker;
import com.mbd.mercado.domain.Product;
import com.mbd.mercado.domain.enums.ProductCategory;
import com.mbd.mercado.sk.AbstractRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductRepository extends AbstractRepository<Product> {
    private static final List<Product> PRODUCTS = new ArrayList<>();

    static {
        Faker faker = new Faker();
        for (int i = 0; i < 15; i++) {
            PRODUCTS.add(
                    Product.builder()
                            .id(UUID.randomUUID())
                            .description(faker.commerce().productName())
                            .value(new BigDecimal(faker.commerce().price(5.0, 2500.0)))
                            .category(ProductCategory.OUTROS)
                    .build());
        }
    }

    @Override
    public List<Product> getRepository() {
        return PRODUCTS;
    }
}
