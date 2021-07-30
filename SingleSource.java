
public class SingleSource{

    //Vector containing all the edges
    static Vertex[] vertices;
    int[] lbl;
    int count;
    int[] ts;
    int infinity = Integer.MAX_VALUE / 10;

    public SingleSource(){

    }

    public void initializeSingleSource(Digraph G, int s){
        vertices = new Vertex[G.V];
        for(int i = 0; i < G.V; i++){
            vertices[i] = new Vertex(i + 1);
            vertices[i].dist = infinity;
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
    
    
    public void DFS(Digraph G){
        int v;
        lbl = new int[G.V];
        ts = new int[G.V];
        count = G.V; 

        for(v = 0; v < G.V; v++){
            ts[v] = -1;
            lbl[v] = -1;
        }

        for(v = 0; v < G.V; v++){
            if(lbl[v] == -1)
            DFSvisit(G, v);
        }
    }

    public void DFSvisit(Digraph G, int v){
        Node p;
        lbl[v] = 0;
        for(p = G.adjList[v].getFirst(); p != null; p = p.next){ 
            if(lbl[p.id - 1] == -1)
            DFSvisit(G, p.id - 1);    
        }
        ts[--count] = v;
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


    // DAG

    public void DAGmin(Digraph G, int s){
        DFS(G);        
        initializeSingleSource(G, s);
        for(int i = 0; i < G.V; i++){
            Node current = G.adjList[ts[i]].getFirst();
            if(vertices[ts[i]].dist == infinity)continue;
            while(current != null){
                Vertex u = vertices[ts[i]];
                Vertex v = vertices[current.id - 1];
                int w = current.weight;
                relax(u, v, w);
                current = current.next;
            }
        }
    }
    public static void main(String args[]){
        Digraph digraph = new Digraph(1000, 0.5, 50, false);
        digraph.print();
        SingleSource singleSource = new SingleSource();

        long startTime = System.nanoTime();
        VertexMinHeap minHeap = singleSource.dijkstra(digraph, 3);
        Double duration  = (System.nanoTime() - startTime) / Math.pow(10, 6);

        for(int i = 0; i < digraph.V; i++) {
            Vertex vertex = minHeap.extractMin();
            System.out.println(vertex.id + " - " + vertex.dist);
        }

        System.out.println("The duration to run was: " + duration + "ms");
        //djikstraMinHeap.print();
    }
}