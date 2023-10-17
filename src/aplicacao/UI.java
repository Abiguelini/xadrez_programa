package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import xadrez.Cores;
import xadrez.Novaposicao;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class UI {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public  static Novaposicao lerposicaoxadrez(Scanner sc) {
		try {
				String s = sc.nextLine();
				char coluna = s.charAt(0);
				int linha = Integer.parseInt(s.substring(1));
				return new Novaposicao (coluna, linha);
		}
		catch(RuntimeException e){
				throw new InputMismatchException("Valores errados. ");
		}
	}
	
	
		// https://stackoverflow.com/questions/2979383/java-clear-the-console
		public static void clearScreen() {
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
	
	
		
		public static void printGame (PartidaXadrez partidaXadrez, List<PecaXadrez>capturadas){
			printTabuleiro(partidaXadrez.getPecaXadrez());
			System.out.println();
			Printpecascapturadas(capturadas);
			System.out.println();
			System.out.println("Turno: " + partidaXadrez.getTurno());
			System.out.println("Esperando jogador... " + partidaXadrez.GetjogadorAtual());
		}
		
		public static void printTabuleiro (PecaXadrez [][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.print("  A B C D E F G H");
	}
	
	public static void printTabuleiro (PecaXadrez [][] pecas, boolean [][] movimentosPossiveis) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.print("  A B C D E F G H");
	}
	
	
	private static void printPeca (PecaXadrez peca, boolean background) {
		if (background) {
			System.out.print(ANSI_PURPLE_BACKGROUND);
		}
		
		if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getcores() == Cores.WHITE) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void Printpecascapturadas(List<PecaXadrez> capturadas) {
		
		List<PecaXadrez> branca = capturadas.stream().filter(x -> x.getcores()==Cores.WHITE).collect(Collectors.toList());
		List<PecaXadrez> preta = capturadas.stream().filter(x -> x.getcores()==Cores.BLACK).collect(Collectors.toList());
		System.out.println("Peças capturadas: ");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(branca.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print(ANSI_YELLOW);
		System.out.print("Pretas: ");
		System.out.println(Arrays.toString(preta.toArray()));
		System.out.print(ANSI_RESET);


	}
	
}
