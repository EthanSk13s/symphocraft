package net.ethansk13s.symphocraft;

import net.ethansk13s.symphocraft.items.*;
import net.ethansk13s.symphocraft.items.gears.DarkGungnirAmulet;
import net.ethansk13s.symphocraft.items.gears.DarkGungnirArmedGear;
import net.ethansk13s.symphocraft.items.gears.DarkGungnirGear;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterItems {
    public static final ItemGroup SYMPHOCRAFT_GROUP = Symphocraft.SYMPHOCRAFT_GROUP;
    public static final TagKey<Item> RELIC_AMULETS = TagKey.of(Registry.ITEM_KEY, new Identifier("symphocraft", "relic_amulets"));

	public static final RuneFragment RUNE_FRAGMENT = new RuneFragment(new FabricItemSettings().group(SYMPHOCRAFT_GROUP));
	public static final DarkGungnirFragment DARK_GUNGNIR_FRAGMENT = new DarkGungnirFragment(new FabricItemSettings().group(SYMPHOCRAFT_GROUP));
    public static final DarkGungnirAmulet DARK_GUNGNIR_AMULET = new DarkGungnirAmulet(EquipmentSlot.CHEST, new FabricItemSettings().group(SYMPHOCRAFT_GROUP));

    public static final DarkGungnirGear DARK_GUNGNIR_VISOR = new DarkGungnirGear(EquipmentSlot.HEAD, new FabricItemSettings().group(SYMPHOCRAFT_GROUP));
    public static final DarkGungnirGear DARK_GUNGNIR_CHEST = new DarkGungnirGear(EquipmentSlot.CHEST, new FabricItemSettings().group(SYMPHOCRAFT_GROUP));
    public static final DarkGungnirGear DARK_GUNGNIR_LEGGINGS = new DarkGungnirGear(EquipmentSlot.LEGS, new FabricItemSettings().group(SYMPHOCRAFT_GROUP));
    public static final DarkGungnirGear DARK_GUNGNIR_HEELS = new DarkGungnirGear(EquipmentSlot.FEET, new FabricItemSettings().group(SYMPHOCRAFT_GROUP));
    public static final DarkGungnirArmedGear DARK_GUNGNIR_ARMED_GEAR = new DarkGungnirArmedGear(new FabricItemSettings().group(SYMPHOCRAFT_GROUP));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("symphocraft", "rune_fragment"), RUNE_FRAGMENT);
		Registry.register(Registry.ITEM, new Identifier("symphocraft", "dark_gungnir_fragment"), DARK_GUNGNIR_FRAGMENT);

        Registry.register(Registry.ITEM, new Identifier("symphocraft", "dark_gungnir_amulet"), DARK_GUNGNIR_AMULET);
        Registry.register(Registry.ITEM, new Identifier("symphocraft", "dark_gungnir_visor"), DARK_GUNGNIR_VISOR);
        Registry.register(Registry.ITEM, new Identifier("symphocraft", "dark_gungnir_chest"), DARK_GUNGNIR_CHEST);
        Registry.register(Registry.ITEM, new Identifier("symphocraft", "dark_gungnir_leggings"), DARK_GUNGNIR_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("symphocraft", "dark_gungnir_heels"), DARK_GUNGNIR_HEELS);

        Registry.register(Registry.ITEM, new Identifier("symphocraft", "dark_gungnir_armed_gear"), DARK_GUNGNIR_ARMED_GEAR);
    }
}
