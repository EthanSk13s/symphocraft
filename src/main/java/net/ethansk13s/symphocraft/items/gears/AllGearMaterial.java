package net.ethansk13s.symphocraft.items.gears;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public enum AllGearMaterial implements ArmorMaterial {
    AMULET("dark_gungnir_amulet", 7, 0, new int[] {0, 0, 6, 0}, 0, 0),
    DARK_GUNGNIR("dark_gungnir_gear", 15, 0, new int[] {10, 15, 18, 12}, 5, 5);

    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
    private final String name;
    private final int maxDamage;
    private final int enchantability;
    private final int[] damageReductionArray;
    private final float toughness;
    private final float knockbackResistance;

    private AllGearMaterial(String name, int maxDamage, int enchantability, int[] damageReductionArray, float toughness, float knockbackResistance) {
        this.name = name;
        this.maxDamage = maxDamage;
        this.enchantability = enchantability;
        this.damageReductionArray = damageReductionArray;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * this.maxDamage;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return null;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return this.damageReductionArray[slot.getEntitySlotId()];
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }
    
}
