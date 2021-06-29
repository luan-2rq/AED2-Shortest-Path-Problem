import java.util.Random;

public class Digraph{
    int V;
    int A;
    double[][] adj;

    public Digraph(int V, double P, double K){
        this.V = V;
        this.A = 0;
        this.adj = new double[V][V];
        initRandomPonderateDigraph(P, K);
    }

    public void initRandomPonderateDigraph(double p, double k){
        for (int i = 0; i < this.V; i++) {
            for (int j = 0; j < this.V; j++){
                if(i!=j){
                    adj[i][j] = trueProbability(p)?getRandomNumber(0, k):0;
                }
            }
        }
    }

    public double getRandomNumber(double min, double max) {
		double randomValue = min + (max - min) * Math.random();
		return randomValue;
	}

    public boolean trueProbability(double p){
        if(Math.random() <= p){
            return true;
        }
        return false;
    }

    public void imprimir(){
        for (int v = 0; v < this.V; v++) {
            System.out.println(v + ": ");
            for (int w = 0; w < this.V; w++){
                if (this.adj[v][w] == 1){
                    System.out.print(w);
                }
                System.out.println();
            }
        }
    }
}