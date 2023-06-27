package Engenhariadas;

import java.io.Serializable;

public abstract class Atleta implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String nome;
	private   int idade;
	private   String universidade;
	protected String modalidade;
	
	public Atleta(String nome, int idade, String universidade) {
		this.nome = nome;
		this.idade = idade;
		this.universidade = universidade;
	}
	public String toString() {
		String retorno = "";
		retorno += "Nome: "     + this.nome     + "\n";
		retorno += "Idade: "    + this.idade    + " anos\n";
		retorno += "Universidade: "     + this.universidade     + "\n";
		retorno += "Modalidade: "  + this.modalidade  + "\n";
		retorno += "Prova: "  + prova()        + "\n";
		return retorno;
	}
	public abstract String prova();
}
