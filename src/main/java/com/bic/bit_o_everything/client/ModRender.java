package com.bic.bit_o_everything.client;


import com.bic.bit_o_everything.client.render.entity.ModBoatEntityRenderer;
import com.bic.bit_o_everything.entity.ModEntityTypes;
import com.bic.bit_o_everything.entity.custom.ModBoatEntity;
import net.minecraft.client.Camera;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = "bit_o_everything", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRender {
    private static void render(Supplier<? extends Block> block, RenderType render) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), render);
    }
    /*
    public static void registerBlockRenderers() {
        RenderType cutout = RenderType.cutout();
        RenderType mipped = RenderType.cutoutMipped();
        RenderType translucent = RenderType.translucent();

        render(UGBlocks.DEEPTURF_BLOCK, mipped);
        render(UGBlocks.DEEPTURF, cutout);
        render(UGBlocks.SHIMMERWEED, cutout);
        render(UGBlocks.SMOGSTEM_SAPLING, cutout);
        render(UGBlocks.WIGGLEWOOD_SAPLING, cutout);
        render(UGBlocks.INDIGO_MUSHROOM, cutout);
        render(UGBlocks.VEIL_MUSHROOM, cutout);
        render(UGBlocks.INK_MUSHROOM, cutout);
        render(UGBlocks.BLOOD_MUSHROOM, cutout);
        render(UGBlocks.UNDERBEAN_BUSH, cutout);
        render(UGBlocks.DITCHBULB_PLANT, cutout);
        render(UGBlocks.TALL_DEEPTURF, cutout);
        render(UGBlocks.TALL_SHIMMERWEED, cutout);
        render(UGBlocks.CLOGGRUM_BARS, cutout);
        render(UGBlocks.GLITTERKELP, cutout);
        render(UGBlocks.GLITTERKELP_PLANT, cutout);
        render(UGBlocks.GOO, translucent);
        render(UGBlocks.SMOGSTEM_DOOR, cutout);
        render(UGBlocks.WIGGLEWOOD_DOOR, cutout);
        render(UGBlocks.SMOGSTEM_TRAPDOOR, cutout);
        render(UGBlocks.WIGGLEWOOD_TRAPDOOR, cutout);
        render(UGBlocks.ASHEN_DEEPTURF, cutout);
        render(UGBlocks.BLISTERBERRY_BUSH, cutout);
        render(UGBlocks.GLOOMGOURD_STEM, cutout);
        render(UGBlocks.GLOOMGOURD_STEM_ATTACHED, cutout);
        render(UGBlocks.SHARD_TORCH, cutout);
        render(UGBlocks.SHARD_WALL_TORCH, cutout);
        render(UGBlocks.DROOPVINE, cutout);
        render(UGBlocks.DROOPVINE_PLANT, cutout);
        render(UGBlocks.GRONGLE_SAPLING, cutout);
        render(UGBlocks.GRONGLE_DOOR, cutout);
        render(UGBlocks.GRONGLE_TRAPDOOR, cutout);
        render(UGBlocks.SEEPING_INK, cutout);
        render(UGBlocks.MUSHROOM_VEIL_PLANT, cutout);
        render(UGBlocks.MUSHROOM_VEIL, cutout);
        render(UGBlocks.POTTED_SHIMMERWEED, cutout);
        render(UGBlocks.POTTED_SMOGSTEM_SAPLING, cutout);
        render(UGBlocks.POTTED_WIGGLEWOOD_SAPLING, cutout);
        render(UGBlocks.POTTED_INDIGO_MUSHROOM, cutout);
        render(UGBlocks.POTTED_VEIL_MUSHROOM, cutout);
        render(UGBlocks.POTTED_INDIGO_MUSHROOM, cutout);
        render(UGBlocks.POTTED_INK_MUSHROOM, cutout);
        render(UGBlocks.POTTED_BLOOD_MUSHROOM, cutout);
        render(UGBlocks.POTTED_GRONGLE_SAPLING, cutout);
        render(UGBlocks.FROZEN_DEEPTURF, cutout);
        render(UGBlocks.SEDIMENT_GLASS, translucent);
        render(UGBlocks.SEDIMENT_GLASS_PANE, translucent);
        render(UGBlocks.HANGING_GRONGLE_LEAVES, cutout);
        render(UGBlocks.GOO_BLOCK, translucent);
        render(UGBlocks.CLOGGRUM_LANTERN, cutout);
    }
    */
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.MOD_BOAT.get(), ModBoatEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for(ModBoatEntity.Type boatType : ModBoatEntity.Type.values()) {
            event.registerLayerDefinition(ModBoatEntityRenderer.boatLayer(boatType), //BoatModel::createBodyModel,false);
            () -> BoatModel.createBodyModel(false));
            // false for no chest
        }
    }
    /*
    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        BlockColors colors = event.getBlockColors();

        colors.register((state, tintGetter, pos, tint) ->
                        tintGetter != null && pos != null ? BiomeColors.getAverageGrassColor(tintGetter, pos) : new Color(91, 117, 91).getRGB(),

        );

        colors.register((state, world, pos, tint) ->
                        new Color(54, 45, 66).getRGB(),
                UGBlocks.GLOOMGOURD_STEM.get(),
                UGBlocks.GLOOMGOURD_STEM_ATTACHED.get()
        );
    }


    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        BlockColors bColors = event.getBlockColors();
        ItemColors iColors = event.getItemColors();

        iColors.register((stack, tint) -> bColors.getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, 0),
                UGBlocks.DEEPTURF_BLOCK.get(),
                UGBlocks.DEEPTURF.get(),
                UGBlocks.SHIMMERWEED.get(),
                UGBlocks.TALL_SHIMMERWEED.get(),
                UGBlocks.TALL_DEEPTURF.get()
        );

        iColors.register((stack, tint) -> {
                    if(tint == 0) {
                        return new Color(91, 117, 91).getRGB();
                    }
                    return -1;
                },

                UGBlocks.SHIMMERWEED.get(),
                UGBlocks.TALL_SHIMMERWEED.get()
        );
    }

    @Mod.EventBusSubscriber(modid = "undergarden", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeBusEvents {
        @SubscribeEvent
        public static void renderVirulentFogColor(EntityViewRenderEvent.FogColors event) {
            Camera camera = event.getCamera();
            FluidState fluidState = camera.getBlockAtCamera().getFluidState();


            if(fluidState.getType() == UGFluids.VIRULENT_MIX_FLOWING.get() || fluidState.getType() == UGFluids.VIRULENT_MIX_SOURCE.get()) {
                event.setRed(57 / 255F);
                event.setGreen(25 / 255F);
                event.setBlue(80 / 255F);
            }

        }

        @SubscribeEvent
        public static void renderVirulentFogDensity(EntityViewRenderEvent.RenderFogEvent event) {
            Camera camera = event.getCamera();
            FluidState fluidState = camera.getBlockAtCamera().getFluidState();


            if(fluidState.getType() == UGFluids.VIRULENT_MIX_FLOWING.get() || fluidState.getType() == UGFluids.VIRULENT_MIX_SOURCE.get()) {
                event.setFarPlaneDistance(15.0F);
                event.setCanceled(true);
            }

        }

    }
    */



}
