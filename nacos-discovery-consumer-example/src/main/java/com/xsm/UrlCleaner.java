package com.xsm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xsm
 * @Date 2019/10/13 11:32
 */
@Slf4j
public class UrlCleaner {
    public static String clean(String url) {
        log.info("enter urlCleaner");
        if (url.matches(".*/echo/.*")) {
            System.out.println("change url");
            url = url.replaceAll("/echo/.*", "/echo/{str}");
        }
        return url;
    }
}
