package com.springwater.playerdata.tests;

import com.springwater.playerdata.PlayerDataParser;
import com.springwater.playerdata.data.PlayerData;
import net.querz.nbt.io.NBTDeserializer;
import net.querz.nbt.io.NamedTag;
import net.querz.nbt.tag.CompoundTag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class PlayerDataParserTests {
    private static final NBTDeserializer deserializer = new NBTDeserializer(true);
    private static final InputStream DATA_1_8_9
            = PlayerData.class.getClassLoader().getResourceAsStream("playerdata/1_8_9/2d4ce3bc-1ef0-405d-8afd-7bd4df975b29.dat");
    private static final InputStream DATA_1_12_1
            = PlayerData.class.getClassLoader().getResourceAsStream("playerdata/1_12_1/2d4ce3bc-1ef0-405d-8afd-7bd4df975b29.dat");
    private static final InputStream DATA_1_13
            = PlayerData.class.getClassLoader().getResourceAsStream("playerdata/1_13/2d4ce3bc-1ef0-405d-8afd-7bd4df975b29.dat");
    private static final InputStream DATA_1_14_4
            = PlayerData.class.getClassLoader().getResourceAsStream("playerdata/1_14_4/2d4ce3bc-1ef0-405d-8afd-7bd4df975b29.dat");
    private static final InputStream DATA_1_18_2
            = PlayerData.class.getClassLoader().getResourceAsStream("playerdata/1_18_2/2d4ce3bc-1ef0-405d-8afd-7bd4df975b29.dat");
    private static final InputStream DATA_1_20_1
            = PlayerData.class.getClassLoader().getResourceAsStream("playerdata/1_20_1/2d4ce3bc-1ef0-405d-8afd-7bd4df975b29.dat");
    private static final InputStream DATA_1_20_1_modded
            = PlayerData.class.getClassLoader().getResourceAsStream("playerdata/1_20_1_modded/2d4ce3bc-1ef0-405d-8afd-7bd4df975b29.dat");
    private static final InputStream DATA_1_21
            = PlayerData.class.getClassLoader().getResourceAsStream("playerdata/1_21/2d4ce3bc-1ef0-405d-8afd-7bd4df975b29.dat");
    private static final InputStream DATA_1_21_11
            = PlayerData.class.getClassLoader().getResourceAsStream("playerdata/1_21_11/2d4ce3bc-1ef0-405d-8afd-7bd4df975b29.dat");
    private static final InputStream DATA_1_21_IA
            = PlayerData.class.getClassLoader().getResourceAsStream("playerdata/1_21_ia/2d4ce3bc-1ef0-405d-8afd-7bd4df975b29.dat");
    private static final InputStream DATA_26_1
            = PlayerData.class.getClassLoader().getResourceAsStream("playerdata/26_1/2d4ce3bc-1ef0-405d-8afd-7bd4df975b29.dat");

    @Test
    public void test1_8_9() throws IOException {
        NamedTag tag = deserializer.fromStream(DATA_1_8_9);
        CompoundTag compoundTag = (CompoundTag) tag.getTag();
        PlayerData playerData = PlayerDataParser.parse(compoundTag);
        System.out.println(playerData);
    }

    @Test
    public void test1_12_1() throws IOException {
        NamedTag tag = deserializer.fromStream(DATA_1_12_1);
        CompoundTag compoundTag = (CompoundTag) tag.getTag();
        PlayerData playerData = PlayerDataParser.parse(compoundTag);
        System.out.println(playerData);
    }

    @Test
    public void test1_13() throws IOException {
        NamedTag tag = deserializer.fromStream(DATA_1_13);
        CompoundTag compoundTag = (CompoundTag) tag.getTag();
        PlayerData playerData = PlayerDataParser.parse(compoundTag);
        System.out.println(playerData);
    }

    @Test
    public void test1_14_4() throws IOException {
        NamedTag tag = deserializer.fromStream(DATA_1_14_4);
        CompoundTag compoundTag = (CompoundTag) tag.getTag();
        PlayerData playerData = PlayerDataParser.parse(compoundTag);
        System.out.println(playerData);
    }

    @Test
    public void test1_18_2() throws IOException {
        NamedTag tag = deserializer.fromStream(DATA_1_18_2);
        CompoundTag compoundTag = (CompoundTag) tag.getTag();
        PlayerData playerData = PlayerDataParser.parse(compoundTag);
        System.out.println(playerData);
    }

    @Test
    public void test1_20_1() throws IOException {
        NamedTag tag = deserializer.fromStream(DATA_1_20_1);
        CompoundTag compoundTag = (CompoundTag) tag.getTag();
        PlayerData playerData = PlayerDataParser.parse(compoundTag);
        System.out.println(playerData);
    }

    @Test
    public void test1_20_1_modded() throws IOException {
        NamedTag tag = deserializer.fromStream(DATA_1_20_1_modded);
        CompoundTag compoundTag = (CompoundTag) tag.getTag();
        PlayerData playerData = PlayerDataParser.parse(compoundTag);
        System.out.println(playerData);
    }

    @Test
    public void test1_21() throws IOException {
        NamedTag tag = deserializer.fromStream(DATA_1_21);
        CompoundTag compoundTag = (CompoundTag) tag.getTag();
        PlayerData playerData = PlayerDataParser.parse(compoundTag);
        System.out.println(playerData);
    }

    @Test
    public void test1_21_11() throws IOException {
        NamedTag tag = deserializer.fromStream(DATA_1_21_11);
        CompoundTag compoundTag = (CompoundTag) tag.getTag();
        PlayerData playerData = PlayerDataParser.parse(compoundTag);
        System.out.println(playerData);
    }

    @Test
    public void test1_21_IA() throws IOException {
        NamedTag tag = deserializer.fromStream(DATA_1_21_IA);
        CompoundTag compoundTag = (CompoundTag) tag.getTag();
        PlayerData playerData = PlayerDataParser.parse(compoundTag);
        System.out.println(playerData);
    }

    @Test
    public void test26_1() throws IOException {
        NamedTag tag = deserializer.fromStream(DATA_26_1);
        CompoundTag compoundTag = (CompoundTag) tag.getTag();
        PlayerData playerData = PlayerDataParser.parse(compoundTag);
        System.out.println(playerData);
    }
}