package ru.crxmarkets.rainholes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.crxmarkets.rainholes.domain.Hole;
import ru.crxmarkets.rainholes.domain.WaterVolume;

import javax.ejb.Stateless;

import static java.lang.Math.max;

/**
 * Calculates the volume of water which remained after the rain, in units.
 *
 * Algorithm performance:
 * Time complexity: O(n). Single iteration of O(n).
 * Space complexity: O(1) extra space.
 *
 * Only constant space required for \text{left}left, \text{right}right, left_max and right_max.
 *
 * Algorithm is based on hypothesis that at each hill the potential water remained
 * is min of the tallest hill to the left and to the right from current hill.
 *
 * It performs in a single pass of the surface and scans it from left and right edges moving
 * to the middle and at each step calculates water volume for current hill.
 *
 * The extra water remainedAt array is provided only if saveRemainedAt
 * option is used.
 *
 * @author Bobur Umurzokov
 */
@Stateless
public class HoleSurfaceVolumeService {
    private boolean saveRemainedAt;
    private static final Logger LOGGER = LoggerFactory.getLogger(HoleSurfaceVolumeService.class);
    /**
     * Creates new HoleSurfaceVolumeService.
     *
     * @param saveRemainedAt true if extra details about water remained at particular hill.
     */
    public HoleSurfaceVolumeService(boolean saveRemainedAt) {
        this.saveRemainedAt = saveRemainedAt;
    }

    public HoleSurfaceVolumeService() {
        this(true);
    }

    /**
     * Calculates water volume.
     * @param hole calculation volume on hole
     * @return water volume remained after the rain, units.
     */
    public WaterVolume calculateWaterVolume(Hole hole) {
        if (hole.getHeights().length < 3)
            return new WaterVolume(0, new int[hole.getHeights().length]);

        int left = 0, right = hole.getHeights().length - 1,
                maxLeft = 0, maxRight = 0,
                totalWater = 0;

        int[] waterRemainedAt = new int[0];
        if (saveRemainedAt)
            waterRemainedAt = new int[hole.getHeights().length];

        int remainedAt;
        int hill;

        // move from both edges to the middle
        while (left <= right) {
            maxLeft = max(maxLeft, hole.getHeights()[left]);
            maxRight = max(maxRight, hole.getHeights()[right]);
            if (maxLeft <= maxRight) {
                remainedAt = maxLeft - hole.getHeights()[left];
                hill = left;
                left++;
            } else {
                remainedAt = maxRight - hole.getHeights()[right];
                hill = right;
                right--;
            }

            totalWater += remainedAt;

            if (saveRemainedAt)
                waterRemainedAt[hill] = remainedAt;
        }
        return new WaterVolume(totalWater, waterRemainedAt);
    }
}
