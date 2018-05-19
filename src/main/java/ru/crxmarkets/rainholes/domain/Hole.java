package ru.crxmarkets.rainholes.domain;

import java.util.Arrays;

/**
 * Describes Hole Profile.
 *
 * Hole is constructed by providing its heights array of integers.
 * Calculates water remaining after rain with calculateWaterVolume.
 *
 * @author Bobur Umurzokov
 */
public class Hole {
    /**
     * Represents heights of the surface profile
     */
    private int[] heights;

    public Hole(int[] heights) {
        this.heights = heights;
    }

    public Hole(String profile) {
        this(parseString(profile));
    }

    private static int[] parseString(String profile) {
        if (profile == null || profile.trim().isEmpty())
            return new int[0];

        try {
            return Arrays.stream(profile.split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid surface format. Valid formats are : 1 2 4 or 1,2,4", e);
        }
    }

    public int[] getHeights() {
        return heights;
    }
}
