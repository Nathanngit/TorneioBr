
public class TabelaTorneio {
	
	    private Times[] times;
	    private int quantidade;

	    public TabelaTorneio(int capacidade) {
	        this.times = new Times[capacidade];
	        this.quantidade = 0;
	    }

	    public boolean adicionar(Times t) {
	        if (t == null || quantidade >= times.length) {
	            return false;
	        }
	       
	        for (int i = 0; i < quantidade; i++) {
	            if (times[i].getNome().equalsIgnoreCase(t.getNome())) {
	                return false;
	            }
	        }
	        times[quantidade] = t;
	        quantidade++;
	        return true;
	    }

	    
	    private boolean vemAntes(Times a, Times b) {
	        if (a.getPontos() != b.getPontos()) {
	            return a.getPontos() > b.getPontos();
	        }
	        if (a.getVitorias() != b.getVitorias()) {
	            return a.getVitorias() > b.getVitorias();
	        }
	        if (a.getDerrotas() != b.getDerrotas()) {
	            return a.getDerrotas() < b.getDerrotas();
	        }
	        return a.getNome().compareToIgnoreCase(b.getNome()) < 0;
	    }

	    
	    public void ordenarPorPontos() {
	        for (int i = 1; i < quantidade; i++) {
	            Times chave = times[i];
	            int j = i - 1;
	            while (j >= 0 && vemAntes(chave, times[j])) {
	                times[j + 1] = times[j];
	                j--;
	            }
	            times[j + 1] = chave;
	        }
	    }


	    public void ordenarPorNome() {
	        for (int i = 1; i < quantidade; i++) {
	            Times chave = times[i];
	            int j = i - 1;
	            while (j >= 0 && chave.getNome().compareToIgnoreCase(times[j].getNome()) < 0) {
	                times[j + 1] = times[j];
	                j--;
	            }
	            times[j + 1] = chave;
	        }
	    }


	    public Times buscarPorNome(String nome) {
	        ordenarPorNome();

	        int baixo = 0;
	        int alto = quantidade - 1;
	        Times encontrado = null;

	        while (baixo <= alto && encontrado == null) {
	            int meio = (baixo + alto) / 2;
	            int cmp = times[meio].getNome().compareToIgnoreCase(nome);
	            if (cmp == 0) {
	                encontrado = times[meio];
	            } else if (cmp < 0) {
	                baixo = meio + 1;
	            } else {
	                alto = meio - 1;
	            }
	        }

	        ordenarPorPontos();
	        return encontrado;
	    }

	    public void exibir() {
	        ordenarPorPontos();
	        for (int i = 0; i < quantidade; i++) {
	            System.out.println((i + 1) + "º " + times[i]);
	        }
	    }

	    public int getQuantidade() {
	        return quantidade;
	    }
	    
	    public Times remover(String nome) {
	        for (int i = 0; i < quantidade; i++) {
	            if (times[i].getNome().equalsIgnoreCase(nome)) {
	                Times removido = times[i];
	                for (int j = i; j < quantidade - 1; j++) {
	                    times[j] = times[j + 1];
	                }
	                times[quantidade - 1] = null;
	                quantidade--;
	                return removido;
	            }
	        }
	        return null;
	    }
	
}
