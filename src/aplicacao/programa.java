package aplicacao;


import java.util.Scanner;


import xadrez.*;

public class Programa {

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		Scanner sc = new Scanner(System.in);
		
		while (true ) {
			UI.printTabuleiro(partidaXadrez.getPecaXadrez());
			System.out.println();
			System.out.print("Origem: ");
			Novaposicao origem = UI.lerposicaoxadrez(sc);
			
			System.out.println();
			System.out.print("Destido: ");
			Novaposicao fifi = UI.lerposicaoxadrez(sc);
			
			PecaXadrez pecaCapturada   = partidaXadrez.moverPeca(origem, fifi);
			
		}
	}

}
