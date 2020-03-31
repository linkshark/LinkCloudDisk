package com.linkjb.servicews.utils;

import com.alibaba.fastjson.JSON;
import com.linkjb.servicews.entity.LinkMedia;
import com.linkjb.servicews.entity.Media;
import com.linkjb.servicews.entity.TextMessage;
import com.linkjb.servicews.enums.MessageEnums;
import com.linkjb.servicews.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName MessageDispatcher
 * @Description TODO
 * @Author shark
 * @Data 2020/2/22 14:18
 **/
@Component
public class MessageDispatcher {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MovieService movieService;

    public  String processMessage(Map<String, String> map) {
        String openid = map.get("FromUserName"); //用户 openid
        String mpid = map.get("ToUserName");   //公众号原始 ID
        String content = map.get("Content");
        if (map.get("MsgType").equals(MessageEnums.REQ_MESSAGE_TYPE_TEXT)) {
            TextMessage txtmsg = new TextMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MessageEnums.RESP_MESSAGE_TYPE_TEXT);

            Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
            Matcher isNum = pattern.matcher(content);
            if(CheckUtil.isNumeric(content)){
                try{
                    //判断为纯数字,按照ID进行判断
                    List<LinkMedia> linkMediaByLinkId = movieService.getLinkMediaByLinkId(content);
                    if(linkMediaByLinkId.size()==0){
                        txtmsg.setContent("没有查询到具体数字对于的资源链接,请重试后再输入");
                        return MessageUtil.textMessageToXml(txtmsg);
                    }else{
                        //先判断一下redis中是否有缓存
                        if(redisUtil.get("movie-"+content)==null){
                            //redis中没有,自己拼好之后存储到redis中
                            StringBuilder s = new StringBuilder();
                            linkMediaByLinkId.forEach(item ->{
                                s.append(item.getUrlAddress()+"\r\n");
                            });
                            String result = s.toString();
                            if(result.length()>600){
                                result = result.substring(0, 600);
                            }
                            redisUtil.set("movie-"+content,result);
                            txtmsg.setContent(result);
                        }else{
                            txtmsg.setContent(redisUtil.get("movie-"+content));
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    txtmsg.setContent("囧囧囧~后台报错了...阿熊的代码水平还有待提高");
                }
                return MessageUtil.textMessageToXml(txtmsg);
            }else{
                //普通文本消息
                List<Media> mediaByText = movieService.getMediaByText(content);
                if(mediaByText.size()==0){
                    txtmsg.setContent("不好意思没有查询到具体的美剧,请输入例如 行尸走肉");
                    return MessageUtil.textMessageToXml(txtmsg);
                }else{
                    try{
                        StringBuilder s = new StringBuilder();
                        mediaByText.forEach(i ->{
                            if(s.toString().length()>=580){
                                return;
                            }else{
                                s.append(i.getId()+":"+i.getMediaName()+"\r\n");
                            }

                        });
                        System.out.println(s.toString());
                        System.out.println(s.length());
                        s.append("回复美剧名前的数字获取相应下载链接");
                        txtmsg.setContent(s.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                        txtmsg.setContent("囧囧囧~后台报错了...阿熊的代码水平还有待提高");
                    }
                    return MessageUtil.textMessageToXml(txtmsg);
                }
            }
        }
        return null;
    }
    public  String processEvent(Map<String, String> map) {
        System.out.println(map);
        String openid = map.get("FromUserName"); //用户 openid
        String mpid = map.get("ToUserName");   //公众号原始 ID
        if("subscribe".equals(map.get("Event"))){
            TextMessage txtmsg = new TextMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MessageEnums.RESP_MESSAGE_TYPE_TEXT);
            txtmsg.setContent("你好,欢迎关注公众号sharkshen,回复美剧名获取详情影视信息,如 行尸走肉 ");
            return MessageUtil.textMessageToXml(txtmsg);
        }
        return "你好";
    }
}
