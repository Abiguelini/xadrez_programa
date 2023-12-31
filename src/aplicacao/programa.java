package aplicacao;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jogodetabuleiro.ExcecaoJogo;

import xadrez.Novaposicao;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class programa {

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		Scanner sc = new Scanner(System.in);
		List<PecaXadrez>capturadas = new ArrayList<>();
		
		
		while (!partidaXadrez.getCheckMate() ) {
			
			try {
				UI.clearScreen();
				UI.printGame(partidaXadrez, capturadas);
				System.out.println();
				System.out.print("Origem: ");
				Novaposicao origem = UI.lerposicaoxadrez(sc);
				
				boolean [][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.getPecaXadrez(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("Destido: ");
				Novaposicao fifi = UI.lerposicaoxadrez(sc);
				
				PecaXadrez pecaCapturada  = partidaXadrez.moverPeca(origem, fifi);
				
				if(pecaCapturada != null) {
					capturadas.add(pecaCapturada);
				}
				
				if(partidaXadrez.getPromovido()!=null) {
					System.out.println("digite Q/B/C/T para promoção: ");
					String tipo = sc.nextLine();
					partidaXadrez.reporPecaPromovida(tipo);
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
		UI.clearScreen();
		UI.printGame(partidaXadrez, capturadas);
	}

}