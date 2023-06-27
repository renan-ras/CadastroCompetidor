package Engenhariadas;

public class Corredor extends Atleta {

	private static final long serialVersionUID = 1L;

	public String prova() {
		return "100m com barreira";
	}
	public Corredor(String nome, int idade, String universidade) {
		super(nome, idade, universidade);
		this.modalidade = "Corrida";
	}
}
