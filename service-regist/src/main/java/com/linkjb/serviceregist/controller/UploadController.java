package com.linkjb.serviceregist.controller;

import com.linkjb.serviceregist.base.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sharkshen
 * @description 文件上传
 * @data 2019/4/27 15:13
 */
@RestController
public class UploadController {
    private static Logger log = LoggerFactory.getLogger(UploadController.class);
    @PostMapping("/File/{name}")
    public BaseResult<List<String>> uploadFile(@RequestParam("files") MultipartFile[] files , @PathVariable String name, @RequestHeader String Authorization){
        BaseResult<List<String>> re = new BaseResult<>();
        List<String> returnList = new ArrayList();
        try{
            for(MultipartFile f:files){
                //log.info("文件的原名为"+f.getOriginalFilename());
                //log.info("文件的后缀名为"+f.getOriginalFilename().substring(f.getOriginalFilename().indexOf(".")));
                File savePath = new File("D://学习资料aa");
                if(!savePath.exists()){
                    savePath.mkdir();
                }
                File dest = new File("D://学习资料aa//"+f.getOriginalFilename());
                f.transferTo(dest);
                returnList.add(dest.getAbsolutePath());
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        re.setEntity(returnList);

        return re;
    }

}
