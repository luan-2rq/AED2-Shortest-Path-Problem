import java.util.Random;

public class Edge{
    int start;
    int end;
    double weight;

    public Edge(int start, int end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

public class Path{
    int start;
    int end;
    double weight;

    public Path(int start, int end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

public class Digraph{
    
    //Number of vertices
    int V;
    //Number of edges(edges can also be referred to as archs)
    int E;
    //Adjacency Matrix
    double[][] adj;

    //Vector containing all the edges
    //Edge[] edges;

    public Digraph(int V, double P, double K){
        this.V = V;
        this.E = 0;

        this.adj = new double[V][V];
        
        //int maximumNumberOfEdges = V(V-1)/2;
        //edges = new Edge[maximumNumberOfEdges];

        //Initializing digraph, creating edges and setting the weight to each one of them
        initRandomPonderateDigraph(P, K);
    }

    public void initRandomPonderateDigraph(double p, double k){
        for (int i = 0; i < this.V; i++) {
            for (int j = 0; j < this.V; j++){
                if(i!=j){
                    double randomWeight = trueProbability(p)?getRandomNumber(0, k):0;
                    adj[i][j] = randomWeight;
                    E++;
                }
            }
        }
    }

    public double getRandomNumber(double min, double max) {
		double randomValue = min + (max - min) * Math.random();
		return randomValue;
	}

    //Given a certain probability this method will return true acoordingly to this probability
    public boolean trueProbability(double p){
        if(Math.random() <= p){
            return true;
        }
        return false;
    }

    public void print(){
        for (int v = 0; v < this.V; v++) {
            System.out.println(v + ": ");
            for (int w = 0; w < this.V; w++){
                if (this.adj[v][w] > 0){
                    System.out.print(w);
                }
                System.out.println();
            }
        }
    }
    //Dijkstra

    /* This algorithm will find the single-source shortest-path on a weighted
    directed graph only if all edge weights are nonnegative. */
    public void dijkstra(){

        //Vertices whose final shortest-path to the source have already been calculated
        int[] S;

    }
}

//Minheap for the dijkstra algorithm
public class MinHeap{

    //Vector containing paths
    Path[] heap;
    //adj matrix 
    int adj[][];
    //Number of paths
    int E;

    public MinHeap(int n){
        vector = new Path[n];
        adj = new int[n][n];
    }

    public boolean insert(Path edge){
        boolean res = false;
  
        //checking if the start and end vertices are not less than 1
        if(edge.start < 1 || edge.end < 1) return res;
        //checking if the start vertices and end doesnt surpass the maximum range
        if(edge.start > n || edge.end > n) return res;
        //checking if the element already exists
        if(this.adj[edge.start][edge.end] == 1) return res;


        this.adj[edge.start][edge.end] = 1;
        this.heap[this.E] = edge;
    
        while (heap[this.E].weight < heap[this.E/2].weight) {

        }
        this.E++;
        res = true;
        return res;
    }

    public boolean getMin(){

    }

}