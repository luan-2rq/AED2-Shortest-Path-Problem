//Minheap for the dijkstra algorithm
public class VertexMinHeap{

    //Vector containing paths
    Vertex[] heap;
    //adj matrix 
    int adj[][];
    //Number of vertices
    int V;

    public VertexMinHeap(int n){
        heap = new Vertex[n];
        adj = new int[n][n];
    }

    public boolean insert(Vertex vertex){
        boolean res = false;
  
        //checking if the start and end vertices are not less than 1
        if(vertex.id < 1) return res;
        //checking if the start vertices and end doesnt surpass the maximum range
        if(vertex.id > heap.length) return res;

        this.heap[this.V] = vertex;
        int pos = this.V;
        this.V++;
    
        while (heap[pos].dist < heap[pos/2].dist) {
            heap[pos] = heap[pos/2];
            heap[pos/2] = vertex;
        }

        res = true;
        return res;
    }

    public Vertex extractMin(){
        if (V > 0)
        {
            Vertex min = heap[0];
            heap[0] = heap[V];
            heap[V] = null;
            minHeapify(0);
            return min;
        }
        return null;
    }

    public void minHeapify(int pos)
    {
        int left = 2 * pos + 1;
        int right = 2 * pos + 2;

        if (pos >= (V / 2) && pos <= V) {
            if (heap[pos].dist > heap[left].dist
                || heap[pos].dist > heap[right].dist) {

                if (heap[left].dist < heap[right].dist) {
                    Vertex vertex = heap[pos];
                    while (heap[pos].dist < heap[left].dist) {
                        heap[pos] = heap[left];
                        heap[left] = vertex;
                    }
                    minHeapify(left);
                }
                else {
                    Vertex vertex = heap[pos];
                    while (heap[pos].dist < heap[right].dist) {
                        heap[pos] = heap[right];
                        heap[right] = vertex;
                    }
                    minHeapify(right);
                }
            }
        }
    }

}