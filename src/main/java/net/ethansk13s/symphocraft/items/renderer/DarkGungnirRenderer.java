package net.ethansk13s.symphocraft.items.renderer;

import net.ethansk13s.symphocraft.items.gears.DarkGungnirGear;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class DarkGungnirRenderer extends GeoArmorRenderer<DarkGungnirGear> {

    public DarkGungnirRenderer() {
        super(new DarkGungnirModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.leftArmBone = "armorLeftArm";
        this.rightArmBone = "armorRightArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
    
}
