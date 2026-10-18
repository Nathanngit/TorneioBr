
public class Partida {
	
	    private Times timeA;
	    private Times timeB;
	    private int placarA;
	    private int placarB;

	    public Partida(Times timeA, Times timeB, int placarA, int placarB) {
	        if (timeA == null || timeB == null) {
	            throw new IllegalArgumentException("Os dois times são obrigatórios.");
	        }
	        if (timeA == timeB) {
	            throw new IllegalArgumentException("Um time não pode jogar contra ele mesmo.");
	        }
	        if (placarA < 0 || placarB < 0) {
	            throw new IllegalArgumentException("Placar não pode ser negativo.");
	        }
	       
	        this.timeA = timeA;
	        this.timeB = timeB;
	        this.placarA = placarA;
	        this.placarB = placarB;

	       
	        if (placarA > placarB) {
	            timeA.registroVitorias();
	            timeB.registroDerrotas();
	        } else if (placarA < placarB) {
	            timeB.registroVitorias();
	            timeA.registroDerrotas();
	        } else {
	        	timeA.registroEmpates();
	        	timeB.registroEmpates();
	        }
	    }
	    
	    public void desfazer() {
	        if (placarA > placarB) {
	            timeA.removerVitoria();
	            timeB.removerDerrota();
	        } else if (placarA < placarB) {
	            timeB.removerVitoria();
	            timeA.removerDerrota();
	        } else {
	            timeA.removerEmpate();
	            timeB.removerEmpate();
	        }
	    }

	    public Times getTimeA() { return timeA; }
	    public Times getTimeB() { return timeB; }
	    public int getPlacarA() { return placarA; }
	    public int getPlacarB() { return placarB; }

	    
	    public boolean envolve(String nomeTime) {
	        return timeA.getNome().equalsIgnoreCase(nomeTime)
	            || timeB.getNome().equalsIgnoreCase(nomeTime);
	    }

	    @Override
	    public String toString() {
	        return timeA.getNome() + " " + placarA + " x " + placarB + " " + timeB.getNome();
	    }
	
}
