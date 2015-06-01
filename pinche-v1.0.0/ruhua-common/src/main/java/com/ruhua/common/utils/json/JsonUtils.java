//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.utils.json;



import java.util.List;

public class JsonUtils {
    private static JsonBinder jsonBinder = JsonBinder.buildNormalBinder();

    public JsonUtils() {
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        return jsonBinder.fromJson(jsonString, clazz);
    }

    public static <T> List<T> fromJsonArray(String jsonArray, Class<T> clazz) {
        return jsonBinder.fromJsonArray(jsonArray, clazz);
    }

    public static String toJson(Object object) {
        return jsonBinder.toJson(object);
    }
}
