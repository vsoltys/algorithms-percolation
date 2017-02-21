package com.vsoltys.coursera.algorithms;

public class Percolation {

    private int[] grid;

    // TODO: add virtual top/bottom node for percolation check?

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        initializeGrid(n);
    }

    private void initializeGrid(int n) {
        isTrue(n > 0, "n should be more than 0");

        grid = new int[n*n];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = i + 1;
        }
    }

    private static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }


    // open site (row, col) if it is not open already
    public void open(int row, int col) {
    }
    // open site (row, col) if it is not open already

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return false;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // testing
    public int[] getGrid() {
        return grid;
    }
}
