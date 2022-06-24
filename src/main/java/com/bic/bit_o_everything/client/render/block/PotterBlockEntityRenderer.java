package com.bic.bit_o_everything.client.render.block;

import com.bic.bit_o_everything.block.entity.custom.PotterBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.model.data.EmptyModelData;

import java.util.*;

public class PotterBlockEntityRenderer implements BlockEntityRenderer<PotterBlockEntity> {
    private final BlockEntityRendererProvider.Context context;


    public PotterBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(PotterBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        final BlockRenderDispatcher dispatcher = this.context.getBlockRenderDispatcher();
        //pPackedLight = getProperLight(pBlockEntity);
        Block mat = pBlockEntity.getMaterial();

        if (pBlockEntity.connections[1]) {
            renderNorthFace(dispatcher, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, mat);
        }
        if (pBlockEntity.connections[5]) {
            renderEastFace(dispatcher, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, mat);
        }
        if (pBlockEntity.connections[7]) {
            renderSouthFace(dispatcher, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, mat);
        }
        if (pBlockEntity.connections[3]) {
            renderWestFace(dispatcher, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, mat);
        }
        renderBottom(dispatcher, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, mat);

        if (pBlockEntity.connections[2]) {
            renderNorthEastCorner(dispatcher, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, mat);
        }
        if (pBlockEntity.connections[8]) {
            renderSouthEastCorner(dispatcher, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, mat);
        }
        if (pBlockEntity.connections[0]) {
            renderNorthWestCorner(dispatcher, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, mat);
        }
        if (pBlockEntity.connections[6]) {
            renderSouthWestCorner(dispatcher, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, mat);
        }
        renderDirt(dispatcher, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, pBlockEntity.getDirt());
        renderFlower(dispatcher, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, pBlockEntity.getFlower());

    }

    // render bottom
    private void renderBottom(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Block b) {
        pPoseStack.pushPose();
        pPoseStack.scale(14/16f,1/16f,14/16f);
        pPoseStack.translate(1/14d,0,1/14d);
        dispatcher.renderSingleBlock(b.defaultBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    // render dirt
    private void renderDirt(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Block b) {
        if (b != null) {
            pPoseStack.pushPose();
            //default method - thin line
            pPoseStack.scale(6399/6400f, 1 / 6400f, 6399/6400f);
            pPoseStack.translate(1/12798d, 4000 - 1 / 6400d, 1/12798d);
            //pPoseStack.scale(1279/1280f, 9/16f, 1279/1280f);
            //pPoseStack.translate(1/2558d, 1/9d, 1/2558d);
            dispatcher.renderSingleBlock(b.defaultBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
            pPoseStack.popPose();
        }
    }

    // render flower
    private void renderFlower(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Block b) {
        if (b != null) {
            pPoseStack.pushPose();
            pPoseStack.scale(14 / 16f, 14 / 16f, 14 / 16f);
            pPoseStack.translate(1 / 14d, 10 / 14d, 1 / 14d);
            dispatcher.renderSingleBlock(b.defaultBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
            pPoseStack.popPose();
        }
    }

    // render faces
    private void renderNorthFace(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Block b) {
        pPoseStack.pushPose();
        pPoseStack.scale(14/16f,12/16f,1/16f);
        pPoseStack.translate(1/14d,0,0);
        dispatcher.renderSingleBlock(b.defaultBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderWestFace(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Block b) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,12/16f,14/16f);
        pPoseStack.translate(0,0,1/14d);
        dispatcher.renderSingleBlock(b.defaultBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderSouthFace(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Block b) {
        pPoseStack.pushPose();
        pPoseStack.scale(14/16f,12/16f,1/16f);
        pPoseStack.translate(1/14d,0,15);
        dispatcher.renderSingleBlock(b.defaultBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderEastFace(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Block b) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,12/16f,14/16f);
        pPoseStack.translate(15,0,1/14d);

        dispatcher.renderSingleBlock(b.defaultBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    // render corners
    private void renderNorthWestCorner(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Block b) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,12/16f,1/16f);
        pPoseStack.translate(0,0,0);
        dispatcher.renderSingleBlock(b.defaultBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderSouthWestCorner(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Block b) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,12/16f,1/16f);
        pPoseStack.translate(0,0,15);
        dispatcher.renderSingleBlock(b.defaultBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderNorthEastCorner(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Block b) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,12/16f,1/16f);
        pPoseStack.translate(15,0,0);
        dispatcher.renderSingleBlock(b.defaultBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    private void renderSouthEastCorner(BlockRenderDispatcher dispatcher, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Block b) {
        pPoseStack.pushPose();
        pPoseStack.scale(1/16f,12/16f,1/16f);
        pPoseStack.translate(15,0,15);
        dispatcher.renderSingleBlock(b.defaultBlockState(), pPoseStack, pBufferSource, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
        pPoseStack.popPose();
    }

    // please make faster
    private int getProperLight(BlockEntity pBlockEntity) {
        Level world = pBlockEntity.getLevel();
        BlockPos me = pBlockEntity.getBlockPos();
        List<BlockPos> surroundPos = new ArrayList<>();
        surroundPos.add(me.above());
        surroundPos.add(me.below());
        surroundPos.add(me.north());
        surroundPos.add(me.east());
        surroundPos.add(me.south());
        surroundPos.add(me.west());
        List<Integer> lightLevels = new ArrayList<>();
        for (BlockPos p: surroundPos) {
            lightLevels.add(LevelRenderer.getLightColor(world, p));
        }
        lightLevels.sort(Comparator.naturalOrder());

        return lightLevels.get(lightLevels.size()-1);
    }

}
