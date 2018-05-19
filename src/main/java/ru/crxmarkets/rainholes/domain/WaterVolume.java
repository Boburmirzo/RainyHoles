package ru.crxmarkets.rainholes.domain;

/**
 * Represents the results of water volume in holes
 * remained after rain calculation.
 *
 * @author Bobur Umurzokov
 */
public class WaterVolume {
    private int total;
    
    private int[] remainedAt;

    public WaterVolume(int total) {
        this(total, null);
    }

    public WaterVolume(int total, int[] remainedAt) {
        this.total = total;
        this.remainedAt = remainedAt;
    }

    public int getTotal() {
        return total;
    }

    public int[] getRemainedAt() {
        return remainedAt;
    }
}
