package com.grape.grapes_ss.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import com.grape.grapes_ss.core.block.entity.CompletionistBannerEntity;
import com.grape.grapes_ss.core.registry.EntityTypeRegistry;
import com.grape.grapes_ss.core.registry.ObjectRegistry;
import com.grape.grapes_ss.core.util.BeachpartyIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("deprecation")
public class CompletionistBannerBlock extends BaseEntityBlock {
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
    private static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public CompletionistBannerBlock(Properties properties) {
        super(properties);
        makeDefaultState();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new CompletionistBannerEntity(blockPos, blockState);
    }

    protected void makeDefaultState() {
        registerDefaultState(this.stateDefinition.any().setValue(ROTATION, 0));
    }

    @Override
    public boolean canSurvive(@NotNull BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            return levelReader.getBlockState(blockPos.below()).isSolid() ||
                    levelReader.getBlockState(blockPos.relative(blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite())).isSolid();
        } else {
            return levelReader.getBlockState(blockPos.below()).isSolid();
        }
    }

    @Override
    public boolean isPossibleToRespawnInThis(@NotNull BlockState blockState) {
        return true;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();
        if (clickedFace == Direction.UP || clickedFace == Direction.DOWN) {
            return this.defaultBlockState().setValue(ROTATION, Mth.floor((double) ((180.0f + context.getRotation()) * 16.0f / 360.0f) + 0.5) & 0xF);
        } else {
            if (this == ObjectRegistry.BEACHPARTY_BANNER.get()) {
                return ObjectRegistry.BEACHPARTY_WALL_BANNER.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, clickedFace.getOpposite());
            }
        }
        return this.defaultBlockState();
    }


    @Override
    public @NotNull BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(ROTATION, rotation.rotate(blockState.getValue(ROTATION), 16));
    }

    @Override
    public @NotNull BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.setValue(ROTATION, mirror.mirror(blockState.getValue(ROTATION), 16));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, EntityTypeRegistry.BEACHPARTY_BANNER.get(), (level1, pos, state1, entity) -> CompletionistBannerEntity.tick(level1, pos));
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState blockState, @NotNull Direction direction, @NotNull BlockState blockState2, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockPos blockPos2) {
        if (direction == Direction.DOWN && !blockState.canSurvive(levelAccessor, blockPos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    public ResourceLocation getRenderTexture() {
        return new BeachpartyIdentifier("textures/banner/beachparty_banner.png");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, net.minecraft.world.item.TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.beachparty.banner.thankyou_1").withStyle(style -> style.withColor(TextColor.fromRgb(0xD4B483))));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("tooltip.beachparty.banner.thankyou_2").withStyle(style -> style.withColor(TextColor.fromRgb(0xD4B483))));
        tooltip.add(Component.translatable("tooltip.beachparty.banner.thankyou_4").withStyle(style -> style.withColor(TextColor.fromRgb(0xD4B483))));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("tooltip.beachparty.banner.thankyou_3").withStyle(style -> style.withColor(TextColor.fromRgb(0xD4B483))));
    }
}
