package com.example.simplegolf.model.converters;

import androidx.room.TypeConverter;

import com.example.simplegolf.model.Hole;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Used to convert a list of Hole into JSON and from JSON to a list of Hole.
 * Required for Room to handle lists
 */
public class HoleConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static String fromList(List<Hole> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Hole> fromString(String value) {
        Type listType = new TypeToken<List<Hole>>() {
        }.getType();
        return gson.fromJson(value, listType);
    }
}
