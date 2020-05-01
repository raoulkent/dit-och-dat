package com.example.simplegolf.model.converters;

import com.example.simplegolf.model.Hole;
import com.example.simplegolf.model.Tee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class TeeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static String fromList(List<Tee> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Tee> fromString(String value) {
        Type listType = new TypeToken<List<Tee>>(){}.getType();
        return gson.fromJson(value, listType);
    }
}
