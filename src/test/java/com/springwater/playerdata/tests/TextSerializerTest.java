package com.springwater.playerdata.tests;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.junit.jupiter.api.Test;

public class TextSerializerTest {
    @Test
    public void doTest() {
        Component component = LegacyComponentSerializer.legacySection().deserialize("§c你好§fHello");
        System.out.println(component);

        String jsonText = JSONComponentSerializer.json().serialize(component);
        System.out.println(jsonText);

        Component deserializeJson = JSONComponentSerializer.json().deserialize("{\"color\":\"gold\",\"italic\":false,\"text\":\"长剑\"}");
        System.out.println(deserializeJson);
        
        String legacyText = LegacyComponentSerializer.legacySection().serialize(deserializeJson);
        System.out.println(legacyText);
    }
}
