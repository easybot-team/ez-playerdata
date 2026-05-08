package com.springwater.playerdata.parser;

import com.springwater.playerdata.data.Enchantment;
import com.springwater.playerdata.data.Item;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.ListTag;
import net.querz.nbt.tag.ShortTag;
import net.querz.nbt.tag.StringTag;

import java.util.ArrayList;
import java.util.List;

public class ItemParser {
    public static Item parse(CompoundTag nbt) {
        Item item = new Item();

        if (nbt.containsKey("Slot")) item.setSlot(nbt.getByte("Slot"));
        else if (nbt.containsKey("slot")) item.setSlot(nbt.getByte("slot"));
        else item.setSlot(-1);

        if (nbt.containsKey("Count")) item.setCount(nbt.getByte("Count"));
        else if (nbt.containsKey("count")) item.setCount(nbt.getInt("count"));
        else item.setCount(1);

        if (nbt.containsKey("id")) item.setId(nbt.getString("id"));
        else item.setId("minecraft:air"); // 无法获取

        if (nbt.containsKey("Damage")) item.setDamage(nbt.getShort("Damage"));
        else item.setDamage(0);

        List<Enchantment> enchantments = new ArrayList<>();
        List<String> lore = new ArrayList<>();

        if (nbt.containsKey("components")) {
            CompoundTag components = nbt.getCompoundTag("components");
            if (components.containsKey("minecraft:enchantments")) {
                CompoundTag enchantmentsTag = components.getCompoundTag("minecraft:enchantments");
                if (enchantmentsTag.containsKey("levels")) {
                    // levels = [{"id": level}]
                    CompoundTag levels = enchantmentsTag.getCompoundTag("levels");
                    for (String keys : levels.keySet()) {
                        Enchantment enchantment = new Enchantment();
                        enchantment.setId(keys);
                        enchantment.setIdRaw(-1);
                        enchantment.setLevel(levels.getInt(keys));
                        enchantments.add(enchantment);
                    }
                }
            }

            if (components.containsKey("minecraft:custom_model_data"))
                item.setCustomModelData(components.getInt("minecraft:custom_model_data"));

            if (components.containsKey("minecraft:custom_name")) {
                String jsonCustomName = components.getString("minecraft:custom_name");
                Component deserializedJsonCustomName = JSONComponentSerializer.json().deserialize(jsonCustomName);
                item.setCustomName(LegacyComponentSerializer.legacySection().serialize(deserializedJsonCustomName));
            }

            if (components.containsKey("minecraft:lore")) {
                ListTag<StringTag> loreTag = components.getListTag("minecraft:lore").asStringTagList();
                for (StringTag loreJson : loreTag) {
                    Component deserializedJsonLore = JSONComponentSerializer.json().deserialize(loreJson.getValue());
                    lore.add(LegacyComponentSerializer.legacySection().serialize(deserializedJsonLore));
                }
            }

            if (components.containsKey("minecraft:custom_data")) {
                CompoundTag customData = components.getCompoundTag("minecraft:custom_data");
                if (customData.containsKey("itemsadder")) {
                    item.setIaItem(true);
                    CompoundTag itemsAdder = customData.getCompoundTag("itemsadder");
                    item.setIaNameSpace(itemsAdder.getString("namespace"));
                    item.setIaId(itemsAdder.getString("id"));
                }
            }


        } else if (nbt.containsKey("tag")) {
            CompoundTag tag = nbt.getCompoundTag("tag");
            if (tag.containsKey("StoredEnchantments")) {
                ListTag<CompoundTag> storedEnchantments = tag.getListTag("StoredEnchantments").asCompoundTagList();
                for (CompoundTag enchantmentTag : storedEnchantments) {
                    Enchantment enchantment = new Enchantment();
                    if(enchantmentTag.get("id") instanceof StringTag) {
                        enchantment.setId(enchantmentTag.getString("id"));
                        enchantment.setIdRaw(-1);
                    }else{
                        enchantment.setIdRaw((int) enchantmentTag.getShort("id"));
                        enchantment.setId(null);
                    }
                    enchantment.setLevel(enchantmentTag.getShort("lvl"));
                    enchantments.add(enchantment);
                }
            }
        }

        if (nbt.containsKey("display")) {
            CompoundTag display = nbt.getCompoundTag("display");
            if (display.containsKey("Name")) {
                ListTag<?> listNameTag = display.getListTag("Name");
                if (listNameTag == null) item.setCustomName(display.getString("Name"));
                else {
                    for (StringTag nameTag : listNameTag.asStringTagList()) {
                        if (jsonLike(nameTag.getValue())) {
                            Component deserializedJsonName = JSONComponentSerializer.json().deserialize(nameTag.getValue());
                            item.setCustomName(LegacyComponentSerializer.legacySection().serialize(deserializedJsonName));
                        } else {
                            item.setCustomName(nameTag.getValue());
                        }
                    }
                }
            }

            if (display.containsKey("Lore")) {
                ListTag<StringTag> loreTag = display.getListTag("Lore").asStringTagList();
                for (StringTag loreJson : loreTag) {
                    if (jsonLike(loreJson.getValue())) {
                        Component deserializedJsonLore = JSONComponentSerializer.json().deserialize(loreJson.getValue());
                        lore.add(LegacyComponentSerializer.legacySection().serialize(deserializedJsonLore));
                    }
                }
            }
        }

        item.setEnchantments(enchantments);
        item.setLore(lore);


        if (nbt.containsKey("CustomModelData")) item.setCustomModelData(nbt.getInt("CustomModelData"));

        if (nbt.containsKey("itemsadder")) {
            item.setIaItem(true);
            CompoundTag itemsAdder = nbt.getCompoundTag("itemsadder");
            item.setIaNameSpace(itemsAdder.getString("namespace"));
            item.setIaId(itemsAdder.getString("id"));
        }

        item.setModItem(
                !item.getId().startsWith("minecraft:")
        );

        return item;
    }

    private static Boolean jsonLike(String json) {
        return json.startsWith("{") && json.endsWith("}");
    }
}
