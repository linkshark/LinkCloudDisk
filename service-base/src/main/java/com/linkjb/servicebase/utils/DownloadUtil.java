package com.linkjb.servicebase.utils;

import org.springframework.scheduling.annotation.Async;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author sharkshen
 * @data 2019/3/24 0:37
 * @Useage
 */

public class DownloadUtil {
    @Async("asyncPromiseExecutor")
    public static void downloadImage(String Imageurl,String filename) throws IOException {
//        System.getProperties().setProperty("http.proxyHost", "IP");//设置代理
//        System.getProperties().setProperty("http.proxyPort", "Port");
        URL url = new URL(Imageurl);
        // 打开连接
        URLConnection con = url.openConnection();
        con.setConnectTimeout(5*1000);
        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        // 输入流
        InputStream is = con.getInputStream();

        //打开网络输入流
//        DataInputStream dis = new DataInputStream(url.openStream());
//        File f = new File("/Users/shark/Desktop/tmp");
//        if(!f.exists()){
//            f.mkdirs();
//        }
//        String newImageName="/Users/shark/Desktop/tmp/"+filename+".jpg";
//        //建立一个新的文件
//        FileOutputStream fos = new FileOutputStream(new File(newImageName));
//        byte[] buffer = new byte[1024];
//        int length;
//        //开始填充数据
//        while((length = dis.read(buffer))>0){
//            fos.write(buffer,0,length);
//        }
//        dis.close();
//        fos.close();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File("/Users/shark/Desktop/tmp/");
        if(!sf.exists()){
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath()+"/"+filename+".jpg");
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();

    }
    public static void main(String[] args) throws IOException {
        DownloadUtil.downloadImage("https://img.kukan5.com/pic/uploadimg/2019-11/520012.jpg","shark");
    }
}
