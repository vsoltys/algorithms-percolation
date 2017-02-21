package com.vsoltys.coursera.algorithms;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class PercolationTest {
    public static final int SIDE_SIZE = 3;

    private Percolation percolation;

    @Before
    public void setUp() throws Exception {
        percolation = new Percolation(SIDE_SIZE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnZeroSize() {
        new Percolation(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnLessThanZeroSize() {
        new Percolation(-2);
    }

    @Test
    public void shouldPreparePercolationGrid() throws Exception {
        int gridSize = SIDE_SIZE * SIDE_SIZE;
        assertTrue("should initialize grid with size " + gridSize, gridSize == percolation.getGrid().length);
        printGrid();
    }

    @Test
    @Ignore
    public void isOpen() throws Exception {

    }

    @Test
    @Ignore
    public void isFull() throws Exception {

    }

    @Test
    @Ignore
    public void numberOfOpenSites() throws Exception {

    }

    @Test
    @Ignore
    public void percolates() throws Exception {

    }

    private void printGrid() throws Exception {
        int[] percolationGrid = percolation.getGrid();
        for (int i = 0; i < percolationGrid.length; i++) {
            String tail = (i + 1) % SIDE_SIZE == 0 ? "\n" : "\t";
            System.out.print(percolationGrid[i] + tail);
        }
    }

}
