package model;

import java.util.Optional;

public class AtorGanhadorOscar {
    private String nome;
    private int idade;
    private int ano;
    private String filme;

    public AtorGanhadorOscar(String nome, int idade, int ano, String filme) {
        this.nome = nome;
        this.idade = idade;
        this.ano = ano;
        this.filme = filme;
    }


    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getAno() {
        return ano;
    }

    public String getFilme() {
        return filme;
    }

    @Override
    public String toString() {
        return
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", ano=" + ano +
                ", filme='" + filme + '\'';
    }

}
