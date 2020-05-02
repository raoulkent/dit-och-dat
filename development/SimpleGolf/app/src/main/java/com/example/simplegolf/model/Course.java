package com.example.simplegolf.model;

import com.example.simplegolf.model.converters.HoleConverter;
import com.example.simplegolf.model.converters.TeeConverter;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

@Entity
public class Course implements Serializable {
    @PrimaryKey
    @NonNull
    private String name;

    @TypeConverters(HoleConverter.class)
    private List<Hole> holes;

    @TypeConverters(TeeConverter.class)
    private List<Tee> tees;


    public Course(String name, List<Hole> holes, List<Tee> tees) {
        this.name = name;
        this.holes = holes;
        this.tees = tees;
    }

    private static boolean checkUniqueHoleHcpIndices(List<Hole> holes) {
        List<Integer> foundHcps = new ArrayList<>();
        for (Hole hole : holes) {
            int hcp = hole.getHcpIndex();
            if (foundHcps.contains(hcp)) {
                return false;
            }
            foundHcps.add(hcp);
        }
        return true;
    }

    private static boolean checkCourseSize(List<Hole> holes) {
        return holes.size() > 0 && holes.size() <= 18;
    }

    public int getTotalPar() {
        int i = 0;
        for (Hole h : holes) {
            i += h.getPar();
        }
        return i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hole> getHoles() {
        return holes;
    }

    public void setHoles(List<Hole> holes) {
        this.holes = holes;
    }

    public List<Tee> getTees() {
        return tees;
    }

    public void setTees(List<Tee> tees) {
        this.tees = tees;
    }
}