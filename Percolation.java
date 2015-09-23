import edu.princeton.cs.algs4.WeightedQuickUnionUF;
    
public class Percolation
{
    private boolean[][] grid;
    private WeightedQuickUnionUF union;
    private WeightedQuickUnionUF filling;
    public Percolation(int N)
    {
        if (N > 0)
        {
            grid = new boolean[N][N];
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    grid[i][j] = false;
                }
            }
            union = new WeightedQuickUnionUF((N * N) + 2);
            filling = new WeightedQuickUnionUF((N * N) + 1);
        }
        else
        {
            throw (new java.lang.IllegalArgumentException());
        }
    }
    public void open(int i, int j)
    {
        if (i > 0 && i <= grid.length && j > 0 && j <= grid.length)
        {
            int indi = i - 1;
            int indj = j - 1;
            if (!grid[indi][indj])
            {
                grid[indi][indj] = true;
                if (indi > 0)
                {
                    if (grid[indi - 1][indj])
                    {
                        union.union((indi * grid.length) + indj, ((indi - 1) * grid.length) + indj);
                        filling.union((indi * grid.length) + indj, ((indi - 1) * grid.length) + indj);
                    }
                }
                else
                {
                    union.union((indi * grid.length) + indj, (grid.length * grid.length));
                    filling.union((indi * grid.length) + indj, (grid.length * grid.length));
                }
                if (indi < grid.length - 1)
                {
                    if (grid[indi + 1][indj])
                    {
                        union.union((indi * grid.length) + indj, ((indi + 1) * grid.length) + indj);
                        filling.union((indi * grid.length) + indj, ((indi + 1) * grid.length) + indj);
                    }
                }
                else
                {
                    union.union((indi * grid.length) + indj, (grid.length * grid.length) + 1);
                }
                if (indj > 0)
                {
                    if (grid[indi][indj - 1])
                    {
                        union.union((indi * grid.length) + indj, (indi * grid.length) + indj - 1);
                        filling.union((indi * grid.length) + indj, (indi * grid.length) + indj - 1);
                    }
                }
                if (indj < grid.length - 1)
                {
                    if (grid[indi][indj + 1])
                    {
                        union.union((indi * grid.length) + indj, (indi * grid.length) + indj + 1);
                        filling.union((indi * grid.length) + indj, (indi * grid.length) + indj + 1);
                    }
                }
            }
        }
        else
        { 
            throw (new java.lang.IndexOutOfBoundsException());
        }
    }
    public boolean isOpen(int i, int j)
    {
        if (i > 0 && i <= grid.length && j > 0 && j <= grid.length)
            return grid[i - 1][j - 1]; 
        else
            throw (new java.lang.IndexOutOfBoundsException());
    }
    public boolean isFull(int i, int j)
    {
        if (i > 0 && i <= grid.length && j > 0 && j <= grid.length)
        {
            int indi = i - 1;
            int indj = j - 1;
            return filling.connected((indi * grid.length) + indj, (grid.length * grid.length));
        }
        else
        {
            throw (new java.lang.IndexOutOfBoundsException());
        }
    }
    public boolean percolates()
    {
        int X = grid.length * grid.length;
        return union.connected(X + 1, X);
    }
}
      