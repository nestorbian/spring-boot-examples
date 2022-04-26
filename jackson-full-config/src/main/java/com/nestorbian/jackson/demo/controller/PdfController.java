package com.nestorbian.jackson.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.jodconverter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nestorbian.jackson.demo.util.FileConvertUtil;

/**
 * pdf预览
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/11/30
 */
@RestController
public class PdfController {

    // 第一步：转换器直接注入
    @Autowired
    DocumentConverter documentConverter;

    /**
     * 直接预览pdf，将pdf文件字节输出到response的输出流种即可，无需设置响应头
     *
     * @param response
     * @return void
     * @date : 2021/11/30 22:49
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/pdf")
    public void pdf(HttpServletResponse response) {
        String path = "F:\\图灵资料\\公开课\\jvm\\jvisualvm安装Visual GC插件.pdf";
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            Files.copy(Paths.get(path), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 系统文件在线预览接口
     *
     * @param response
     * @return void
     * @date : 2021/11/30 22:50
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/preview")
    public void preview(HttpServletResponse response) throws Exception {
        onlinePreview1(response);
    }


    public void onlinePreview2(HttpServletResponse response) throws Exception {
        // 获取文件类型
        // String sourcePath = "C:\\Users\\Lenovo\\Desktop\\Anyest批量筛选入库改造点.doc";
        String sourcePath = "C:\\Users\\Lenovo\\Desktop\\anyest农行实施环境.xls";
        String[] str = sourcePath.split("\\.");

        if (str.length == 0) {
            throw new RuntimeException("文件格式不正确");
        }
        String suffix = str[str.length - 1];
        // 支持pdf,swf,xhtml,html,odt,sxw,doc,rtf,wpd,txt,wiki,ods,sxc,xls,csv,tsv,odp,sxi,ppt,odg,svg
        if (!suffix.equals("txt") && !suffix.equals("doc") && !suffix.equals("csv") && !suffix.equals("xls")
                && !suffix.equals("xlsx") && !suffix.equals("ppt") && !suffix.equals("pptx")) {
            throw new RuntimeException("文件格式不支持预览");
        }

        try (InputStream in = FileConvertUtil.convertLocaleFile(sourcePath, suffix);
                OutputStream outputStream = response.getOutputStream();) {
            // 创建存放文件内容的数组
            byte[] buff = new byte[1024];
            // 所读取的内容使用n来接收
            int n;
            // 当没有读取完时,继续读取,循环
            while ((n = in.read(buff)) != -1) {
                // 将字节数组的数据全部写入到输出流中
                outputStream.write(buff, 0, n);
            }
            // 强制将缓存区的数据进行输出
            outputStream.flush();
        }
    }

    /**
     * 方法一：springboot的方式直接注入DocumentConverter转换
     *
     * @param response
     * @return void
     * @date : 2021/11/30 22:50
     * @author : Nestor.Bian
     * @since : 1.0
     */
    public void onlinePreview1(HttpServletResponse response) throws Exception {
        // 获取文件类型
        // String sourcePath = "C:\\Users\\Lenovo\\Desktop\\Anyest批量筛选入库改造点.doc";
        String sourcePath = "C:\\Users\\Lenovo\\Desktop\\anyest农行实施环境.xlsx";
        String[] str = sourcePath.split("\\.");

        if (str.length == 0) {
            throw new RuntimeException("文件格式不正确");
        }
        String suffix = str[str.length - 1];
        // 支持swf,tiff,bmp,gif,csv,tif,tsv,jpeg,html,sxc,jpg,xlsx,otg,rtf,fodp,sxi,
        // svg,fods,png,fodt,wpd,otp,ott,docx,ots,pptx,txt,pdf,sxw,odg,ppt,doc,odp,fodg,odt,xls,ods

        try (OutputStream outputStream = response.getOutputStream();) {
            String format = "pdf";
            if ("xlsx".equals(suffix) || "xls".equals(suffix)) {
                format = "html";
            }
            documentConverter.convert(new File(sourcePath)).to(outputStream).as(
                    documentConverter.getFormatRegistry().getFormatByExtension(format)).execute();
        }
    }

}
