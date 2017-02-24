package com.vsoltys.coursera.algorithms;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Programming Assignment 1: Percolation
 *
 * @author: vsoltys
 * @details: http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 **/
public class PercolationStats {
    private int sideSize;
    private int trials;
    private double[] thresholds;

    private double meanPercolationThreshold;
    private double standardDeviation;
    private double lowEndpointConfidenceInterval;
    private double hiEndpointConfidenceInterval;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("params should be greater that 0. n=" + n + ", trials=" + trials);
        }

        this.sideSize = n;
        this.trials = trials;
        thresholds = new double[trials];

        runExperiment();
    }

    /**
     * main() method that takes two command-line arguments n and T, performs T independent
     * computational experiments (discussed above) on an n-by-n grid, and prints the sample meanPercolationThreshold,
     * sample standard deviation, and the 95% confidence interval for the percolation threshold
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trails);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }

    void runExperiment() {
        for (int trial = 0; trial < trials; trial++) {
            Percolation percolation = new Percolation(sideSize);
            while (!percolation.percolates()) {
                percolation.open(getRandomValue(), getRandomValue());
            }
            int numberOfOpenSites = percolation.numberOfOpenSites();
            thresholds[trial] = (double) numberOfOpenSites / Math.pow(sideSize, 2);
        }

        meanPercolationThreshold = StdStats.mean(thresholds);
        standardDeviation = StdStats.stddev(thresholds);
        lowEndpointConfidenceInterval = (meanPercolationThreshold - (1.96 * standardDeviation)) / Math.sqrt(trials);
        hiEndpointConfidenceInterval = (meanPercolationThreshold + (1.96 * standardDeviation)) / Math.sqrt(trials);
    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return meanPercolationThreshold;
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return standardDeviation;
    }

    /**
     * @return low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return lowEndpointConfidenceInterval;
    }

    /**
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return hiEndpointConfidenceInterval;
    }

    private int getRandomValue() {
        return StdRandom.uniform(1, sideSize + 1);
    }
}
