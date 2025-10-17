package com.theswcy_allay.mixin;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(AllayEntity.class)
public abstract class AllayDoorGoalMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void swcy_AllayDoorInteraction(CallbackInfo ci) {
        AllayEntity allay = (AllayEntity) (Object) this;
        if (allay.getWorld().isClient()) return;


        ServerWorld world = (ServerWorld) allay.getWorld();
        BlockPos allayPos = allay.getBlockPos();


        BlockPos.stream(allayPos.add(-1, 0, -1), allayPos.add(1, 1, 1)).forEach(pos -> {
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();


            if (block instanceof DoorBlock) {
                boolean open = state.get(DoorBlock.OPEN);
                world.setBlockState(pos, state.with(DoorBlock.OPEN, !open));
            } else if (block instanceof TrapdoorBlock) {
                boolean open = state.get(TrapdoorBlock.OPEN);
                world.setBlockState(pos, state.with(TrapdoorBlock.OPEN, !open));
            }
        });
    }
}