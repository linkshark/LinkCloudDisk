package com.linkjb.servicebase.controller;

import com.linkjb.servicebase.error.BusinessException;
import com.linkjb.servicebase.error.EmBussnessError;
import com.linkjb.servicebase.response.CommonReturnType;
import com.linkjb.servicebase.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sharkshen
 * @data 2019/3/24 16:40
 * @Useage
 */
@RestController
@RequestMapping("spider")
//extends BaseController
public class SpiderController {
    @Autowired
    SpiderService spService;
    @RequestMapping(value = "/hello",method = RequestMethod.PUT)
    public void Hello() throws IOException {
        List<String> allList = spService.getAllUrl();
        allList.forEach(i-> {
            try {
                spService.getAndInsertData(i);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        });
        ;
    }
    @RequestMapping(value = "/hello/{url}",method = RequestMethod.PUT)
    public void Hello(@PathVariable("url") String url) throws IOException {
            try {
                spService.getAndInsertData(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        ;
    }


//    @GetMapping("/hello")
//    public CommonReturnType GoDie(@RequestParam(name = "id") Integer id) throws BusinessException {
//        CommonReturnType type = new CommonReturnType();
//        if(null==null){
//            throw new BusinessException(EmBussnessError.USER_NOT_EXIST);
//        }
//        return type;
//    }


}
