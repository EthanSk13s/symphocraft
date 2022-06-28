package net.ethansk13s.symphocraft.core.symphogears;

import java.util.ArrayList;

import net.ethansk13s.symphocraft.core.rhythm.BaseCombo;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public abstract class BaseSymphogear {
    public ArmorItem[] symphogearParts;
    private Item armedGear;
    private ArrayList<BaseCombo> combos;

    public BaseSymphogear(ArmorItem[] symphogearParts, Item armedGear) {
        this.symphogearParts = symphogearParts;
        this.armedGear = armedGear;
    }

    public ArmorItem[] getSymphogearParts() {
        return this.symphogearParts;
    }

    public Item getArmedGear() {
        return this.armedGear;
    }

    public ArrayList<BaseCombo> getCombos() {
        return this.combos;
    }

    public void setCombos(ArrayList<BaseCombo> combos) {
        this.combos = combos;
    }
}
