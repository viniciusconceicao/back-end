package br.com.searchdevelopers.godev.usecases;

public class ListaObj<T> {

    private T[] vetor;
    private int ultimaPosicao;

    public ListaObj(int tam) {
        vetor = (T[]) new Object[tam];
        ultimaPosicao = 0;
    }

    public boolean adiciona(T valor) {
        if (ultimaPosicao >= vetor.length) {
            System.out.println("Lista está cheia");
            return false;
        }
        vetor[ultimaPosicao++] = valor;
        return true;
    }

    public void exibe() {
        System.out.println("\nExibindo elementos da lista:");
        for (int i = 0; i < ultimaPosicao; i++) {
            System.out.print(vetor[i] + "\t");
        }
        System.out.println();
    }

    public int busca(T valor) {
        for (int i = 0; i < ultimaPosicao; i++) {
            if (vetor[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }


    public boolean removePeloIndice(int indice) {
        if (indice < 0 || indice >= ultimaPosicao) {
            return false;
        } else {
            for (int i = indice; i < ultimaPosicao - 1; i++) {
                vetor[i] = vetor[i + 1];
            }
            ultimaPosicao--;
            return true;
        }
    }

    public boolean removeElemento(T valor) {
        return removePeloIndice(busca(valor));
    }

    public int getTamanho() {
        return ultimaPosicao;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= ultimaPosicao) {
            return null;
        } else {
            return vetor[indice];
        }
    }

    public void limpa() {
        ultimaPosicao = 0;
    }

}
