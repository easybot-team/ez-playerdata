package com.springwater.playerdata.data;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlayerData {
    private final static Gson gson = new Gson();
    private String uuid;
    private List<Item> inventory;
    
    @Override
    public String toString() {    
        return gson.toJson(this);
    }
}
