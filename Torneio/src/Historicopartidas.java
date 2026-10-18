
public class Historicopartidas {

    private Partida[] partidas;
    private int quantidade;

    public Historicopartidas(int capacidade) {
        this.partidas = new Partida[capacidade];
        this.quantidade = 0;
    }

    public boolean adicionar(Partida p) {
        if (p == null || quantidade >= partidas.length) {
            return false;
        }
        partidas[quantidade] = p;
        quantidade++;
        return true;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Partida getPartida(int indice) {
        if (indice < 0 || indice >= quantidade) {
            return null;
        }
        return partidas[indice];
    }

    public boolean estaCheio() {
        return quantidade >= partidas.length;
    }

    public Partida[] buscarPorTime(String nomeTime) {
        int total = 0;
        for (int i = 0; i < quantidade; i++) {
            if (partidas[i].envolve(nomeTime)) {
                total++;
            }
        }

        Partida[] resultado = new Partida[total];
        int posicao = 0;
        for (int i = 0; i < quantidade; i++) {
            if (partidas[i].envolve(nomeTime)) {
                resultado[posicao] = partidas[i];
                posicao++;
            }
        }
        return resultado;
    }

    public Partida remover(int indice) {
        if (indice < 0 || indice >= quantidade) {
            return null;
        }
        Partida removida = partidas[indice];
        for (int i = indice; i < quantidade - 1; i++) {
            partidas[i] = partidas[i + 1];
        }
        partidas[quantidade - 1] = null;
        quantidade--;
        return removida;
    }
}