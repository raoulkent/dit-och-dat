package com.example.simplegolf.model.converters;

import androidx.room.TypeConverter;

import com.example.simplegolf.model.Tee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Used to convert a list of Tee into JSON and from JSON to a list of Tee.
 * Required for Room to handle lists
 */
public class TeeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static String fromList(List<Tee> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Tee> fromString(String value) {
        Type listType = new TypeToken<List<Tee>>() {
        }.getType();
        return gson.fromJson(value, listType);
    }
}
