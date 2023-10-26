package xadrez.pecas;

import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	public Peao(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
		
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		
		boolean  [][] mat = new boolean [getTabuleiro().getColumns()][getTabuleiro().getRows()];
		
		Posicao p = new Posicao (0,0);
		
		if (getcores() == cores.WHITE) {
			p.darValores(posicao.getRow()-1, posicao.getCollumn());
			if (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
				mat [p.getRow()][p.getCollumn()] = true;
			}
			Posicao p2 = new Posicao (posicao.getRow()-2 , posicao.getCollumn());
			p.darValores(posicao.getRow()-2, posicao.getCollumn());
			if (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)&& getTabuleiro().PosicaoExiste(p2) && !getTabuleiro().TemUmaPeca(p2) && getContadorMov()==0) {
				mat [p.getRow()][p.getCollumn()] = true;
			}
			p.darValores(posicao.getRow()-1, posicao.getCollumn()-1);
			if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
				mat [p.getRow()][p.getCollumn()] = true;
			}
			p.darValores(posicao.getRow()-1, posicao.getCollumn()+1);
			if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
				mat [p.getRow()][p.getCollumn()] = true;
			}
			
		}
		else {
			p.darValores(posicao.getRow()+1, posicao.getCollumn());
			if (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
				mat [p.getRow()][p.getCollumn()] = true;
			}
			Posicao p2 = new Posicao (posicao.getRow()+2 , posicao.getCollumn());
			p.darValores(posicao.getRow()+2, posicao.getCollumn());
			if (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)&& getTabuleiro().PosicaoExiste(p2) && !getTabuleiro().TemUmaPeca(p2) && getContadorMov()==0) {
				mat [p.getRow()][p.getCollumn()] = true;
			}
			p.darValores(posicao.getRow()+1, posicao.getCollumn()-1);
			if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
				mat [p.getRow()][p.getCollumn()] = true;
			}
			p.darValores(posicao.getRow()+1, posicao.getCollumn()+1);
			if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
				mat [p.getRow()][p.getCollumn()] = true;
			}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}