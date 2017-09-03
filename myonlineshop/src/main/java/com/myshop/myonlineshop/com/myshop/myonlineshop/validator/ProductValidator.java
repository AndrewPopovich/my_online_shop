package com.myshop.myonlineshop.com.myshop.myonlineshop.validator;

import com.myshop.shopbackend.dto.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        if (product.getFiles() == null || product.getFiles().size() != 5) {
            errors.rejectValue("file", null, "Please select five image files for upload!");
            return;
        }

        product.getFiles().forEach(f -> {
            if (!(f.getContentType().equals("image/jpeg") ||
                    f.getContentType().equals("image/png") ||
                    f.getContentType().equals("image/gif"))) {

                errors.rejectValue("file", null, "Please use only image files for upload!");
            }
        });
    }
}
