package com.mygdx.game.math;

import java.util.Random;

public class Rnd {
    private static final Random random = new Random();

    /**
     * random
     * @param min value
     * @param max value
     * @return result
     */
    public static float nextFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }
}
