package net.ethansk13s.symphocraft.items.renderer;

import net.ethansk13s.symphocraft.items.gears.DarkGungnirGear;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DarkGungnirModel extends AnimatedGeoModel<DarkGungnirGear> {

    @Override
    public Identifier getAnimationFileLocation(DarkGungnirGear animatable) {
        return new Identifier("symphocraft", "animations/dark_gungnir_idle.json");
    }

    @Override
    public Identifier getModelLocation(DarkGungnirGear object) {
        return new Identifier("symphocraft", "geo/dark_gungnir.geo.json");
    }

    @Override
    public Identifier getTextureLocation(DarkGungnirGear object) {
        return new Identifier("symphocraft", "textures/models/armor/dark_gungnir.png");
    }
    
}
