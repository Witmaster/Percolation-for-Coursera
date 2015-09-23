import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats 
{
    private double[] percolationData;
    public PercolationStats(int N, int T)
    {
        if (N <= 0 || T <= 0)
            throw (new java.lang.IllegalArgumentException());
        else
        {
            percolationData = new double[T];
            for (int i = 0; i < T; i++)
            {
                Percolation testGrid = new Percolation(N);
                int opened = 0;
                while (!testGrid.percolates())
                {
                    int rndi = StdRandom.uniform(1, N + 1);
                    int rndj = StdRandom.uniform(1, N + 1);
                    if (!testGrid.isOpen(rndi, rndj))
                    {
                        testGrid.open(rndi, rndj);
                        opened++;
                    }
                }
                percolationData[i] = (Double.parseDouble(Integer.toString(opened)) / (N * N));
            }
        }
    }
    public double mean()
    {
        return StdStats.mean(percolationData);
    }
    public double stddev()
    {
        return StdStats.stddev(percolationData);
    }
    public double confidenceLo()
    {
        return (mean() - (1.96 * this.stddev())) / java.lang.Math.sqrt(percolationData.length);
    }
    public double confidenceHi()
    {
        return (mean() + (1.96 * this.stddev())) / java.lang.Math.sqrt(percolationData.length);
    }
    
    public static void main(String[] args)
    {
        if (args.length > 0)
        {
            PercolationStats experiment = 
                new PercolationStats(Integer.parseInt(args[0]),
                                     Integer.parseInt(args[1]));
            StdOut.print("mean = ");
            StdOut.println(experiment.mean());
            StdOut.print("stddev = ");
            StdOut.print(experiment.stddev());
            StdOut.println();
            StdOut.print("95% confidence interval = ");
            StdOut.print(experiment.confidenceLo());
            StdOut.print(", ");
            StdOut.println(experiment.confidenceHi());
        }
    }
}