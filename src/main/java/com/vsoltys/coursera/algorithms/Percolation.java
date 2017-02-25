package com.vsoltys.coursera.algorithms;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Programming Assignment 1: Percolation
 *
 * @author: vsoltys
 * @details: http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 * <p>
 * By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left site
 * IndexOutOfBoundsException is thrown if any argument to open(), isOpen(), or isFull() is outside its prescribed range
 */
public class Percolation {

    // size of the grid
    private final int sideSize;

    // tracks open/blocked site state: false when blocked, true otherwise
    private final boolean[] grid;

    // virtual top and bottom sites: two last cells in data structure
    private final int virtualTop;
    private final int virtualBottom;

    // Union-Find data structure and algorithm
    private WeightedQuickUnionUF algorithm;

    // number of open sites
    private int numberOfOpenSites;

    /**
     * Creates n-by-n grid, with all sites blocked
     *
     * @param n size of grid
     */
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n should be more than 0");
        }

        sideSize = n;
        int gridSize = sideSize * sideSize;

        grid = new boolean[gridSize];
        algorithm = new WeightedQuickUnionUF(gridSize + 2);
        virtualTop = gridSize;
        virtualBottom = virtualTop + 1;
    }

    /**
     * Returns true if site (row, col) is open, false otherwise
     *
     * @param row row index
     * @param col column index
     * @return true if site(row, column) is open
     */
    public boolean isOpen(int row, int col) {
        validateCoordinates(row, col);
        return isSiteOpen(row, col);
    }

    /**
     * A full site is an open site that can be connected to an open site in the top row
     * via a chain of neighboring (left, right, up, down) open sites.
     *
     * @param row row index
     * @param col column index
     * @return true if site(row, column) is full
     */
    public boolean isFull(int row, int col) {
        return algorithm.connected(mapToIndex(row, col), virtualTop);
    }

    /**
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * Check if system percolates: there is a full site in the bottom row
     *
     * @return true if system percolates
     */
    public boolean percolates() {
        return algorithm.connected(virtualTop, virtualBottom);
    }

    /**
     * Opens a site (row, col) if it is not opened already
     * and connects it with its neighbor open sites
     *
     * @param row row index
     * @param col column index
     */
    public void open(int row, int col) {
        validateCoordinates(row, col);
        int position = mapToIndex(row, col);
        if (isSiteOpen(row, col)) {
            return;
        }

        // open site in percolation grid
        grid[position] = true;
        numberOfOpenSites++;

        // connect left cell
        if (isSiteOpen(row, col - 1)) {
            algorithm.union(position, mapToIndex(row, col - 1));
        }

        // connect right cell
        if (isSiteOpen(row, col + 1)) {
            algorithm.union(position, mapToIndex(row, col + 1));
        }

        // connect open (or virtual) top
        if (row == 1) {
            algorithm.union(position, virtualTop);
        } else if (isSiteOpen(row - 1, col)) {
            algorithm.union(position, mapToIndex(row - 1, col));
        }

        // connect open (or virtual) bottom
        if (row == sideSize) {
            algorithm.union(position, virtualBottom);
        } else if (isSiteOpen(row + 1, col)) {
            algorithm.union(position, mapToIndex(row + 1, col));
        }
    }

    /**
     * Maps 2d (row, col) coordinates to 1d index. Parameters should be within boundaries.
     *
     * @param row row index
     * @param col column index
     * @return mapped 1d index if params are within boundaries. -1 otherwise
     */
    private int mapToIndex(int row, int col) {
        if (row > 0 && row <= sideSize && col > 0 && col <= sideSize) {
            return sideSize * --row + --col;
        }
        return -1;
    }

    /**
     * Checks if site (row, col) is open
     *
     * @param row row index
     * @param col column index
     * @return true if site (row, col) is open, false otherwise
     */
    private boolean isSiteOpen(int row, int col) {
        int index = mapToIndex(row, col);
        return index >= 0 && grid[index]; // handle boundary sites: always closed
    }

    /**
     * Validates that coordinates (row, col) are within boundaries
     *
     * @param row row index
     * @param col column index
     */
    private void validateCoordinates(int row, int col) {
        isValidIndex(row, "row should be in range [1, " + sideSize + "]");
        isValidIndex(col, "col should be in range [1, " + sideSize + "]");
    }

    private void isValidIndex(int index, String message) {
        if (index < 1 || index > sideSize) {
            throw new IndexOutOfBoundsException(message);
        }
    }
}
