package net.ethansk13s.symphocraft.items.gears;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class DarkGungnirArmedGear extends Item implements IAnimatable, ISyncable {
    public static final String CONTROLLER_NAME = "controller";
    public AnimationFactory factory = new AnimationFactory(this);
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public DarkGungnirArmedGear(Settings settings) {
        super(settings);

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(
            ATTACK_DAMAGE_MODIFIER_ID,
            "Tool Modifier",
            12.0,
            EntityAttributeModifier.Operation.ADDITION));

        this.attributeModifiers = builder.build();
        GeckoLibNetwork.registerSyncable(this);
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		return PlayState.CONTINUE;
	}

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (user.isOnGround()) {
            user.setJumping(true);
            user.addVelocity(0, 1.2, 0);
    
            return TypedActionResult.success(itemStack);
        } else {
            float f = user.getYaw();
            float g = user.getPitch();
            float h = -MathHelper.sin(f * ((float)Math.PI / 180)) * MathHelper.cos(g * ((float)Math.PI / 180));
            float k = -MathHelper.sin(g * ((float)Math.PI / 180));
            float l = MathHelper.cos(f * ((float)Math.PI / 180)) * MathHelper.cos(g * ((float)Math.PI / 180));
            float m = MathHelper.sqrt(h * h + k * k + l * l);
            float n = 3.0f * ((1.0f + (float) 1.0f) / 4.0f);

            user.addVelocity(h *= m, -1.2, l *= n / m);

            return TypedActionResult.success(itemStack);
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.attributeModifiers;
        }

        return super.getAttributeModifiers(slot);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void registerControllers(AnimationData data) {
        AnimationController animationController = new AnimationController(this, CONTROLLER_NAME, 1, this::predicate);


        data.addAnimationController(animationController);
        
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void onAnimationSync(int id, int state) {
        final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, CONTROLLER_NAME);
        controller.setAnimation(new AnimationBuilder().addAnimation("animation.dark_gungnir_armed_gear.idle", false));        
    }
    
}
