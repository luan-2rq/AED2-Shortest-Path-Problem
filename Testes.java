
class MedidorTempo {

    private long inicio;
    
    public void comeca(String metodo) {
        System.out.println("Medindo o tempo (" + metodo + ")");
        inicio = System.currentTimeMillis();
    }
    
    public void termina(String metodo) {
        System.out.println("Tempo gasto ("+ metodo + "): " + ((System.currentTimeMillis() - inicio) / 1000) + " segundo(s).\n");
    }
}

// int[] s = {1000, 10000, 20000, 75000, 150000, 500000};
// int[] s = {5000, 15000, 50000, 100000, 200000, 1000000};
public class Testes {

    public static void main(String args[]){

        int[] s = {1000, 5000, 10000, 15000, 20000, 50000, 75000, 100000, 150000, 200000, 500000, 1000000};
		double[] p = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
        int k = 100;
        int source = 10;

        SingleSource singleSource = new SingleSource();
        MedidorTempo medidorDeTempo = new MedidorTempo();

		//comecando o teste do maior t para o menor, pois quanto menor o t mais demora
		for(int i = 0; i < s.length; i--){
            for (int j = 0; j < p.length; j++) {
                Digraph digraph = new Digraph(s[i], p[j], k, false);
                Digraph digraphDag = new Digraph(s[i], p[j], k, true);

                System.out.println("Testes com S = " + s[i] + "; e P = " + p[j] + ";\n");

                //Tempo Bellmanford
                medidorDeTempo.comeca("Bellmanford");
                singleSource.bellmanFord(digraph, source);
                medidorDeTempo.termina("Bellmanford");

                //Tempo Dijkstra
                medidorDeTempo.comeca("Dijkstra");
                singleSource.dijkstra(digraph, source);
                medidorDeTempo.termina("Dijkstra");

                //Tempo DagMin
                medidorDeTempo.comeca("DAGMin");
                singleSource.dijkstra(digraphDag, source);
                medidorDeTempo.termina("DAGMin");

            }
        }
    }
}
