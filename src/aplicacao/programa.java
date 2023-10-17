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
<<<<<<< HEAD
		List<PecaXadrez>capturadas = new ArrayList<>();
		
=======
		List<PecaXadrez> captituradas = new ArrayList<>(); 
>>>>>>> 5d5d5211041444121698aa46ab6eff74d2f5d711
		
		while (true ) {
			
			try {
<<<<<<< HEAD
				UI.clearScreen();
				UI.printGame(partidaXadrez, capturadas);
=======
				UI.printGame(partidaXadrez, captituradas);
>>>>>>> 5d5d5211041444121698aa46ab6eff74d2f5d711
				System.out.println();
				System.out.print("Origem: ");
				Novaposicao origem = UI.lerposicaoxadrez(sc);
				
				boolean [][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.getPecaXadrez(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("Destido: ");
				Novaposicao fifi = UI.lerposicaoxadrez(sc);
				
<<<<<<< HEAD
				PecaXadrez pecaCapturada  = partidaXadrez.moverPeca(origem, fifi);
				
				if(pecaCapturada != null) {
					capturadas.add(pecaCapturada);
=======
				PecaXadrez pecaCapturada   = partidaXadrez.moverPeca(origem, fifi);
				if (pecaCapturada != null) {
					captituradas.add(pecaCapturada);
>>>>>>> 5d5d5211041444121698aa46ab6eff74d2f5d711
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
