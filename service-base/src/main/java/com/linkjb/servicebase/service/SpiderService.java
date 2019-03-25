package com.linkjb.servicebase.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author sharkshen
 * @data 2019/3/24 16:37
 * @Useage
 */
public interface SpiderService {
    public void getAndInsertData(String currentUrl) throws IOException;
    public List<String> getAllUrl();
}
