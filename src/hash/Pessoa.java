package hash;

public class Pessoa implements ValidadorCPF {
    private final String cpf;
    private final String nome;
    private final int idade;

    public Pessoa(String cpf, String nome, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public boolean isCpfValido() {
        return cpf != null && cpf.matches("\\d{11}");
    }

    @Override
    public String toString() {
        return "CPF: " + cpf + ", Nome: " + nome + ", Idade: " + idade;
    }
}
