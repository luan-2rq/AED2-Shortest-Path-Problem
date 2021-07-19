public class AdjList {

    public Node head;
    public int max;
    public int n;

    public AdjList(int max){
        this.max = max;
        this.n = 0;
        //adding pivot node to the head
        this.head = new Node(0, 0);
    }

    public void add(Node node){
        if(n >= max) return;

        if(head.next == null){
            head.next = node;
        }else{
            Node current = head;
            while(current.next != null && node.id > current.next.id){
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }

        n++;
    }

    public Node getFirst(){
        return head.next;
    }
}
