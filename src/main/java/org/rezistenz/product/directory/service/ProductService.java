package org.rezistenz.product.directory.service;

import java.util.Collection;
import java.util.Map;

import org.rezistenz.product.directory.model.Product;
import org.rezistenz.product.directory.persistence.EnityRepository;
import org.rezistenz.product.directory.persistence.ProductRepository;

public interface ProductService extends EnityRepository<Product, Long> {
}
