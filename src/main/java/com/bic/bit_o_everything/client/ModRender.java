package com.bic.bit_o_everything.client;


import com.bic.bit_o_everything.block.entity.ModBlockEntities;
import com.bic.bit_o_everything.client.render.block.PotterBlockEntityRenderer;
import com.bic.bit_o_everything.client.render.entity.*;
import com.bic.bit_o_everything.entity.ModEntityTypes;
import com.bic.bit_o_everything.entity.custom.ModBoatEntity;
import com.bic.bit_o_everything.entity.custom.ModChestBoatEntity;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = "bit_o_everything", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRender {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.MOD_CHEST_BOAT.get(), ModChestBoatEntityRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.MOD_BOAT.get(), ModBoatEntityRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.EXPLOSIVE_ARROW.get(), ModExplosiveArrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.TIMED_ARROW.get(), ModTimedArrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SILVER_ARROW.get(), ModSilverArrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.STICKY_GRENADE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.GRENADE.get(), ThrownItemRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntities.POTTER.get(), PotterBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

        for(ModChestBoatEntity.Type boatType : ModChestBoatEntity.Type.values()) {
            event.registerLayerDefinition(ModChestBoatEntityRenderer.boatLayer(boatType),
                    () -> BoatModel.createBodyModel(true));
        }

        for(ModBoatEntity.Type boatType : ModBoatEntity.Type.values()) {
            event.registerLayerDefinition(ModBoatEntityRenderer.boatLayer(boatType),
                    () -> BoatModel.createBodyModel(false));
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
