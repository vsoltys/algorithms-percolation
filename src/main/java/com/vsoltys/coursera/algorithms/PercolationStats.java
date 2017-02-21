package com.vsoltys.coursera.algorithms;

/**
 * Programming Assignment 1: Percolation
 * 
 * @author: vsoltys
 *
 * @details: http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 *
 **/
public class PercolationStats {
    private int sideSize;
    private int trials;

    private double meanPercolationThreshold = 0.0;
    private double standardDeviation = 0.0;
    private double lowEndpointConfidenceInterval = 0.0;
    private double hiEndpointConfidenceInterval = 0.0;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("params should be grater that 0. n=" + n + ", trials=" + trials);
        }

        this.sideSize = n;
        this.trials = trials;

        runExperiment();
    }

    // sample mean of percolation threshold
    public double mean() {
        return meanPercolationThreshold;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return standardDeviation;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return lowEndpointConfidenceInterval;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return hiEndpointConfidenceInterval;
    }

    void runExperiment() {












    }

    // @formatter:off
    /**
     *  main() method that takes two command-line arguments n and T, performs T independent
     *  computational experiments (discussed above) on an n-by-n grid, and prints the sample meanPercolationThreshold,
     *  sample standard deviation, and the 95% confidence interval for the percolation threshold
     *
     * @param args
     *
     * Usage example:
     *
     * % java PercolationStats 200 100
     * meanPercolationThreshold                    = 0.5929934999999997
     * stddev                  = 0.00876990421552567
     * 95% confidence interval = [0.5912745987737567, 0.5947124012262428]
     *
     * % java PercolationStats 200 100
     * meanPercolationThreshold                    = 0.592877
     * stddev                  = 0.009990523717073799
     * 95% confidence interval = [0.5909188573514536, 0.5948351426485464]
     *
     */
    // @formatter:on
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);

        new PercolationStats(n, trails);
    }
}
