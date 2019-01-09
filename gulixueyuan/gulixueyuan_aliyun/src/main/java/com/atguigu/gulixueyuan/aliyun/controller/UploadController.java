package com.atguigu.gulixueyuan.aliyun.controller;

import com.atguigu.entity.R;
import com.atguigu.gulixueyuan.aliyun.utils.AliyunOSSUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author helen
 * @since 2018/12/23
 */
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/aliyun/upload")
public class UploadController {

    /**
     * 文件上传
     *
     * @param avatar
     */
    @PostMapping("file")
    public R uploadBlog(@RequestParam("avatar") MultipartFile avatar) throws IOException {

        if (null != avatar) {
            String filename = avatar.getOriginalFilename();
            File newFile = new File(filename);
            avatar.transferTo(newFile);
            //上传到OSS
            String uploadUrl = AliyunOSSUtil.upload(newFile);
            return R.ok().message("文件上传成功").data(uploadUrl);
        }
        return R.error().message("请选择文件");

    }

}
