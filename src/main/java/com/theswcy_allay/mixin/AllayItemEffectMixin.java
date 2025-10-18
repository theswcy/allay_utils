package com.theswcy_allay.mixin;


import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import java.util.HashMap;
import java.util.Map;


@Mixin(AllayEntity.class)
public abstract class AllayItemEffectMixin {


    @Unique
    private static final Map<AllayEntity, Integer> particleCooldowns = new HashMap<>();


    @Inject(method = "tick", at = @At("HEAD"))
    private void swcy_AddItemParticles(CallbackInfo ci) {
        AllayEntity allay = (AllayEntity) (Object) this;


        if (!allay.getWorld().isClient()) return;


        particleCooldowns.put(allay, particleCooldowns.getOrDefault(allay, 0) - 1);
        if (particleCooldowns.getOrDefault(allay, 0) > 0) return;


        ItemStack stack = allay.getMainHandStack();
        if (stack.isEmpty()) return;


        if (stack.isOf(Items.GOLDEN_APPLE) || stack.isOf(Items.ENCHANTED_GOLDEN_APPLE)) {
            allay.getWorld().addParticle(
                    ParticleTypes.CHERRY_LEAVES,
                    allay.getX(),
                    allay.getY(),
                    allay.getZ(),
                    0.0, 0.3, 0.0
            );
        } else if (stack.isOf(Items.ENDER_PEARL)) {
            allay.getWorld().addParticle(
                    ParticleTypes.END_ROD,
                    allay.getX(),
                    allay.getY() - 3,
                    allay.getZ(),
                    0.0, 0.3, 0.0
            );
        }else if (stack.getItem() instanceof SwordItem) {
            int amount = 0;
            if (stack.isOf(Items.WOODEN_SWORD)) amount = 1;
            if (stack.isOf(Items.STONE_SWORD)) amount = 1;
            if (stack.isOf(Items.IRON_SWORD)) amount = 2;
            if (stack.isOf(Items.DIAMOND_SWORD)) amount = 4;
            if (stack.isOf(Items.NETHERITE_SWORD)) amount = 6;
            for (int i = 0; i < amount; i++) {
                allay.getWorld().addParticle(
                        ParticleTypes.FLAME,
                        allay.getX() + Math.random() - 0.5,
                        allay.getY() - 0.3,
                        allay.getZ() + Math.random() - 0.5,
                        0.0, 0.05, 0.0
                );
            }
        } else {
            allay.getWorld().addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    allay.getX(),
                    allay.getY() + 1.0,
                    allay.getZ(),
                    0.0, 0.3, 0.0
            );
            allay.getWorld().playSound(
                    null,
                    allay.getBlockPos(),
                    SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
                    SoundCategory.PLAYERS,
                    1.0F, 1.0F
            );
        }


        particleCooldowns.put(allay, 5);
    }
}