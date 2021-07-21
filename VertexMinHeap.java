//Minheap for the dijkstra algorithm
public class VertexMinHeap{

    //Vector containing paths
    Vertex[] heap;
    //Number of vertices
    int V = 0;

    public VertexMinHeap(int n){
        heap = new Vertex[n];
    }

    public boolean insert(Vertex vertex){
        boolean res = false;
  
        if(vertex.id < 1)return res;
        if(vertex.id > heap.length)return res;

        this.heap[this.V] = vertex;
        int pos = this.V;
        this.V++;
    
        while (heap[pos].dist < heap[(pos - 1) /2].dist) {
            heap[pos] = heap[(pos - 1)/2];
            heap[(pos - 1)/2] = vertex;
            pos = (pos - 1)/2;
        }

        res = true;
        return res;
    }

    public Vertex extractMin(){
        if (V > 0)
        {
            Vertex min = heap[0];
            heap[0] = heap[V - 1];
            this.V--;
            minHeapify(0);
            return min;
        }
        return null;
    }

    public void minHeapify(int pos)
    {
        int left = (2 * pos) + 1;
        int right = (2 * pos) + 2;
        if (!(pos >= V / 2)) {
            if (heap[pos].dist > heap[left].dist
                || heap[pos].dist > heap[right].dist) {
                if (heap[left].dist < heap[right].dist) {
                    Vertex vertex = heap[pos];
                    heap[pos] = heap[left];
                    heap[left] = vertex;
                    minHeapify(left);
                }
                else {
                    Vertex vertex = heap[pos];
                    heap[pos] = heap[right];
                    heap[right] = vertex;
                    minHeapify(right);
                }
            }
        }
    }

}