package com.bic.bit_o_everything;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class ModUtils {

    /**
     * Class pertaining to some advanced functions on the player's view
     */
    public static class advancedPlayerView {

        /**
         * Raycasts the player's view to determine if they are looking at a block
         * @param range the range (in blocks) of how far the player should be able to see a block
         * @return BlockHitResult based on if the player is looking at a block at a far distance
         */
        public static BlockHitResult getBlockLookingAt(Level level, Player player, double range) {
            Vec3 view = player.getViewVector(1F);
            Vec3 eyePos = player.getEyePosition(1F);
            ClipContext cc = new ClipContext(eyePos, view.scale(range).add(eyePos),ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, null);
            return level.clip(cc);
        }
    }

    /**
     * Class pertaining to some advanced random functions
     */
    public static class advancedRandom {

        public static boolean randomTrue(double d) {
            return Math.random() < d;
        }

        public static int randomRange(int min, int max) {
            return (int) ((Math.random() * (max - min)) + min);
        }
    }



}
