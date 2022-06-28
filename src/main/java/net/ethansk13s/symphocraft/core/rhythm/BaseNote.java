package net.ethansk13s.symphocraft.core.rhythm;

import net.minecraft.client.util.InputUtil.Key;

public interface BaseNote {
    boolean hit();
    boolean miss();
    Key getDirection();
}
