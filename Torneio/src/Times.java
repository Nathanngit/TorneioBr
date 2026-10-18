
public class Times {
  private String nome;
  private int vitorias;
  private int derrotas;
  private int empates;
  private int pontos;
  
  public Times(String nome) {
	 this.nome = nome;
	 this.vitorias = 0;
	 this.derrotas = 0;
	 this.empates = 0;
	 this.pontos = 0;
  }
   public void registroVitorias() {
	   vitorias++;
	   pontos += 3;
   }
    public void registroDerrotas() {
    	derrotas++;
    	
    }
    
    public void registroEmpates() { 
    	empates++;
    	pontos += 1;
    }
    
    public void removerVitoria() {
		vitorias--;
		pontos -= 3;
	}

	public void removerDerrota() {
		derrotas--;
	}

	public void removerEmpate() {
		empates--;
		pontos -= 1;
	}
    
   public String getNome() {
	   return nome;
   }
   
   public int getVitorias() {
	   return vitorias;
   }
   
   
   public int getDerrotas() {
	   return derrotas;
   }
   
   public int getPontos() {
	   return pontos;
   }
   
   public int getEmpates() {
	   return empates;
   };
   
   public void setNome(String nome) {
	   this.nome = nome;
   }
   
	
   
   @Override public String toString() {
	   return "Times{" +
	   "nome='" + nome + '\'' +
	   "| vitorias='" + vitorias + '\'' +
	   "| derrotas='" + derrotas + '\'' +
	   "| empates=" + empates +
	   "| pontos=" + pontos +
	  " }";
   }
   
}


