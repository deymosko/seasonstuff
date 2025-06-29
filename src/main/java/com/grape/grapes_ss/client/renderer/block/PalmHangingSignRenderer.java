package com.grape.grapes_ss.client.renderer.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import com.grape.grapes_ss.core.block.entity.PalmSignBlockEntity;

import java.util.Map;

@SuppressWarnings("all")
public class PalmHangingSignRenderer extends PalmSignRenderer {
    private static final String PLANK = "plank";
    private static final String V_CHAINS = "vChains";
    private static final String NORMAL_CHAINS = "normalChains";
    private static final String CHAIN_L_1 = "chainL1";
    private static final String CHAIN_L_2 = "chainL2";
    private static final String CHAIN_R_1 = "chainR1";
    private static final String CHAIN_R_2 = "chainR2";
    private static final String BOARD = "board";
    private static final float MODEL_RENDER_SCALE = 1.0F;
    private static final float TEXT_RENDER_SCALE = 0.9F;
    private static final Vec3 TEXT_OFFSET = new Vec3(0.0, -0.3199999928474426, 0.0729999989271164);
    private final Map<WoodType, HangingSignModel> hangingSignModels;

    public PalmHangingSignRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        this.hangingSignModels = (Map)WoodType.values().collect(ImmutableMap.toImmutableMap((woodType) -> {
            return woodType;
        }, (woodType) -> {
            return new HangingSignModel(context.bakeLayer(ModelLayers.createHangingSignModelName(woodType)));
        }));
    }

    public float getSignModelRenderScale() {
        return 1.0F;
    }

    public float getSignTextRenderScale() {
        return 0.9F;
    }

    public void render(PalmSignBlockEntity signBlockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        BlockState blockState = signBlockEntity.getBlockState();
        SignBlock signBlock = (SignBlock)blockState.getBlock();
        WoodType woodType = SignBlock.getWoodType(signBlock);
        HangingSignModel hangingSignModel = (HangingSignModel)this.hangingSignModels.get(woodType);
        hangingSignModel.evaluateVisibleParts(blockState);
        this.renderSignWithText(signBlockEntity, poseStack, multiBufferSource, i, j, blockState, signBlock, woodType, hangingSignModel);
    }

    void translateSign(PoseStack poseStack, float f, BlockState blockState) {
        poseStack.translate(0.5, 0.9375, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        poseStack.translate(0.0F, -0.3125F, 0.0F);
    }

    void renderSignModel(PoseStack poseStack, int i, int j, Model model, VertexConsumer vertexConsumer) {
        HangingSignModel hangingSignModel = (HangingSignModel)model;
        hangingSignModel.root.render(poseStack, vertexConsumer, i, j);
    }

    Material getSignMaterial(WoodType woodType) {
        return Sheets.getHangingSignMaterial(woodType);
    }

    Vec3 getTextOffset() {
        return TEXT_OFFSET;
    }

    public static LayerDefinition createHangingSignLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("board", CubeListBuilder.create().texOffs(0, 12).addBox(-7.0F, 0.0F, -1.0F, 14.0F, 10.0F, 2.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("plank", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -6.0F, -2.0F, 16.0F, 2.0F, 4.0F), PartPose.ZERO);
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("normalChains", CubeListBuilder.create(), PartPose.ZERO);
        partDefinition2.addOrReplaceChild("chainL1", CubeListBuilder.create().texOffs(0, 6).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(-5.0F, -6.0F, 0.0F, 0.0F, -0.7853982F, 0.0F));
        partDefinition2.addOrReplaceChild("chainL2", CubeListBuilder.create().texOffs(6, 6).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(-5.0F, -6.0F, 0.0F, 0.0F, 0.7853982F, 0.0F));
        partDefinition2.addOrReplaceChild("chainR1", CubeListBuilder.create().texOffs(0, 6).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(5.0F, -6.0F, 0.0F, 0.0F, -0.7853982F, 0.0F));
        partDefinition2.addOrReplaceChild("chainR2", CubeListBuilder.create().texOffs(6, 6).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(5.0F, -6.0F, 0.0F, 0.0F, 0.7853982F, 0.0F));
        partDefinition.addOrReplaceChild("vChains", CubeListBuilder.create().texOffs(14, 6).addBox(-6.0F, -6.0F, 0.0F, 12.0F, 6.0F, 0.0F), PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public static final class HangingSignModel extends Model {
        public final ModelPart root;
        public final ModelPart plank;
        public final ModelPart vChains;
        public final ModelPart normalChains;

        public HangingSignModel(ModelPart modelPart) {
            super(RenderType::entityCutoutNoCull);
            this.root = modelPart;
            this.plank = modelPart.getChild("plank");
            this.normalChains = modelPart.getChild("normalChains");
            this.vChains = modelPart.getChild("vChains");
        }

        public void evaluateVisibleParts(BlockState blockState) {
            boolean bl = !(blockState.getBlock() instanceof CeilingHangingSignBlock);
            this.plank.visible = bl;
            this.vChains.visible = false;
            this.normalChains.visible = true;
            if (!bl) {
                boolean bl2 = (Boolean)blockState.getValue(BlockStateProperties.ATTACHED);
                this.normalChains.visible = !bl2;
                this.vChains.visible = bl2;
            }

        }

        public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
            this.root.render(poseStack, vertexConsumer, i, j, f, g, h, k);
        }
    }
}

