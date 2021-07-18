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
  
        //checking if the start and end vertices are not less than 1
        if(vertex.id < 1)return res;
        //checking if the start vertices and end doesnt surpass the maximum range
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

    // Function to print the contents of the heap
    public void print()
    {
        for (int i = 0; i < V / 2; i++) {
            System.out.print(" PARENT : " + heap[i].dist
                                + " LEFT CHILD : " + heap[2 * i + 1].dist
                                + " RIGHT CHILD :" + heap[2 * i + 2].dist);
            System.out.println();
        }
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