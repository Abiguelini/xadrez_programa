package aplicacao;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		List<PecaXadrez> captituradas = new ArrayList<>(); 
		
		while (true ) {
			
			try {
				UI.printGame(partidaXadrez, captituradas);
				System.out.println();
				System.out.print("Origem: ");
				Novaposicao origem = UI.lerposicaoxadrez(sc);
				
				boolean [][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.getPecaXadrez(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("Destido: ");
				Novaposicao fifi = UI.lerposicaoxadrez(sc);
				
				PecaXadrez pecaCapturada   = partidaXadrez.moverPeca(origem, fifi);
				if (pecaCapturada != null) {
					captituradas.add(pecaCapturada);
				}
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
