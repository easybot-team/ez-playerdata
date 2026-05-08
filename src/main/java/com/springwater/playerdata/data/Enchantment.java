package com.springwater.playerdata.data;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
public class Enchantment {
    private String id;
    private @Nullable Integer idRaw;
    private int level;
}
