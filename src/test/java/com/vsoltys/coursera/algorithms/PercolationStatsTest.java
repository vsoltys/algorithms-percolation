package com.vsoltys.coursera.algorithms;

import org.junit.Test;

public class PercolationStatsTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithZeroNParameter() throws Exception {
        new PercolationStats(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithZeroTrialsParameter() throws Exception {
        new PercolationStats(1, 0);
    }
}
