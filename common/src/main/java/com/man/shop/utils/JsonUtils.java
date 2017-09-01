package com.man.shop.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

/**
 * Created by smanolache on 3/22/2017.
 */
public class JsonUtils {

    public static <T> T fromJsonFile(File file, Type type) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);

        Gson gson = new Gson();
        return gson.fromJson(fileReader, type);
    }

    public static <T> T fromJsonFile(File file, Class<T> clazz) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);

        Gson gson = new Gson();
        return gson.fromJson(fileReader, clazz);
    }

    public static <T> String toJson(T object){
        Gson gson = new Gson();

        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz){
        Gson gson = new Gson();

        return gson.fromJson(json, clazz);
    }
}
