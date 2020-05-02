package com.example.simplegolf.model.converters;

import com.example.simplegolf.model.Hole;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class IntArrayConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static String fromIntArray(int[] shots) {
        return gson.toJson(shots);
    }

    @TypeConverter
    public static int[] fromString(String value) {
        Type listType = new TypeToken<int[]>(){}.getType();
        return gson.fromJson(value, listType);
    }
}
