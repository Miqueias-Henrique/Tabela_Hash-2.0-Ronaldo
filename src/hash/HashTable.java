package hash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private final List<List<Pessoa>> tabela;
    private final int tamanho;
    private int colisoes;
    private int comparacoesBusca;

    public HashTable(int tamanho) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho da tabela deve ser positivo.");
        }
        this.tamanho = tamanho;
        this.tabela = new ArrayList<>(tamanho);
        for (int i = 0; i < tamanho; i++) {
            this.tabela.add(new LinkedList<>());
        }
    }

    private int hash(String cpf) {
        return Math.floorMod(cpf.hashCode(), tamanho);
    }

    public boolean inserir(Pessoa pessoa) {
        if (pessoa == null || !pessoa.isCpfValido()) {
            System.out.println("CPF invalido. Informe exatamente 11 digitos numericos.");
            return false;
        }
        int pos = hash(pessoa.getCpf());
        List<Pessoa> bucket = tabela.get(pos);
        for (Pessoa existente : bucket) {
            if (existente.getCpf().equals(pessoa.getCpf())) {
                System.out.println("CPF ja cadastrado.");
                return false;
            }
        }
        if (!bucket.isEmpty()) {
            colisoes++;
        }
        bucket.add(pessoa);
        return true;
    }

    public Pessoa buscar(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return null;
        }
        long inicio = System.nanoTime();
        int pos = hash(cpf);
        for (Pessoa pessoa : tabela.get(pos)) {
            comparacoesBusca++;
            if (pessoa.getCpf().equals(cpf)) {
                long fim = System.nanoTime();
                System.out.println("Tempo de busca: " + (fim - inicio) + " ns");
                return pessoa;
            }
        }
        long fim = System.nanoTime();
        System.out.println("Tempo de busca: " + (fim - inicio) + " ns");
        return null;
    }

    public boolean remover(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }
        int pos = hash(cpf);
        return tabela.get(pos).removeIf(p -> p.getCpf().equals(cpf));
    }

    public void imprimirTabela() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print("Posicao " + i + ": ");
            List<Pessoa> bucket = tabela.get(i);
            if (bucket.isEmpty()) {
                System.out.println("vazio");
            } else {
                for (int j = 0; j < bucket.size(); j++) {
                    System.out.print(bucket.get(j));
                    if (j < bucket.size() - 1) {
                        System.out.print(" | ");
                    }
                }
                System.out.println();
            }
        }
        System.out.println("Numero de colisoes: " + colisoes);
        System.out.println("Total de comparacoes em buscas: " + comparacoesBusca);
    }
}
