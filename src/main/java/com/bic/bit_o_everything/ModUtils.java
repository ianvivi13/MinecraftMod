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

    public static class advancedMath {
        /**
         * scales a value according to the algorithm: [<b>val</b> % in range (<b>min</b> -> <b>max</b>)]<sup><b>power</b></sup><br>
         * Refer to this <a href="https://www.desmos.com/calculator/cgixul5oxs">graph</a>
         * @param min smallest value in range
         * @param max largest value in range
         * @param val value to scale
         * @param power value to scale by: x/1 will be exponential, x/1 will be logarithmic, 1 will be linear. A negative power, will result in the return going down as <b>val</b> approaches <b>max</b>
         * @return 0 -> 1
         */
        public static double scale(float min, float max, float val, double power) {
            if (power == 0) return 0;
            if (min >= max) return 0;
            double signPower = Math.signum(power);
            return signPower * Math.pow((clamp(val, min, max)-max) / (min-max), Math.abs(power)) + Math.abs((signPower - 1)/2);
        }

        public static float clamp(float val, float min, float max) {
            return val > max ? max : min > val ? min : val;
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

    public static class advancedRotation {
        /**
         * simplifies a radian (r) to a value between 0 (inclusive) and 2&pi; (exclusive) according to this <a href="https://www.desmos.com/calculator/invrphnxlq">graph</a>
         */
        public static float simplify0To2Pi(float r) {
            return (float) (r - (2 * Math.PI * Math.floor(r/(2 * Math.PI))));
        }

        /**
         * simplifies a radian (r) to a value between -&pi; (inclusive) and &pi; (exclusive) according to this <a href="https://www.desmos.com/calculator/kancjmicjx">graph</a>
         */
        public static float simplifyNegPiToPi(float r) {
            return (float) (r - (2 * Math.PI * Math.floor(r/(2 * Math.PI) + 1/2d)));
        }

        /**
         * locks a radian (val) to increments (step) in a circle. Defined by this <a href="https://www.desmos.com/calculator/3ia9iujqcn">graph</a>
         */
        public static float step(float val, double step) {
            return (float) (Math.floor(val/step + 1/2d)*step);
        }

    }

}
