package com.bic.bit_o_everything.item.custom;

import net.minecraft.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.net.URI;
import java.net.URISyntaxException;


public class ModBookItem extends Item {
    public ModBookItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide()) {
            try {
                openWebLink(new URI("https://htmlpreview.github.io/?https://github.com/ianvivi13/MinecraftMod/blob/master/webpage/index.html"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    private static void openWebLink(URI url)
    {
        Util.getPlatform().openUri(url);
    }

}
