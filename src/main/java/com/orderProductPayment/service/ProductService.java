package com.orderProductPayment.service;

import com.orderProductPayment.dtos.ProductDto;
import com.orderProductPayment.model.Product;
import com.orderProductPayment.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    ProductRepo pr;

    public ProductDto createProduct(Product b){
        return utility(pr.save(b));
    }

    public Iterable<ProductDto> allProducts(){
        Set<ProductDto> pds = new HashSet<>();
        for (Product p : pr.findAll() ) {
            pds.add(utility(p));
        }
        return pds ;
    }

    public ProductDto oneProduct(Integer id){
        return utility(  pr.findById(id).orElse(new Product())  );
    }

    public ProductDto updateProduct(Integer id , Product p) {
        Product oldProduct =  pr.findById(id).orElse(new Product());
        if (p.getName() != null)
            oldProduct.setName(p.getName());
        if (p.getPrice() != null)
            oldProduct.setPrice(p.getPrice());
        pr.save(oldProduct);
        return utility(oldProduct);
    }

    public String deleteProduct(Integer id){
        Product p = pr.findById(id).orElse(new Product());
        if(p.getOrders().isEmpty())
        {
            pr.deleteById(id);
            return "Product Deleted";
        }
        return "An Order contains this product";
    }

    public static ProductDto utility(Product p) {
        ProductDto pd = new ProductDto();
        pd.productId = p.getId();
        pd.name = p.getName();
        pd.price = p.getPrice();
        return pd;
    }

}
