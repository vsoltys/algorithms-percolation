package com.vsoltys.coursera.algorithms;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PercolationTest {
    public static final int SIDE_SIZE = 100;

    private Percolation percolation;

    @Before
    public void setUp() throws Exception {
        percolation = new Percolation(SIDE_SIZE);
    }

    @Test
    public void shouldPercolate() throws Exception {
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(1, SIDE_SIZE + 1);
            int col = StdRandom.uniform(1, SIDE_SIZE + 1);
            percolation.open(row, col);
        }
        int numberOfOpenSites = percolation.numberOfOpenSites();
        double totalSites = Math.pow(SIDE_SIZE, 2);
        double threshold = (double) numberOfOpenSites / totalSites;
        System.out.println("threshold: " + threshold + " (" + numberOfOpenSites + "/" + totalSites + ")");
        assertTrue(threshold > 0);
    }

    @Test
    public void shouldOpen() throws Exception {
        int row = StdRandom.uniform(1, SIDE_SIZE + 1);
        int col = StdRandom.uniform(1, SIDE_SIZE + 1);
        percolation.open(row, col);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithZeroColumn() throws Exception {
        percolation.open(0, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithNegativeRow() throws Exception {
        percolation.open(1, -1);
    }
}
