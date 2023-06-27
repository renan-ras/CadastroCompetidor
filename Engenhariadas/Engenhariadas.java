package Engenhariadas;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Engenhariadas {
	private ArrayList<Atleta> atletas;

	public Engenhariadas() {
		this.atletas = new ArrayList<Atleta>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Nadador leNadador (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Idade", "Universidade"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Nadador gato = new Nadador (valores[0],idade,valores[2]);
		return gato;
	}

	public Corredor leCorredor (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Idade", "Universidade"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Corredor corr = new Corredor (valores[0],idade,valores[2]);
		return corr;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public int retornaInteiro(String entrada) {
		int numInt;

		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um numero inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaAtletas (ArrayList<Atleta> atletas){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("engenhariadas.dados"));
			for (int i=0; i < atletas.size(); i++)
				outputStream.writeObject(atletas.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossivel criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Atleta> recuperaAtletas (){
		ArrayList<Atleta> atletasTemp = new ArrayList<Atleta>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("engenhariadas.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Atleta) {
					atletasTemp.add((Atleta) obj);
				}   
			}          
		} catch (EOFException ex) {
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com atletas não existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return atletasTemp;
		}
	}

	public void menuEngenhariadas (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Engenhariadas\n" +
					"Opções:\n" + 
					"1. Entrar Atletas\n" +
					"2. Exibir Atletas\n" +
					"3. Limpar Atletas\n" +
					"4. Gravar Atletas\n" +
					"5. Recuperar Atletas\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Estudantes Atletas\n" +
						"Opções:\n" + 
						"1. Corredor\n" +
						"2. Nadador\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: atletas.add((Atleta)leCorredor());
				break;
				case 2: atletas.add((Atleta)leNadador());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Estudante atleta para entrada não escolhido!");
				}

				break;
			case 2:
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com estudantes atletas primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < atletas.size(); i++)	{
					dados += atletas.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3:
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com estudantes atletas primeiramente");
					break;
				}
				atletas.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4:
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com estudantes atletas primeiramente");
					break;
				}
				salvaAtletas(atletas);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5:
				atletas = recuperaAtletas();
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo Engenhariadas");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		Engenhariadas eng = new Engenhariadas ();
		eng.menuEngenhariadas();

	}

}
