public class SingleSource{

    //Vector containing all the edges
    Vertex[] vertices;

    public SingleSource(){

    }

    public void initializeSingleSource(Digraph G, int s){
        vertices = new Vertex[G.V + 1];
        for(int i = 0; i < G.V; i++){
            vertices[i] = new Vertex(i + 1);
            vertices[i].dist = Integer.MAX_VALUE;
            vertices[i].pred = null;
        }
        Vertex S = vertices[s - 1];
        S.dist = 0;
    }

    
    public void relax(Vertex u, Vertex v, int w){
        if(v.dist > u.dist + w){
            v.dist = u.dist + w;
            v.pred = u;
        }
    }

    //*Dijkstra*//
    /* This algorithm will find the single-source shortest-path on a weighted
    directed graph only if all edge weights are nonnegative. */

    public VertexMinHeap dijkstra(Digraph G, int s){

        initializeSingleSource(G, s);

        //Vertices whose final shortest-path to the source have already been calculated
        VertexMinHeap S = new VertexMinHeap(G.V);
        //Min heap with all the vertices from the graph
        VertexMinHeap Q = new VertexMinHeap(G.V);

        for(int i = 0; i < G.V; i++){
            Q.insert(vertices[i]);
        }

        while(Q.V > 0){
            Vertex u = Q.extractMin();
            S.insert(u);
            Node currentNode = G.adjList[u.id - 1].getFirst();
            while(currentNode != null){
                Vertex v = vertices[currentNode.id - 1];
                relax(u, v, currentNode.weight);
                currentNode = currentNode.next;
            }
        }
        return S;
    }

    // Bellman-Ford
    
    public boolean bellmanFord(Digraph G, int s){
        initializeSingleSource(G, s);
        for(int i = 1; i < G.V; i++){
            for(int j = 0; j < G.V; j++){
                Node currentEdge = G.adjList[j].getFirst();
                while(currentEdge != null){
                    Vertex u = vertices[j];
                    Vertex v = vertices[currentEdge.id - 1];
                    int w = currentEdge.weight;
                    relax(u, v, w);
                    currentEdge = currentEdge.next;
                }
            }
        }
        for(int i = 0; i < G.V; i++){
            Node currentEdge = G.adjList[i].getFirst();
            while(currentEdge != null){
                Vertex u = vertices[i];
                Vertex v = vertices[currentEdge.id - 1];
                int w = currentEdge.weight;
                if(v.dist > u.dist + w)return false;
                currentEdge = currentEdge.next;
            }
        }
        return true;
    }

    public static void main(String args[]){
        Digraph digraph = new Digraph(1000, 0.5, 200, false);
        digraph.print();
        SingleSource singleSource = new SingleSource();
        VertexMinHeap djikstraMinHeap = singleSource.dijkstra(digraph, 3);
        for(int i = 0; i < digraph.V; i++) {
            Vertex vertex = djikstraMinHeap.extractMin();
            System.out.println(vertex.id + " - " + vertex.dist);
        }

        //djikstraMinHeap.print();
    }
}