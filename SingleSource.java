public class SingleSource{

    //Vector containing all the edges
    Vertex[] vertices;

    public SingleSource(){

    }

    public void initializeSingleSource(Digraph G, int s){
        for(int i = 1; i <= G.V; i++){
            vertices[i] = new Vertex(i);
            vertices[i].dist = Integer.MAX_VALUE;
            vertices[i].pred = null;
        }
        Vertex S = vertices[s];
        S.dist = 0;
    }

    //*Dijkstra*//
    /* This algorithm will find the single-source shortest-path on a weighted
    directed graph only if all edge weights are nonnegative. */

    public Vertex dijkstra(Digraph G, int s){

        initializeSingleSource(G, s);

        //Vertices whose final shortest-path to the source have already been calculated
        VertexMinHeap S = new VertexMinHeap(G.V);
        //Min heap with all the vertices from the graph
        VertexMinHeap Q = new VertexMinHeap(G.V);

        for(int i = 1; i <= G.V; i++){
            Q.insert(vertices[i]);
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

        return S.extractMin();
    }

    public void relax(Vertex u, Vertex v, double[][] w){
        if(v.dist > u.dist + w[u.id][v.id]){
            v.dist = u.dist + w[u.id][v.id];
            v.pred = u;
        }
    }
}