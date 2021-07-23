public class Digraph{
    
    //Number of vertices
    int V;
    //Number of edges(edges can also be referred to as archs)
    int E;
    //Adjacency List
    AdjList[] adjList;
    //Boolean informing if digraph is acyclic
    boolean acyclic;

    public Digraph(int S, double P, double K, boolean acyclic){

        if(acyclic){
            this.V = (int) Math.round(((P / 2 - 1) + Math.sqrt(Math.pow((1 - P / 2), 2) + 4 * P / 2 * S)) / (2 * P / 2));;
        }else{
            this.V = (int) Math.round(((P-1) + Math.sqrt(Math.pow((1-P), 2) + 4 * P * S))/(2*P));
        }

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
                        int randomWeight = trueProbability(p)?getRandomNumber(0, k):-1;
                        if(randomWeight != -1)adjList[i].add(new Node(j + 1, randomWeight));
                        E++;
                    }
                }else{
                    if(i != j){
                        int randomWeight = trueProbability(p)?getRandomNumber(0, k):-1;
                        if(randomWeight != -1)adjList[i].add(new Node(j + 1, randomWeight));
                        E++;
                    }
                }
                    
            }

        }
    }

    public int getRandomNumber(double min, double max) {
		int randomValue = (int)Math.round(min + (max - min) * Math.random());
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

