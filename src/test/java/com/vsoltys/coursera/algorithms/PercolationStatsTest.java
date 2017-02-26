package com.vsoltys.coursera.algorithms;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PercolationStatsTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithZeroNParameter() throws Exception {
        new PercolationStats(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithZeroTrialsParameter() throws Exception {
        new PercolationStats(1, 0);
    }

    @Test
    public void shouldCollectPercolationStats() throws Exception {
        int size = 700;
        int trials = 100;
        PercolationStats percolationStats = new PercolationStats(size, trials);
        double meanPercolationThreshold = percolationStats.mean();
        double standardDeviation = percolationStats.stddev();
        double lowEndpointConfidenceInterval = percolationStats.confidenceLo();
        double hiEndpointConfidenceInterval = percolationStats.confidenceHi();

        System.out.println("mean                    = " + meanPercolationThreshold);
        System.out.println("stddev                  = " + standardDeviation);
        System.out.println("95% confidence interval = [" + lowEndpointConfidenceInterval + ", " + hiEndpointConfidenceInterval + "]");
        System.out.println("wallclock time (avg)    = " + percolationStats.getWallclockTime() + " second(s)");

        assertTrue(meanPercolationThreshold > 0);
        assertTrue(standardDeviation > 0);
        assertTrue(lowEndpointConfidenceInterval > 0);
        assertTrue(hiEndpointConfidenceInterval > 0);
    }
}
