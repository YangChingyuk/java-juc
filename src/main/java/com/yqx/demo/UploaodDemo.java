package com.yqx.demo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/**
 * Upload
 *
 * @author YangChingyu-k
 * @date 2019/9/12 13:44
 */
@Controller
public class UploaodDemo {

    public static final String UPLOAD_DIR = "C:/Users/YangChingyu-k/Desktop/upload";

    /**
     * 上传图片
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA)
    public JSONObject uploadImg(HttpServletRequest request, HttpServletResponse response, @RequestParam("imgFile") MultipartFile imgFile) throws Exception {
        JSONObject respJson = new JSONObject();
        String tempPath = UPLOAD_DIR;
        File localFile = null;
        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            try {
                request.setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            Iterator<String> iterator = multiRequest.getFileNames();
            if (iterator.hasNext()) {
                String name = imgFile.getOriginalFilename();
                name = System.currentTimeMillis() + name.substring(name.indexOf("."));
                if (null != name && name.length() > 0) {
                    localFile = new File(tempPath + File.separator + name);
                }
                // 上传
                if (!localFile.getParentFile().exists()) {
                    localFile.mkdirs();
                }
                try {
                    imgFile.transferTo(localFile);
                    respJson.put("status", true);
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                    respJson.put("status", false);
                }
            }
        }
        return respJson;

    }

}
