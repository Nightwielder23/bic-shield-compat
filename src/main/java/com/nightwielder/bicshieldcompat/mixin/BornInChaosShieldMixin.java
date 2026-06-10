package com.nightwielder.bicshieldcompat.mixin;

import com.nightwielder.bicshieldcompat.ShieldDisableHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {
        "net.mcreator.borninchaosv.entity.SkeletonThrasherEntity",
        "net.mcreator.borninchaosv.entity.SkeletonThrasherNotDespawnEntity",
        "net.mcreator.borninchaosv.entity.DoorKnightEntity",
        "net.mcreator.borninchaosv.entity.DoorKnightNotDespawnEntity"
}, remap = false)
public class BornInChaosShieldMixin
{
    // m_6469_ is LivingEntity#hurt; the BIC jar is never deobfed here, so target the SRG name directly.
    @Inject(method = "m_6469_", at = @At("HEAD"))
    private void bicshieldcompat$onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        ShieldDisableHelper.onHurt((LivingEntity) (Object) this, source);
    }
}
