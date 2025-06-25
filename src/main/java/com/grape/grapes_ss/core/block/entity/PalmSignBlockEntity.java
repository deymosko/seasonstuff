package com.grape.grapes_ss.core.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

public class PalmSignBlockEntity extends SignBlockEntity {

    public PalmSignBlockEntity(BlockEntityType<? extends PalmSignBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public PalmSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityType.SIGN, pPos, pBlockState);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return BlockEntityType.SIGN;
    }
}