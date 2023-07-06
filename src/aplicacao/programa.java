package aplicacao;

import jogodetabuleiro.*;
import xadrez.*;
public class Programa {

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		UI.printTabuleiro(partidaXadrez.getPecaXadrez());
	}

}
