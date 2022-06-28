package net.ethansk13s.symphocraft.core.symphogears;

import net.ethansk13s.symphocraft.RegisterItems;
import net.ethansk13s.symphocraft.core.rhythm.BaseCombo;
import net.ethansk13s.symphocraft.core.symphogears.combos.dark_gungnir.SpearThrust;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class DarkGungnir extends BaseSymphogear {
    public static ArmorItem[] symphogearParts = {RegisterItems.DARK_GUNGNIR_HEELS, RegisterItems.DARK_GUNGNIR_LEGGINGS, RegisterItems.DARK_GUNGNIR_CHEST, RegisterItems.DARK_GUNGNIR_VISOR};
    public static Item armedGear = RegisterItems.DARK_GUNGNIR_ARMED_GEAR;
    public static ArrayList<BaseCombo> combos = new ArrayList<>();

    public DarkGungnir() {
        super(symphogearParts, armedGear);

        combos.add(new SpearThrust());
        super.setCombos(combos);
    }
}
