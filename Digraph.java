public class Digraph{
    
    //Number of vertices
    int V;
    //Number of edges(edges can also be referred to as archs)
    int E;
    //Adjacency Matrix
    double[][] adj;
    //Boolean informing if digraph is acyclic
    boolean acyclic;

    public Digraph(int V, double P, double K, boolean acyclic){
        this.V = V;
        this.E = 0;

        //The size of the matriz is V + 1, because the index of the vertices will begin at 1
        this.adj = new double[V + 1][V + 1];

        this.acyclic = acyclic;

        //Initializing digraph, creating edges and setting the weight to each one of them
        initRandomPonderateDigraph(P, K);
    }

    public void initRandomPonderateDigraph(double p, double k){
        for (int i = 1; i <= this.V; i++) {
            for (int j = 1; j <= this.V; j++){
                if(acyclic){
                    if(i!=j){
                        double randomWeight = trueProbability(p)?getRandomNumber(0, k):-1;
                        adj[i][j] = randomWeight;
                        E++;
                    }
                }else{
                    double randomWeight = trueProbability(p)?getRandomNumber(0, k):-1;
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
}

