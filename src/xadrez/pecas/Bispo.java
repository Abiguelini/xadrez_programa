package xadrez.pecas;


import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaXadrez;


public class Bispo extends PecaXadrez {

	public Bispo(Tabuleiro tabuleiro, Cores cor) {
		super(tabuleiro, cor);	
	}
	
	@Override
	public String toString() {
		return "B";
	}

	@Override
	
	public boolean[][] movimentosPossiveis() {
		boolean [][] mat = new boolean [getTabuleiro().getRows()][getTabuleiro().getColumns()];
		
		Posicao p = new Posicao (0, 0);
		
		// NO
		
		p.darValores(posicao.getRow() - 1, posicao.getCollumn()-1);
		while (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat [p.getRow()][p.getCollumn()] = true;
			p.darValores(p.getRow() - 1, p.getCollumn()-1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)){
			mat[p.getRow()][p.getCollumn()] = true;
		}
		
		// ND
		
		p.darValores(posicao.getRow() -1 , posicao.getCollumn() +1 );
		while (getTabuleiro().PosicaoExiste(p)&& !getTabuleiro().TemUmaPeca(p)) {
			mat [p.getRow()][p.getCollumn()]= true;
			p.darValores(p.getRow() - 1, p.getCollumn()+1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)){
			mat[p.getRow()][p.getCollumn()] = true;
		}
		
		// SD
		
		p.darValores(posicao.getRow()+1 , posicao.getCollumn() +1 );
		while (getTabuleiro().PosicaoExiste(p)&& !getTabuleiro().TemUmaPeca(p)) {
			mat [p.getRow()][p.getCollumn()]= true;
			p.darValores(p.getRow() + 1, p.getCollumn()+ 1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)){
			mat[p.getRow()][p.getCollumn()] = true;
		}
		// SO
		
		p.darValores(posicao.getRow() + 1, posicao.getCollumn()-1);
		while (getTabuleiro().PosicaoExiste(p)&& !getTabuleiro().TemUmaPeca(p)) {
			mat [p.getRow()][p.getCollumn()]= true;
			p.darValores(p.getRow() + 1, p.getCollumn()-1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)){
			mat[p.getRow()][p.getCollumn()] = true;
				}
				
				
		return mat;
	}
		

}
