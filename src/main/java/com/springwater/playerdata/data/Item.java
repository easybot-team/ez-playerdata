package com.springwater.playerdata.data;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
@Setter
public class Item {
    private int slot;
    private int count;
    private String id;
    private int damage;
    private List<Enchantment> enchantments;
    private List<String> lore;
    private String customName;
    
    private @Nullable Integer customModelData;
    
    private boolean isIaItem;
    private @Nullable String iaNameSpace;
    private @Nullable String iaId;
    
    private boolean isModItem;
}
