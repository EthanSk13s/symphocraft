package net.ethansk13s.symphocraft.items.renderer;

import net.ethansk13s.symphocraft.items.gears.DarkGungnirArmedGear;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DarkGungnirArmedGearModel extends AnimatedGeoModel<DarkGungnirArmedGear> {

    @Override
    public Identifier getAnimationFileLocation(DarkGungnirArmedGear animatable) {
        return new Identifier("symphocraft", "animations/dark_gungnir_armed_gear_idle.json");
    }

    @Override
    public Identifier getModelLocation(DarkGungnirArmedGear object) {
        return new Identifier("symphocraft", "geo/dark_gungnir_armed_gear.geo.json");
    }

    @Override
    public Identifier getTextureLocation(DarkGungnirArmedGear object) {
        return new Identifier("symphocraft", "textures/item/dark_gungnir_armed_gear.png");
    }
    
}
