package com.example.simplegolf.model.converters;

import androidx.room.TypeConverter;

import com.example.simplegolf.model.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Used to convert a list of Player into JSON and from JSON to a list of Player.
 * Required for Room to handle lists
 */
public class PlayerConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static String fromList(List<Player> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Player> fromString(String value) {
        Type listType = new TypeToken<List<Player>>() {
        }.getType();
        return gson.fromJson(value, listType);
    }
}
