package com.linkjb.servicebase.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author sharkshen
 * @data 2019/3/24 0:37
 * @Useage
 */
public class DownloadUtil {
    public static void downloadImage(String Imageurl,String filename) throws IOException {
//        System.getProperties().setProperty("http.proxyHost", "IP");//设置代理
//        System.getProperties().setProperty("http.proxyPort", "Port");
        URL url = new URL(Imageurl);
        //打开网络输入流
        DataInputStream dis = new DataInputStream(url.openStream());
        String newImageName="D://tmp//"+filename+".jpg";
        //建立一个新的文件
        FileOutputStream fos = new FileOutputStream(new File(newImageName));
        byte[] buffer = new byte[1024];
        int length;
        //开始填充数据
        while((length = dis.read(buffer))>0){
            fos.write(buffer,0,length);
        }
        dis.close();
        fos.close();
    }
}
