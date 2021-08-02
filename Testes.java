
class MedidorTempo {

    private Long inicio;
    
    public void comeca(String metodo) {
        inicio = System.nanoTime();
    }
    
    public void termina(String metodo) {
        double tempo = (System.nanoTime() - inicio) / Math.pow(10, 6);
        System.out.println(tempo);
    }
}

public class Testes {

    public static void main(String args[]){

        int[] s = {10000, 50000, 100000, 500000, 1000000, 5000000, 10000000};
		double[] p = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
        int k = 100;
        int source = 10;

        SingleSource singleSource = new SingleSource();
        MedidorTempo medidorDeTempo = new MedidorTempo();

		for(int i = 0; i < s.length; i++){
            System.out.println("\n");
            for (int j = 0; j < p.length; j++) {
                System.out.println("|| Bellman-Ford com digrafo ciclico, com " + "P = " + p[j] + "; e S = " + s[i] + "; ||\n");
                Digraph digraph = new Digraph(s[i], p[j], k, false);
                //Tempo Bellmanford
                medidorDeTempo.comeca("Bellmanford");
                singleSource.bellmanFord(digraph, source);
                medidorDeTempo.termina("Bellmanford");
            }
            for (int j = 0; j < p.length; j++) {
                System.out.println("|| Dijkstra com digrafo ciclico, com " + "P = " + p[j] + "; e S = " + s[i] + "; ||\n");
                Digraph digraph = new Digraph(s[i], p[j], k, false);
                //Tempo Dijkstra
                medidorDeTempo.comeca("Dijkstra");
                singleSource.dijkstra(digraph, source);
                medidorDeTempo.termina("Dijkstra");
            }
            for (int j = 0; j < p.length; j++) {
                System.out.println("|| DAGmin com digrafo aciclico, com " + "P = " + p[j] + "; e S = " + s[i] + "; ||\n");
                Digraph digraphDag = new Digraph(s[i], p[j], k, true);
                 //Tempo DagMin
                medidorDeTempo.comeca("DAGMin");
                singleSource.DAGmin(digraphDag, source);
                medidorDeTempo.termina("Dijkstra");
            }
            for (int j = 0; j < p.length; j++) {
                System.out.println("|| Bellman-Ford com digrafo aciclico, com " + "P = " + p[j] + "; e S = " + s[i] + "; ||\n");
                Digraph digraph = new Digraph(s[i], p[j], k, true);
                //Tempo Bellmanford
                medidorDeTempo.comeca("Bellmanford");
                singleSource.bellmanFord(digraph, source);
                medidorDeTempo.termina("Bellmanford");
            }
            for (int j = 0; j < p.length; j++) {
                System.out.println("|| Dijkstra com digrafo aciclico, com " + "P = " + p[j] + "; e S = " + s[i] + "; ||\n");
                Digraph digraph = new Digraph(s[i], p[j], k, true);
                //Tempo Dijkstra
                medidorDeTempo.comeca("Dijkstra");
                singleSource.dijkstra(digraph, source);
                medidorDeTempo.termina("Dijkstra");
            }
        }
    }
}
