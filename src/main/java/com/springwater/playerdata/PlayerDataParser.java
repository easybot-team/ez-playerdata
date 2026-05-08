package com.springwater.playerdata;

import com.springwater.playerdata.data.Item;
import com.springwater.playerdata.data.PlayerData;
import com.springwater.playerdata.parser.ItemParser;
import com.springwater.playerdata.parser.UUIDParser;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.IntArrayTag;
import net.querz.nbt.tag.ListTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlayerDataParser {
    public static PlayerData parse(CompoundTag nbt) {
        PlayerData playerData = new PlayerData();
        Optional<UUID> uuid = UUIDParser.parse(nbt);
        if (uuid.isPresent()) playerData.setUuid(uuid.get().toString());
        else playerData.setUuid("");

        List<Item> inventory = new ArrayList<>();
        playerData.setInventory(inventory);

        if (nbt.containsKey("Inventory")) {
            ListTag<CompoundTag> inventoryTag = nbt.getListTag("Inventory").asCompoundTagList();
            for (CompoundTag itemTag : inventoryTag) {
                inventory.add(ItemParser.parse(itemTag));
            }
        }

        return playerData;
    }
}
