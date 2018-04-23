package com.taotao.manage.controller;

import com.taotao.common.pojo.PicUploadResult;
import com.taotao.manage.service.PropertiesService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("pic/upload")
public class PicUpLoadController {
    private static final List<String> extentions = Arrays.asList("jpg", "jpeg", "gif", "png", "bmp");
    @Autowired
    private PropertiesService propretiesService;

    /**
     * 1.大小
     * 2.是不是图片：后缀
     * 3.文件内容
     *
     * @param uploadFile
     * @return
     */
    @PostMapping
    public PicUploadResult uploadPicture(@RequestParam("uploadFile") MultipartFile uploadFile) {
        //设置图片上传结果
        PicUploadResult picUploadResult = new PicUploadResult();
        //默认不成功
        picUploadResult.setError(1).setMessage("文件上传不成功");
        //没上传文件
        if (uploadFile == null) {
            return picUploadResult;
        }

        try {
            //获取文件名, 验证后缀
            String originalFilename = uploadFile.getOriginalFilename();
            if (!extentions.contains(StringUtils.substringBeforeLast(originalFilename, "."))) {
                picUploadResult.setMessage("图片格式不正确!");
                return picUploadResult;
            }
            // 验证内容
            // 验证码
            BufferedImage image = ImageIO.read(uploadFile.getInputStream());
            if (image == null) {
                picUploadResult.setMessage(null);
                return picUploadResult;
            }
            // 验证通过
            picUploadResult.setError(0).setMessage("图片上传成功");
            String filePath = getFilePath(originalFilename);
            uploadFile.transferTo(new File(filePath));
            String url = StringUtils.replace(StringUtils.replace(filePath, propretiesService.REPOSITORY_PATH, propretiesService.IMAGE_URL), "\\", "/");
            picUploadResult.setUrl(url).setMessage(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picUploadResult;


    }

    @org.jetbrains.annotations.NotNull
    private String getFilePath(String sourceFileName) {
        String baseFolder = this.propretiesService.REPOSITORY_PATH + File.separator + "images";
        Date nowDate = new Date();
        // yyyy/MM/dd
        String fileFolder = baseFolder + File.separator + new DateTime(nowDate).toString("yyyy") + File.separator
                + new DateTime(nowDate).toString("MM") + File.separator + new DateTime(nowDate).toString("dd");
        File file = new File(fileFolder);
        if (!file.isDirectory()) {
            // 如果目录不存在，则创建目录
            file.mkdirs();
        }
        // 生成新的文件名
        String fileName = new DateTime(nowDate).toString("yyyyMMddhhmmssSSSS") + RandomUtils.nextInt(100, 9999) + "."
                + StringUtils.substringAfterLast(sourceFileName, ".");
        return fileFolder + File.separator + fileName;
    }

}
