package com.nightwielder.bicshieldcompat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public final class ShieldDisableHelper
{
    private static final int DISABLE_TICKS = 100;
    private static final ResourceLocation BLOCK_BREAK_ID =
            new ResourceLocation(BicShieldCompat.BIC_MODID, "block_break");

    private static MobEffect blockBreak;
    private static boolean blockBreakResolved;

    private ShieldDisableHelper()
    {
    }

    // BIC mobs fake a shield by self-applying Resistance IV on hurt, so strip it and trip their own block_break gate.
    public static void onHurt(LivingEntity mob, DamageSource source)
    {
        if (mob.level().isClientSide)
        {
            return;
        }
        Entity attacker = source.getEntity();
        if (!(attacker instanceof LivingEntity living))
        {
            return;
        }
        if (source.getDirectEntity() != attacker)
        {
            return;
        }
        if (!living.getMainHandItem().canDisableShield(ItemStack.EMPTY, mob, living))
        {
            return;
        }

        mob.removeEffect(MobEffects.DAMAGE_RESISTANCE);
        MobEffect effect = blockBreak();
        if (effect != null)
        {
            mob.addEffect(new MobEffectInstance(effect, DISABLE_TICKS, 0, false, false));
        }
    }

    private static MobEffect blockBreak()
    {
        if (!blockBreakResolved)
        {
            blockBreak = ForgeRegistries.MOB_EFFECTS.getValue(BLOCK_BREAK_ID);
            blockBreakResolved = true;
        }
        return blockBreak;
    }
}
