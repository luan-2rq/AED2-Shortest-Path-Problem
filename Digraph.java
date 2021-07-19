public class Digraph{
    
    //Number of vertices
    int V;
    //Number of edges(edges can also be referred to as archs)
    int E;
    //Adjacency List
    AdjList[] adjList;
    //Boolean informing if digraph is acyclic
    boolean acyclic;

    public Digraph(int V, double P, double K, boolean acyclic){
        this.V = V;
        this.E = 0;

        //The size of the matriz is V + 1, because the index of the vertices will begin at 1
        this.adjList = new AdjList[V];

        for(int i = 0; i < V; i++){
            this.adjList[i] = new AdjList(V);
        }

        this.acyclic = acyclic;

        //Initializing digraph, creating edges and setting the weight to each one of them
        initRandomPonderateDigraph(P, K);
    }

    public void initRandomPonderateDigraph(double p, double k){
        for (int i = 0; i < this.V; i++) {
            for (int j = 0; j < this.V; j++){
                if(acyclic){
                    if(i<j){
                        double randomWeight = trueProbability(p)?getRandomNumber(0, k):-1;
                        if(randomWeight != -1)adjList[i].add(new Node(j + 1, randomWeight));
                        E++;
                    }
                }else{
                    double randomWeight = trueProbability(p)?getRandomNumber(0, k):-1;
                    if(randomWeight != -1)adjList[i].add(new Node(j + 1, randomWeight));
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
            System.out.print(v + 1 + ": ");
            int printedArchCount = 0;
            Node currentNode = this.adjList[v].getFirst();
            while(currentNode != null){
                if(printedArchCount > 0){
                    System.out.print(", " + currentNode.id + ":" + currentNode.weight);
                }else{
                    System.out.print(currentNode.id + ":" + currentNode.weight);
                 }
                printedArchCount++;
                currentNode = currentNode.next;
            }
            System.out.println();
        }
    }
}

