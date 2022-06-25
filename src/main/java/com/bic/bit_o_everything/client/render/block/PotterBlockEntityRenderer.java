package com.bic.bit_o_everything.client.render.block;

import com.bic.bit_o_everything.block.entity.custom.PotterBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.EmptyModelData;

public class PotterBlockEntityRenderer implements BlockEntityRenderer<PotterBlockEntity> {
    private final BlockEntityRendererProvider.Context context;


    public PotterBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(PotterBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        final BlockRenderDispatcher dispatcher = this.context.getBlockRenderDispatcher();
        BlockPos pos = pBlockEntity.getBlockPos();
        Level pBELevel = pBlockEntity.getLevel();
        RandomSource pBERandom = pBELevel.getRandom();

        int height = 12;
        if (pBlockEntity.connections[4]) {
            height = 6;
        }

        BlockState mat = pBlockEntity.getMaterial().defaultBlockState();
        for(RenderType t : RenderType.chunkBufferLayers()) {
            if (ItemBlockRenderTypes.canRenderInLayer(mat,t)) {
                VertexConsumer VC = pBufferSource.getBuffer(t);
                if (pBlockEntity.connections[1]) {
                    renderNorthFace(dispatcher, pPoseStack, mat, pos, pBELevel, pBERandom, VC, height);
                }
                if (pBlockEntity.connections[5]) {
                    renderEastFace(dispatcher, pPoseStack, mat, pos, pBELevel, pBERandom, VC, height);
                }
                if (pBlockEntity.connections[7]) {
                    renderSouthFace(dispatcher, pPoseStack, mat, pos, pBELevel, pBERandom, VC, height);
                }
                if (pBlockEntity.connections[3]) {
                    renderWestFace(dispatcher, pPoseStack, mat, pos, pBELevel, pBERandom, VC, height);
                }
                if (pBlockEntity.connections[2]) {
                    renderNorthEastCorner(dispatcher, pPoseStack, mat, pos, pBELevel, pBERandom, VC, height);
                }
                if (pBlockEntity.connections[8]) {
                    renderSouthEastCorner(dispatcher, pPoseStack, mat, pos, pBELevel, pBERandom, VC, height);
                }
                if (pBlockEntity.connections[0]) {
                    renderNorthWestCorner(dispatcher, pPoseStack, mat, pos, pBELevel, pBERandom, VC, height);
                }
                if (pBlockEntity.connections[6]) {
                    renderSouthWestCorner(dispatcher, pPoseStack, mat, pos, pBELevel, pBERandom, VC, height);
                }
                renderBottom(dispatcher, pPoseStack, mat, pos, pBELevel, pBERandom, VC);
                break;
            }
        }

        Block dirt = pBlockEntity.getDirt();
        if (dirt != null) {
            BlockState dirtState = dirt.defaultBlockState();
            for(RenderType t : RenderType.chunkBufferLayers()) {
                if (ItemBlockRenderTypes.canRenderInLayer(dirtState,t)) {
                    VertexConsumer VC = pBufferSource.getBuffer(t);
                    renderDirt(dispatcher, pPoseStack, dirtState, pos, pBELevel, pBERandom, VC, height);
                    if (!pBlockEntity.connections[1]) {
                        renderNorthFace(dispatcher, pPoseStack, dirtState, pos, pBELevel, pBERandom, VC, height - 2);
                    }
                    if (!pBlockEntity.connections[5]) {
                        renderEastFace(dispatcher, pPoseStack, dirtState, pos, pBELevel, pBERandom, VC, height - 2);
                    }
                    if (!pBlockEntity.connections[7]) {
                        renderSouthFace(dispatcher, pPoseStack, dirtState, pos, pBELevel, pBERandom, VC, height - 2);
                    }
                    if (!pBlockEntity.connections[3]) {
                        renderWestFace(dispatcher, pPoseStack, dirtState, pos, pBELevel, pBERandom, VC, height - 2);
                    }
                    if (!pBlockEntity.connections[2]) {
                        renderNorthEastCorner(dispatcher, pPoseStack, dirtState, pos, pBELevel, pBERandom, VC, height - 2);
                    }
                    if (!pBlockEntity.connections[8]) {
                        renderSouthEastCorner(dispatcher, pPoseStack, dirtState, pos, pBELevel, pBERandom, VC, height - 2);
                    }
                    if (!pBlockEntity.connections[0]) {
                        renderNorthWestCorner(dispatcher, pPoseStack, dirtState, pos, pBELevel, pBERandom, VC, height - 2);
                    }
                    if (!pBlockEntity.connections[6]) {
                        renderSouthWestCorner(dispatcher, pPoseStack, dirtState, pos, pBELevel, pBERandom, VC, height - 2);
                    }
                    break;
                }
            }
        }

        Block flower = pBlockEntity.getFlower();
        if (flower != null) {
            BlockState flowerState = flower.defaultBlockState();
            for(RenderType t : RenderType.chunkBufferLayers()) {
                if (ItemBlockRenderTypes.canRenderInLayer(flowerState, t)) {
                    renderFlower(dispatcher, pPoseStack, flowerState, pos, pBELevel, pBERandom, pBufferSource.getBuffer(t), height);
                }
            }
        }
    }


