package com.linkjb.servicebase.service.Impl;

import com.linkjb.servicebase.dao.LinkMediaMapper;
import com.linkjb.servicebase.dao.MediaMapper;
import com.linkjb.servicebase.pojo.LinkMedia;
import com.linkjb.servicebase.pojo.Media;
import com.linkjb.servicebase.service.SpiderService;
import com.linkjb.servicebase.utils.DownloadUtil;
import com.linkjb.servicebase.utils.HttpRequest;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author sharkshen
 * @data 2019/3/22 13:52
 * @Useage
 */
@Service
public class SpiderServiceImp implements SpiderService {
    @Resource
    MediaMapper mediaDAO;
    @Resource
    LinkMediaMapper linkMediaDAO;
    private static final Logger log = LoggerFactory.getLogger(SpiderServiceImp.class);
    private static String url = "https://www.meijutt.com/1_______.html";

//    public static void main(String[] args) throws IOException {
//            Spider sp = new Spider();
//            //sp.getAllUrl();
//        sp.getData();
//    }


    public String getPageNum() throws IOException {
        String number ="";
       try{
           Connection conn = Jsoup.connect(url).timeout(5000);
           conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
           conn.header("Accept-Encoding", "gzip, deflate, sdch");
           conn.header("Accept-Language", "zh-CN,zh;q=0.8");
           conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
           Document doc = conn.get();
           Elements select = doc.select(".page span");
            number =  select.get(0).text().substring(select.get(0).text().indexOf("/")+1);
       }catch (Exception e){
           e.printStackTrace();
       }
       return number;

    }
    public List<String> getUrl() throws IOException {
        List<String> list = new ArrayList<>(3000);
        try{
            String number = getPageNum();
            String url = "https://www.meijutt.com/";
            for(int i = 1;i<=Integer.parseInt(number);i++){
                list.add(url+i+"_______.html");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getAllUrl(){
        List<String> list = new ArrayList<>(5000);
        try{
            List<String> Allurl = getUrl();
            for (String url:Allurl){
                Document document = Jsoup.connect(url).timeout(5000).header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Encoding", "gzip, deflate, sdch")
                        .header("Accept-Language", "zh-CN,zh;q=0.8")
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                        .get();
                Elements select = document.select(".cn_box2 .bor_img3_right a");
//                String fileName="D:"+ File.separator+"allUrl.txt";
//                File f = new File(fileName);
//                OutputStream out = new FileOutputStream(f,true);//true表示追加模式，否则为覆盖
                for(Element currentEle:select){
//                    String href = "https://www.meijutt.com"+currentEle.attr("href")+"\r\n";
                    log.info("https://www.meijutt.com"+currentEle.attr("href"));
//                    byte[] b = href.getBytes();
//                    out.write(b);
                    list.add("https://www.meijutt.com"+currentEle.attr("href"));
                }
                //out.close();

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Async("asyncPromiseExecutor")
    //通过@Async注解表明该方法是一个异步方法，如果注解在类级别，表明该类下所有方法都是异步方法，
    // 而这里的方法自动被注入使用ThreadPoolTaskExecutor 作为 TaskExecutor
    public void getAndInsertData(String currentUrl) throws IOException {
        //List<String> allUrl = getAllUrl();

        //List<String> allUrl = new ArrayList<>();
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:/lastUrl.txt")),
//                "UTF-8"));
//        String line = null;
//        while((line=br.readLine())!=null){
//            log.info(line);
//            allUrl.add(line);
//        }
//        br.close();

//        String fileName="D:"+ File.separator+"download.txt";
//        File f = new File(fileName);
        //OutputStream out = new FileOutputStream(f,true);//true表示追加模式，否则为覆盖

            //for(String currentUrl:allUrl){
                try{

                Document document = Jsoup.connect(currentUrl).timeout(5000).header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Encoding", "gzip, deflate, sdch")
                        .header("Accept-Language", "zh-CN,zh;q=0.8")
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                        .get();
                Media m = new Media();
                m.setMediaName(document.select(".info-title span").text()+document.select(".info-title h1").text());
                if(!"".equals(document.select(".info-title span").text()+document.select(".info-title h1").text())){
                    int countByMediaName = mediaDAO.getCountByMediaName(document.select(".info-title span").text() + document.select(".info-title h1").text());
                    if(countByMediaName>=1){
                        return; //重复数据不爬取
                    }
                }else {
                    return;
                }
                    m.setPrimitiveName(document.select(".o_r_contact ul li").get(1).text().substring(document.select(".o_r_contact ul li").get(1).text().indexOf("：")+1));
                    m.setAlias(document.select(".o_r_contact ul li").get(2).text().substring(document.select(".o_r_contact ul li").get(2).text().indexOf("：")+1));
                    m.setScriptWriterName(document.select(".o_r_contact ul li").get(3).text().substring(document.select(".o_r_contact ul li").get(3).text().indexOf("：")+1,document.select(".o_r_contact ul li").get(3).text().indexOf("更多")));
                    m.setDirectorName(document.select(".o_r_contact ul li").get(4).text().substring(document.select(".o_r_contact ul li").get(4).text().indexOf("：")+1,document.select(".o_r_contact ul li").get(4).text().indexOf("更多")));
                    m.setActors(document.select(".o_r_contact ul li").get(5).text().substring(document.select(".o_r_contact ul li").get(5).text().indexOf("：")+1,document.select(".o_r_contact ul li").get(5).text().indexOf("更多")));
                    String Date = document.select(".o_r_contact ul li").get(6).text().substring(document.select(".o_r_contact ul li").get(6).text().indexOf("：") + 1);
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                Date premiereDate = df.parse(Date);
                    m.setBigMediaKind(document.select(".o_r_contact ul li").get(8).text().substring(document.select(".o_r_contact ul li").get(8).text().indexOf("：")+1));
                    m.setLocation(document.select(".o_r_contact ul li").get(9).select("label").text().substring(document.select(".o_r_contact ul li").get(9).select("label").text().indexOf("：")+1));
                    m.setTvStation(document.select(".o_r_contact ul li").get(10).select("label").text().substring(document.select(".o_r_contact ul li").get(10).select("label").text().indexOf("：")+1));
                    String secondDate = document.select(".o_r_contact ul li").get(11).select("label").text().substring(document.select(".o_r_contact ul li").get(11).select("label").text().indexOf("：")+1);
//                SimpleDateFormat dff = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                Date timeSchadule = dff.parse(secondDate);
                    m.setTimeSchadule(secondDate);
                    m.setPremiereDate(Date);
                    m.setMediaKind( document.select(".o_r_contact ul li").get(11).select("span").text().substring(document.select(".o_r_contact ul li").get(11).select("span").text().indexOf("：")+1));
                    //log.info(document.select("#score-stars").toString());
                    //m.setGrade(document.select("#schedule-score").attr("style"));
                    String params = "id="+currentUrl.substring(currentUrl.lastIndexOf("meiju")+5,currentUrl.lastIndexOf("."))+"&action=newstarscorevideo";
                    //log.info(params);
                    Thread.sleep(1000);
                    String s = HttpRequest.sendGet("https://www.meijutt.com/inc/ajax.asp", params);
                    //log.info(s);
                    String substring = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
                    String[] split = substring.split(",");
                    double v = Double.parseDouble(split[0]);
                    double v0 = Double.parseDouble(split[1]);
                    double v1 = Double.parseDouble(split[2]);
                    double v2 = Double.parseDouble(split[3]);
                    double v3 = Double.parseDouble(split[4]);
                    Double grade =(v*2+v0*4+v1*6+v2*8+v3*10)/(v+v0+v1+v2+v3);
                    DecimalFormat df3 = new DecimalFormat("#.00");
                    String str = df3.format(grade);
                    m.setGrade(str);
                    String imgUrl = document.select(".o_big_img_bg_b img").attr("src");
                    String imgName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.valueOf(new Random().nextInt(10000));
                    DownloadUtil.downloadImage(imgUrl,imgName);
                    m.setImage(imgName);
                mediaDAO.insert(m);
                //已经插入的url写入文件以下次断点续爬
//                     byte[] b = currentUrl.getBytes();
//                    out.write(b);
                Thread.sleep(100);
                Integer id = m.getId();
                //m1.setUrlName(document.select(".down_list").select(".max-height").select("li"));
                try{
                    Elements li = document.select(".down_list").get(0).select("ul li");
                    if(li.size()!=0){
                        for(int i=0;i<li.size();i++){
                            Thread.sleep(100);
                            Element element = li.get(i);
                            //log.info(element.toString());
                            LinkMedia m1 = new LinkMedia();
                            m1.setUrlName(element.select(".down_part_name").select("a").text());

                            m1.setSize(element.select("em").text());
                            m1.setLinkId(id);
                            m1.setUrlAddress(element.select(".down_part_name").select("a").attr("href").replaceAll("请输入ED2K://开头的","").replaceAll("地址",""));
                            //log.info( element.select("em").text());
//                        log.info(m1.toString());
                            linkMediaDAO.insert(m1);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    log.info(currentUrl);
                    return;
                }
                //log.info(li.toString());
                //log.info(Integer.valueOf(li.size()).toString());
            }catch (Exception e){
                    e.printStackTrace();
                    log.info(currentUrl);
                }
            //
        //}
            //out.close();


    }



}
