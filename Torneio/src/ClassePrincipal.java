

import java.util.Scanner;




	public class ClassePrincipal {

	    static TabelaTorneio tabela = new TabelaTorneio(30);
	    static Historicopartidas historico = new Historicopartidas(500);
	    static Scanner sc = new Scanner(System.in);
	    static int lerInt(String mensagem) {
	        while (true) {
	            System.out.print(mensagem);
	            try {
	                return Integer.parseInt(sc.nextLine().trim());
	            } catch (NumberFormatException e) {
	                System.out.println("Digite um número inteiro.");
	            }
	        }
	    }
	    
	    
	    public static void main(String[] args) {
	    	 popularDados();

	    	    int opcao = 0;
	    	    do {
                    System.out.println("-----Brasileirão----");
                    System.out.println("(Digite o número referente a opção desejada)");
                    System.out.println("1: Exibir tabela");
                    System.out.println("2: Cadastrar time");
                    System.out.println("3: Registrar partida");
                    System.out.println("4: Buscar seu time");
                    System.out.println("5: Buscar partidas de um time  ");
                    System.out.println("6: Listar todas as partidas");
                    System.out.println("7: Editar nome de um time");
                    System.out.println("8: Editar placar da partida");
                    System.out.println("9: Excluir time");
                    System.out.println("10 Excluir partida");
                    System.out.println("0: Sair");
                    opcao = lerInt("Escolha: ");
                    switch (opcao) {
					case 1:
						tabela.exibir();
						break;
					case 2:
						cadastrarTime();
						break;
					case 3:
						registrarPartida();
						break;
					case 4:
						buscarTime();
						break;
					case 5:
						buscarPartidasDoTime();
						break;
					case 6:
						listarPartidas();
						break;
					case 7:
						editarTime();
						break;
					case 8:
						editarPartida();
						break;
					case 9:
						removerTime();
						break;
					case 10:
						removerPartida();
						break;
					case 0:
						System.out.println("Encerrando...");
						break;
					default:
						System.out.println("Opção inválida.");
					}
                  
                    
	    	     
	    	    } while (opcao != 0);
	       
	    
	    }
	    
	    static String lerTexto(String mensagem) {
	        System.out.print(mensagem);
	        return sc.nextLine().trim();
	    }

	    static void cadastrarTime() {
	        String nome = lerTexto("Nome do time: ");
	        if (nome.isEmpty()) {
	            System.out.println("Nome vazio.");
	            return;
	        }
	        if (tabela.adicionar(new Times(nome))) {
	            System.out.println("Time cadastrado.");
	        } else {
	            System.out.println("Não foi possível (nome repetido ou tabela cheia).");
	        }
	    }

	    static void registrarPartida() {
	        if (historico.estaCheio()) {
	            System.out.println("Histórico cheio.");
	            return;
	        }
	        Times a = tabela.buscarPorNome(lerTexto("Time A: "));
	        Times b = tabela.buscarPorNome(lerTexto("Time B: "));
	        if (a == null || b == null) {
	            System.out.println("Time não encontrado.");
	            return;
	        }
	        int pa = lerInt("Placar de " + a.getNome() + ": ");
	        int pb = lerInt("Placar de " + b.getNome() + ": ");
	        try {
	            historico.adicionar(new Partida(a, b, pa, pb));
	            System.out.println("Partida registrada.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("Erro: " + e.getMessage());
	        }
	    }

	    static void buscarTime() {
	        Times t = tabela.buscarPorNome(lerTexto("Nome: "));
	        System.out.println(t != null ? t : "Time não encontrado.");
	    }

	    static void buscarPartidasDoTime() {
	        Partida[] r = historico.buscarPorTime(lerTexto("Nome: "));
	        if (r.length == 0) {
	            System.out.println("Nenhuma partida encontrada.");
	        }
	        for (int i = 0; i < r.length; i++) {
	            System.out.println(r[i]);
	        }
	    }

	    static void listarPartidas() {
	        for (int i = 0; i < historico.getQuantidade(); i++) {
	            System.out.println((i + 1) + " - " + historico.getPartida(i));
	        }
	    }

	    static void editarTime() {
	        Times t = tabela.buscarPorNome(lerTexto("Time a editar: "));
	        if (t == null) {
	            System.out.println("Time não encontrado.");
	            return;
	        }
	        String novo = lerTexto("Novo nome: ");
	        if (novo.isEmpty() || tabela.buscarPorNome(novo) != null) {
	            System.out.println("Nome vazio ou já existente.");
	            return;
	        }
	        t.setNome(novo);
	        System.out.println("Nome alterado.");
	    }

	    static void editarPartida() {
	        int n = lerInt("Número da partida (veja a opção 6): ");
	        Partida antiga = historico.getPartida(n - 1);
	        if (antiga == null) {
	            System.out.println("Partida inexistente.");
	            return;
	        }
	        int pa = lerInt("Novo placar de " + antiga.getTimeA().getNome() + ": ");
	        int pb = lerInt("Novo placar de " + antiga.getTimeB().getNome() + ": ");
	        if (pa < 0 || pb < 0) {
	            System.out.println("Placar não pode ser negativo.");
	            return;
	        }
	        Times a = antiga.getTimeA();
	        Times b = antiga.getTimeB();
	        historico.remover(n - 1).desfazer();
	        historico.adicionar(new Partida(a, b, pa, pb));
	        System.out.println("Partida editada (agora é a última da lista).");
	    }

	    static void removerTime() {
	        Times t = tabela.buscarPorNome(lerTexto("Time a remover: "));
	        if (t == null) {
	            System.out.println("Time não encontrado.");
	            return;
	        }
	        
	        for (int i = historico.getQuantidade() - 1; i >= 0; i--) {
	            Partida p = historico.getPartida(i);
	            if (p.envolve(t.getNome())) {
	                historico.remover(i);
	                p.desfazer();
	            }
	        }
	        tabela.remover(t.getNome());
	        System.out.println("Time e suas partidas foram removidos.");
	    }

	    static void removerPartida() {
	        int n = lerInt("Número da partida (veja a opção 6): ");
	        Partida p = historico.remover(n - 1);
	        if (p == null) {
	            System.out.println("Partida inexistente.");
	            return;
	        }
	        p.desfazer();
	        System.out.println("Partida removida.");
	    }

	    static void popularDados() {
	        Times Flamengo = new Times("Flamengo");
	        Times VilaNova = new Times("Vila-Nova");
	        Times Corinthians = new Times("Corinthians");
	        Times Cuiaba = new Times("Cuiabá");
	        Times Vasco = new Times("Vasco");
	        Times Gremio = new Times("Grêmio");
	        Times Palmeiras = new Times("Palmeiras");
	        Times Vitoria = new Times("Vitória");
	        Times Botafogo = new Times("Botafogo");
	        Times Fluminense = new Times("Fluminense");
	        Times Fortaleza = new Times("Fortaleza");
	        Times Santos = new Times("Santos");
	        Times Cruzeiro = new Times("Cruzeiro");
	        Times Bahia = new Times("Bahia");
	        Times Porto = new Times("Porto");
	        Times Sport = new Times("Sport");
	        Times Mirassol = new Times("Mirassol");
	        Times Internacional = new Times("Internacional");
	        Times Remo = new Times("Remo");
	        Times Bragantino = new Times("Bragantino");

	        Times[] times = { Flamengo,VilaNova,Corinthians, Cuiaba, Vasco, Gremio,Palmeiras,Fortaleza,
	                Vitoria,Botafogo,Fluminense,Santos,Cruzeiro, Bahia, Porto,Sport,Mirassol,Internacional,Remo,Bragantino }; 

	        tabela.adicionar(Flamengo);
	        tabela.adicionar(VilaNova);
	        tabela.adicionar(Corinthians);
	        tabela.adicionar(Cuiaba);
	        tabela.adicionar(Vasco);
	        tabela.adicionar(Gremio);
	        tabela.adicionar(Palmeiras);
	        tabela.adicionar(Fortaleza);
	        tabela.adicionar(Vitoria);
	        tabela.adicionar(Botafogo);
	        tabela.adicionar(Fluminense);
	        tabela.adicionar(Santos);
	        tabela.adicionar(Cruzeiro);
	        tabela.adicionar(Bahia);
	        tabela.adicionar(Porto);
	        tabela.adicionar(Sport);
	        tabela.adicionar(Mirassol);
	        tabela.adicionar(Internacional);
	        tabela.adicionar(Remo);
	        tabela.adicionar(Bragantino);

	        java.util.Random sorteio = new java.util.Random(42);
	        for (int i = 0; i < times.length; i++) {
	            for (int j = i + 1; j < times.length; j++) {
	                int placarA = sorteio.nextInt(4);
	                int placarB = sorteio.nextInt(4);
	                historico.adicionar(new Partida(times[i], times[j], placarA, placarB));
	            }
	        }
	        
	        
	    }
	}
    
