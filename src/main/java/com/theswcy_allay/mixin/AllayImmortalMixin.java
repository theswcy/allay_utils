package com.theswcy_allay.mixin;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(AllayEntity.class)
public abstract class AllayImmortalMixin extends PathAwareEntity {
    public AllayImmortalMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }


    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void swcy_MakeAllayImmortal(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
        cir.cancel();
    }
}