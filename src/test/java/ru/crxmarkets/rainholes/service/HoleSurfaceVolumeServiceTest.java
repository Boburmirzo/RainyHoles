package ru.crxmarkets.rainholes.service;

import org.junit.Before;
import org.junit.Test;
import ru.crxmarkets.rainholes.domain.Hole;
import ru.crxmarkets.rainholes.domain.WaterVolume;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Verifies Surface Water Calc behavior.
 * It checks known samples and edges cases.
 *
 * @author Bobur Umurzokov
 */
public class HoleSurfaceVolumeServiceTest {

    private HoleSurfaceVolumeService holeSurfaceVolumeService;

    @Before
    public void setUp() {
        holeSurfaceVolumeService = new HoleSurfaceVolumeService(false);
    }

    @Test
    public void calculateWaterVolume_whenEmpty_shouldBeZero() {
        assertEquals(0, holeSurfaceVolumeService.calculateWaterVolume(new Hole(new int[] {})).getTotal());
    }

    @Test
    public void calculateWaterVolume_when2Heights_shouldBeZero() {
        assertEquals(0, holeSurfaceVolumeService.calculateWaterVolume(new Hole(new int[] {4, 1})).getTotal());
    }

    @Test
    public void calculateWaterVolume_when32412_shouldBe2() {
        assertEquals(2, holeSurfaceVolumeService.calculateWaterVolume(new Hole(new int[] {3, 2, 4, 1, 2})).getTotal());
    }

    @Test
    public void calculateWaterVolume_when411023_shouldBeTotal8_withWaterRemained022310() {
        holeSurfaceVolumeService = new HoleSurfaceVolumeService(true);
        WaterVolume waterVolumeResult = holeSurfaceVolumeService.calculateWaterVolume(new Hole(new int[]{4, 1, 1, 0, 2, 3}));
        assertEquals(8, waterVolumeResult.getTotal());
        assertArrayEquals(new int[] {0, 2, 2, 3, 1, 0}, waterVolumeResult.getRemainedAt());
    }
}
