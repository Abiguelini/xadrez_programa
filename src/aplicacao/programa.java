package aplicacao;


import java.util.InputMismatchException;
import java.util.Scanner;

import jogodetabuleiro.ExcecaoJogo;
import xadrez.ExcecaoXadrez;
import xadrez.Novaposicao;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Programa {

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		Scanner sc = new Scanner(System.in);
		
		while (true ) {
			
			try {
				UI.printTabuleiro(partidaXadrez.getPecaXadrez());
				System.out.println();
				System.out.print("Origem: ");
				Novaposicao origem = UI.lerposicaoxadrez(sc);
				
				System.out.println();
				System.out.print("Destido: ");
				Novaposicao fifi = UI.lerposicaoxadrez(sc);
				
				PecaXadrez pecaCapturada   = partidaXadrez.moverPeca(origem, fifi);
			}
			catch(ExcecaoJogo e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

}
