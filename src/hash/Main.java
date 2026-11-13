package hash;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashTable tabela = new HashTable(10);
        carregarDadosIniciais(tabela);

        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (true) {
                exibirMenu();
                Integer opcao = lerInteiro(scanner);
                if (opcao == null || opcao == 0) {
                    System.out.println("Saindo...");
                    break;
                }
                switch (opcao) {
                    case 1 -> inserirPessoa(tabela, scanner);
                    case 2 -> buscarPessoa(tabela, scanner);
                    case 3 -> removerPessoa(tabela, scanner);
                    case 4 -> tabela.imprimirTabela();
                    default -> System.out.println("Opcao invalida!");
                }
            }
        }
    }

    private static void carregarDadosIniciais(HashTable tabela) {
        for (int i = 0; i < 30; i++) {
            String cpf = String.format("%011d", i);
            tabela.inserir(new Pessoa(cpf, "Pessoa" + i, 20 + (i % 10)));
        }
        System.out.println("30 registros iniciais carregados na tabela hash.");
    }

    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1 - Inserir pessoa");
        System.out.println("2 - Buscar pessoa");
        System.out.println("3 - Remover pessoa");
        System.out.println("4 - Imprimir tabela");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private static Integer lerInteiro(Scanner scanner) {
        while (true) {
            if (!scanner.hasNextLine()) {
                return null;
            }
            String linha = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.print("Digite um numero valido: ");
            }
        }
    }

    private static void inserirPessoa(HashTable tabela, Scanner scanner) {
        System.out.print("Digite o CPF (11 digitos): ");
        String cpf = scanner.nextLine().trim();
        System.out.print("Digite o Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Digite a Idade: ");
        Integer idade = lerInteiro(scanner);
        if (idade == null || idade < 0) {
            System.out.println("Idade invalida.");
            return;
        }
        if (tabela.inserir(new Pessoa(cpf, nome, idade))) {
            System.out.println("Cadastro realizado!");
        }
    }

    private static void buscarPessoa(HashTable tabela, Scanner scanner) {
        System.out.print("Informe o CPF para busca: ");
        String cpf = scanner.nextLine().trim();
        Pessoa pessoa = tabela.buscar(cpf);
        if (pessoa != null) {
            System.out.println(pessoa);
        } else {
            System.out.println("Nao encontrado. Verifique o cadastro ou os 11 digitos do CPF.");
        }
    }

    private static void removerPessoa(HashTable tabela, Scanner scanner) {
        System.out.print("Informe o CPF para remover: ");
        String cpf = scanner.nextLine().trim();
        if (tabela.remover(cpf)) {
            System.out.println("CPF removido com sucesso!");
        } else {
            System.out.println("CPF nao encontrado ou formato invalido.");
        }
    }
}
