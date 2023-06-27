package Engenhariadas;

public class Nadador extends Atleta {

	private static final long serialVersionUID = 1L;

	public String prova() {
		return "50m nado livre";
	}
	public Nadador(String nome, int idade, String universidade) {
		super(nome, idade, universidade);
		this.modalidade = "Natação";
	}
}
