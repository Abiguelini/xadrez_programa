package xadrez;

import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro (8, 8);
		SetUpInicial();
		}
	public PecaXadrez[][] getPecaXadrez(){
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getRows()][tabuleiro.getColumns()];
		for (int i = 0 ; i<tabuleiro.getRows(); i++)
			 for ( int j = 0; j<tabuleiro.getColumns(); j++)
				 mat [i][j]= (PecaXadrez) tabuleiro.peca(i,j);
		
		return mat;
	}
	
	public void Novaposicao( char coluna, int linha, PecaXadrez peca) {
		tabuleiro.LugaresPeca(peca, new Novaposicao(coluna, linha).Toposicao());
	}
	
	public void SetUpInicial() {
		Novaposicao('a', 2, new Torre(tabuleiro,Cores.BLACK));
		Novaposicao('h', 5, new Torre(tabuleiro,Cores.BLACK));
	}

}
