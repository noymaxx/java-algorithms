import java.util.ArrayList;

public class AMDigraph {
    private final int V;
    private int E;
    private final boolean[][] adj;

    public AMDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new boolean[V][V];
    }

    public void addEdge(int v, int w) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        if (!adj[v][w]) {
            E++;
        }
        adj[v][w] = true;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Integer> adj(int v) {
        ArrayList<Integer> adjList = new ArrayList<>();
        for (int w = 0; w < V; w++) {
            if (adj[v][w]) {
                adjList.add(w);
            }
        }
    }

    public static void main(String[] args) {

    }
}
