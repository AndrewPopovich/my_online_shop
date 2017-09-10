package com.myshop.myonlineshop.validator;

import com.myshop.shopbackend.dto.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        if (checkFiles(product.getFiles())) {
            errors.rejectValue("files", null, "Please select five image files for upload!");
            return;
        }

        product.getFiles().forEach(f -> {
            if (!(f.getContentType().equals("image/jpeg") ||
                    f.getContentType().equals("image/png") ||
                    f.getContentType().equals("image/gif"))) {

                errors.rejectValue("files", null, "Please use only image files for upload!");
            }
        });
    }

    private boolean checkFiles(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            if (file.getSize() == 0) {
                return true;
            }
        }
        return false;
    }
}
