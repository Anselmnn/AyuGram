package com.tech.ayugram.messenger;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    private static final Gson gson = new Gson();
    private static final Type LONG_LIST_TYPE = new TypeToken<ArrayList<Long>>(){}.getType();
    private static final Type STRING_LIST_TYPE = new TypeToken<ArrayList<String>>(){}.getType();
    private static final Type BOOLEAN_FLAGS_TYPE = new TypeToken<java.util.Map<String, Boolean>>(){}.getType();

    @TypeConverter
    public static String fromLongList(List<Long> list) {
        return list == null ? null : gson.toJson(list);
    }

    @TypeConverter
    public static List<Long> toLongList(String json) {
        return json == null ? new ArrayList<>() : gson.fromJson(json, LONG_LIST_TYPE);
    }

    @TypeConverter
    public static String fromStringList(List<String> list) {
        return list == null ? null : gson.toJson(list);
    }

    @TypeConverter
    public static List<String> toStringList(String json) {
        return json == null ? new ArrayList<>() : gson.fromJson(json, STRING_LIST_TYPE);
    }

    @TypeConverter
    public static String fromBooleanFlags(java.util.Map<String, Boolean> flags) {
        return flags == null ? null : gson.toJson(flags);
    }

    @TypeConverter
    public static java.util.Map<String, Boolean> toBooleanFlags(String json) {
        return json == null ? new java.util.HashMap<>() : gson.fromJson(json, BOOLEAN_FLAGS_TYPE);
    }
}