//Minheap for the dijkstra algorithm
public class VertexMinHeap{

    //Vector containing paths
    Vertex[] heap;
    //Number of vertices
    int V = 0;

    public VertexMinHeap(int n){
        heap = new Vertex[n];
    }

    public void insert(Vertex vertex){
        if(V >= heap.length)return;
        this.heap[this.V] = vertex;
        this.V++;
        minHeapifyUp(this.V - 1);
    }

    public Vertex extractMin(){
        if (V > 0)
        {
            Vertex min = heap[0];
            heap[0] = heap[V - 1];
            this.V--;
            minHeapifyDown(0);
            return min;
        }
        return null;
    }

    public void minHeapifyDown(int pos)
    {
        int left = (2 * pos) + 1;
        int right = (2 * pos) + 2;
        int aux = pos;

        if (left < V && heap[left].dist < heap[pos].dist){
            aux = left;
        }
        if(right < V && heap[right].dist < heap[aux].dist){
            aux = right;
        }
        if (aux != pos){
           Vertex tmp = heap[pos];
           heap[pos] = heap[aux];
           heap[aux] = tmp;
           minHeapifyDown(aux);
        }
    }

    public void minHeapifyUp(int pos)
    {
       int parent = (pos - 1)/2;
       if (heap[parent].dist > heap[pos].dist)
       {
           Vertex tmp = heap[pos];
           heap[pos] = heap[parent];
           heap[parent] = tmp;
           minHeapifyUp(parent);
       }
    }
}