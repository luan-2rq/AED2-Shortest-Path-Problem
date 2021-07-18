public class SingleSource{

    //Vector containing all the edges
    Vertex[] vertices;

    public SingleSource(){

    }

    public void initializeSingleSource(Digraph G, int s){
        vertices = new Vertex[G.V + 1];
        for(int i = 1; i <= G.V; i++){
            vertices[i] = new Vertex(i);
            vertices[i].dist = i;
            vertices[i].pred = null;
        }
        Vertex S = vertices[s];
        S.dist = 0;
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
            Q.insert(vertices[i + 1]);
        }

        while(Q.V > 0){
            Vertex u = Q.extractMin();
            S.insert(u);
            for(int i = 1; i <= G.V; i++){
                if(G.adj[u.id][i] != -1){
                    Vertex v = vertices[i];
                    relax(u, v, G.adj);
                }
            }
        }

        return S;
    }

    public void relax(Vertex u, Vertex v, double[][] w){
        if(v.dist > u.dist + w[u.id][v.id]){
            v.dist = u.dist + w[u.id][v.id];
            v.pred = u;
        }
    }


    /* Bellman-Ford
    
    public Vertex bellmanFord(Digraph G, int s){
    
        initializeSingleSource(G, s);
        Vertex v, w;
        
        link p;
        
        for(v=0; v < G.V; v++){
            for( each edge (u, v) pertencente a G.E)
                relax(u, v, w)  
        }
        for each edge (u,v) pertencente a G.E
            if v.d > u.d + w(u, v)
            return false

        return true
        
        return null;
    }
    
    */

    public static void main(String args[]){
        Digraph digraph = new Digraph(10, 0.4, 5, true);
        digraph.print();
        SingleSource singleSource = new SingleSource();
        VertexMinHeap djikstraMinHeap = singleSource.dijkstra(digraph, 3);
        for(int i = 0; i < 10; i++) System.out.println(i + " - " + djikstraMinHeap.extractMin().dist);
        //djikstraMinHeap.print();
    }
}