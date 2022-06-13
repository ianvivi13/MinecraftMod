package com.bic.bit_o_everything.client.render.entity;

import com.bic.bit_o_everything.BitOEverything;
import com.bic.bit_o_everything.entity.projectile.TimedArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModTimedArrowEntityRenderer extends ArrowRenderer<TimedArrow> {
    public ModTimedArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(TimedArrow pEntity) {
        return new ResourceLocation(BitOEverything.MOD_ID, "textures/entity/projectiles/timed_arrow.png");
    }

}
