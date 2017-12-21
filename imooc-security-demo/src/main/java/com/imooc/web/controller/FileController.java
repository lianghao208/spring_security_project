package com.imooc.web.controller;

import com.imooc.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/18.
 * 处理文件上传、下载的Controller
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private String folder = "D:\\IntelliJ_Java_project\\code-spring-cloud\\spring_security_project\\imooc-security-demo\\src\\main\\java\\com\\imooc\\web\\controller";

    /**
     * 文件上传
     * 上传到本地服务器的folder路径下
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping
    public FileInfo upload(MultipartFile file) throws Exception{
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder,new Date().getTime()+".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    /**
     * 文件下载
     * 从本地服务器的folder路径下载
     * @param id
     * @param request
     * @param response
     */
    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        try(InputStream inputStream = new FileInputStream(new File(folder,id + ".txt"));//id为时间戳文件名
            OutputStream outputStream = response.getOutputStream();){

            response.setContentType("application/x-download");//声明下载文件的请求头
            response.addHeader("Content-Disposition","attachment;filename=test.txt");//下载后的文件名
            IOUtils.copy(inputStream, outputStream);//将输入流完全复制转换成输出流
            outputStream.flush();
        }catch (Exception e){

        }
    }

}
