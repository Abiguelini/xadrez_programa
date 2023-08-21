package xadrez.pecas;
 


import jogodetabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	public Rei(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean [][] mat = new boolean [getTabuleiro().getRows()][getTabuleiro().getColumns()];
		return mat;
	}
	

}
