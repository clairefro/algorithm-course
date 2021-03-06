public class QuickUnionWeightedUF {
    private int[] id;
    private int[] sz;

    public QuickUnionWeightedUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        // chase parent pointers until reach root
        while (i != id[i])
            // below line helps flatten tree by making every other node in path point to
            // grandparent
            id[i] = id[id[i]];
        i = id[i];
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j)
            return;
        // link root of smaller tree to root of larger tree, update sz[]
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
}