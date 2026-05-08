package com.springwater.playerdata.parser;
import net.querz.nbt.tag.CompoundTag;

import java.util.Optional;
import java.util.UUID;
public class UUIDParser {

    /**
     * 自动检测并解析 NBT 中的 UUID。
     * 优先尝试读取现代格式 (int[] UUID)，
     * 如果失败则尝试读取旧版格式 (long UUIDMost/Least)。
     *
     * @param nbt NBT 标签对象
     * @return 解析出的 UUID，如果未找到则返回 Empty
     */
    public static Optional<UUID> parse(CompoundTag nbt) {
        if (nbt == null) return Optional.empty();
        if (nbt.containsKey("UUID")) {
            int[] uuidInts = nbt.getIntArray("UUID");
            if (uuidInts.length == 4) {
                return parseFromIntArray(uuidInts);
            }
        }
        if (nbt.containsKey("UUIDMost") && nbt.containsKey("UUIDLeast")) {
            try {
                long most = nbt.getLong("UUIDMost");
                long least = nbt.getLong("UUIDLeast");
                return Optional.of(new UUID(most, least));
            } catch (Exception ignored) {
            }
        }

        return Optional.empty();
    }
    public static Optional<UUID> parseFromIntArray(int[] uuid) {
        if (uuid == null || uuid.length != 4) return Optional.empty();

        long mostSigBits = ((long) uuid[0] << 32) | ((long) uuid[1] & 0xFFFFFFFFL);
        long leastSigBits = ((long) uuid[2] << 32) | ((long) uuid[3] & 0xFFFFFFFFL);

        return Optional.of(new UUID(mostSigBits, leastSigBits));
    }
}