    // render dirt
    private void renderDirt(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, BlockState b, BlockPos p, Level level, RandomSource RS, VertexConsumer Vc, int h) {
        pPoseStack.pushPose();
        pPoseStack.scale(6399/6400f, 1 / 6400f, 6399/6400f);
        pPoseStack.translate(1/12798d, (400*(h-2)) - 1 / 6400d, 1/12798d);
        dispatcher.renderBatched(b, p, level, pPoseStack, Vc, false, RS, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    // render flower
    private void renderFlower(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, BlockState b, BlockPos p, Level level, RandomSource RS, VertexConsumer Vc, int h) {
        pPoseStack.pushPose();
        pPoseStack.scale(14 / 16f, 14 / 16f, 14 / 16f);
        pPoseStack.translate(1 / 14d, (h-2) / 14d, 1 / 14d);
        dispatcher.renderBatched(b, p, level, pPoseStack, Vc, false, RS, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    //region material renders
    // render bottom
    private void renderBottom(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, BlockState b, BlockPos p, Level level, RandomSource RS, VertexConsumer Vc) {
        pPoseStack.pushPose();
        pPoseStack.scale(14/16f,1/16f,14/16f);
        pPoseStack.translate(1/14d,0,1/14d);
        dispatcher.renderBatched(b, p, level, pPoseStack, Vc, false, RS, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    // render faces
    private void renderNorthFace(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, BlockState b, BlockPos p, Level level, RandomSource RS, VertexConsumer Vc, int h) {
        pPoseStack.pushPose();
        pPoseStack.scale(14/16f,h/16f,1/16f);
        pPoseStack.translate(1/14d,0,0);
        dispatcher.renderBatched(b, p, level, pPoseStack, Vc, false, RS, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderWestFace(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, BlockState b, BlockPos p, Level level, RandomSource RS, VertexConsumer Vc, int h) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,h/16f,14/16f);
        pPoseStack.translate(0,0,1/14d);
        dispatcher.renderBatched(b, p, level, pPoseStack, Vc, false, RS, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderSouthFace(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, BlockState b, BlockPos p, Level level, RandomSource RS, VertexConsumer Vc, int h) {
        pPoseStack.pushPose();
        pPoseStack.scale(14/16f,h/16f,1/16f);
        pPoseStack.translate(1/14d,0,15);
        dispatcher.renderBatched(b, p, level, pPoseStack, Vc, false, RS, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderEastFace(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, BlockState b, BlockPos p, Level level, RandomSource RS, VertexConsumer Vc, int h) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,h/16f,14/16f);
        pPoseStack.translate(15,0,1/14d);

        dispatcher.renderBatched(b, p, level, pPoseStack, Vc, false, RS, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    // render corners
    private void renderNorthWestCorner(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, BlockState b, BlockPos p, Level level, RandomSource RS, VertexConsumer Vc, int h) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,h/16f,1/16f);
        pPoseStack.translate(0,0,0);
        dispatcher.renderBatched(b, p, level, pPoseStack, Vc, false, RS, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderSouthWestCorner(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, BlockState b, BlockPos p, Level level, RandomSource RS, VertexConsumer Vc, int h) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,h/16f,1/16f);
        pPoseStack.translate(0,0,15);
        dispatcher.renderBatched(b, p, level, pPoseStack, Vc, false, RS, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderNorthEastCorner(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, BlockState b, BlockPos p, Level level, RandomSource RS, VertexConsumer Vc, int h) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,h/16f,1/16f);
        pPoseStack.translate(15,0,0);
        dispatcher.renderBatched(b, p, level, pPoseStack, Vc, false, RS, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderSouthEastCorner(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, BlockState b, BlockPos p, Level level, RandomSource RS, VertexConsumer Vc, int h) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,h/16f,1/16f);
        pPoseStack.translate(15,0,15);
        dispatcher.renderBatched(b, p, level, pPoseStack, Vc, false, RS, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }
    //endregion
}
