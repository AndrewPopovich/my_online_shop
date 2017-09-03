package com.myshop.myonlineshop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUploadUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtility.class);

    private static final String ABS_PATH = "/Users/andrew/myprojects/my_online_shop/myonlineshop/src/main/webapp/assets/images/";

    private static String realPath = "";

    public static void uploadFile(HttpServletRequest request, List<MultipartFile> files, String code) {
        LOGGER.debug("In uploadFile method");

        realPath = ABS_PATH + code + "/";

        File dir = new File(realPath);

        if (!new File(realPath).exists()) {
            new File(realPath).mkdirs();
        }

        int count = 0;
        try {
            for (MultipartFile f : files) {
                f.transferTo(new File(realPath + count + ".jpg"));
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
