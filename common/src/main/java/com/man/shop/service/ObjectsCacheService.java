package com.man.shop.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by smanolache on 4/1/2017.
 */
@Service
public class ObjectsCacheService {

    private ConcurrentHashMap<String, SimpleDateFormat> dateFormatCache;

    public SimpleDateFormat getDateFormat(String format){
        if (dateFormatCache.containsKey(format)){
            return dateFormatCache.get(format);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            dateFormatCache.put(format, simpleDateFormat);
            return simpleDateFormat;
        }
    }
}
