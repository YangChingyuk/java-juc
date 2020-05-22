package com.yqx.demo;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 上传下载测试
 *
 * @author YangChingyu-k
 * @date 2019/9/12 10:55
 */
@Controller
public class DemoController {


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestParam("image") MultipartFile image, HttpServletRequest request) throws IOException {

        System.out.println(image.getContentType());
        System.out.println(image.getName());
        System.out.println(image.getOriginalFilename());

        ServletContext context = request.getServletContext();
        String realPath = context.getRealPath("/upload");

        String filName = "";
        filName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        File file = new File(realPath, filName);
        image.transferTo(file);
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response) {

        String path = request.getServletContext().getRealPath("/upload/");
        File file = new File(path, fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + "");
            byte[] b = new byte[2048];
            int len = 0;
            while ((len = fis.read(b)) != -1) {
                os.write(b, 0, len);
            }
            fis.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String fileName, HttpServletRequest request) throws Exception {
        String path = request.getServletContext().getRealPath("/upload/");
        File file = new File(path, fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "utf-8"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }

}
