package com.vsoltys.coursera.algorithms;


/**
 * Programming Assignment 1: Percolation
 *
 * @author: vsoltys
 * @details: http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 *
 * By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left site
 * IndexOutOfBoundsException is thrown if any argument to open(), isOpen(), or isFull() is outside its prescribed range
 */
public class Percolation {

    private int[] grid;
    private int gridSize;
    private int numberOfOpenSites;
    private int virtualTop;
    private int virtualGround;

    /**
     * creates n-by-n grid, with all sites blocked
     * @param n size of grid
     */
    public Percolation(int n) {
        isTrue(n > 0, "n should be more than 0");
        this.gridSize = n;

        grid = new int[gridSize * gridSize];
        virtualTop = 0;
        virtualGround = grid.length - 1;
    }

    /**
     * @param row row index
     * @param col column index
     * @return true if site(row, column) is open
     */
    public boolean isOpen(int row, int col) {
        validateCoordinates(row, col);
        return getValue(row, col) > 0;
    }

    /**
     * @param row row index
     * @param col column index
     * @return true if site(row, column) is full
     */
    public boolean isFull(int row, int col) {
        return !isOpen(row, col);
    }

    /**
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * @return true if system percolates
     */
    public boolean percolates() {
        return grid[virtualTop] == 0 ? false : connected(virtualTop, virtualGround);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validateCoordinates(row, col);
        int position = mapToGrid(row, col); // cell id
        int value = getValue(row, col);
        if (value == 0) {                               // if closed
            value = row == 1 ? 1 :                      // top row: virtual top id
                    row == gridSize ? grid.length - 1 : // ground row: virtual ground id
                            position + 1;               // or cell value

            grid[position] = value;
            numberOfOpenSites++;
            connectNeighborSites(row, col);
        }
    }

    private void connectNeighborSites(int row, int col) {
        int position = mapToGrid(row, col); // cell id

        // if left opened
        if (getValue(row, col - 1) > 0) {
            union(position, mapToGrid(row, col - 1));
        }
        // if right opened
        if (getValue(row, col + 1) > 0) {
            union(position, mapToGrid(row, col + 1));
        }

        // if top opened
        int topValue = getValue(row - 1, col);
        if (topValue > 0) {
            union(position, mapToGrid(row - 1, col));
        } else if (topValue < 0) {
            // -1; opened top row
            virtualTop = position;
        }

        // if bottom opened
        int bottomValue = getValue(row + 1, col);
        if (bottomValue > 0) {
            union(position, mapToGrid(row + 1, col));
        } else if (bottomValue < 0) {
            // -1; opened ground row
            virtualGround = position;
        }
    }

    private boolean connected(int p, int q) {
        return grid[p] == grid[q];
    }

    private void union(int p, int q) {
        int pid = grid[p];
        int qid = grid[q];
        for (int i = 0; i < grid.length; i++) {
            if (grid[i] == pid) {
                grid[i] = qid;
            }
        }
    }

    private void validateCoordinates(int row, int col) {
        isTrue(row > 0 && row <= gridSize, "row should be in range [0, " + gridSize + "]");
        isTrue(col > 0 && col <= gridSize, "col should be in range [0, " + gridSize + "]");
    }

    // use -1 to ignore/cutoff out of bounds access
    private int getValue(int row, int col) {
        int index = mapToGrid(row, col);
        return index > -1 ? grid[index] : -1;
    }

    // returns -1 if col/row out of range
    private int mapToGrid(int row, int col) {
        if (row > 0 && row <= gridSize && col > 0 && col <= gridSize) {
            return gridSize * --row + --col;
        }
        return -1;
    }

    private static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
