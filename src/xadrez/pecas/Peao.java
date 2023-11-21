package xadrez.pecas;

import jogodetabuleiro.Peca;
import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {
	
	
	private PartidaXadrez partidaXadrez;
	public Peao(Tabuleiro tabuleiro, Cores cores, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cores);
		this.partidaXadrez = partidaXadrez;
		
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
			
			//enPassant
			
			if(posicao.getRow()== 3) {
				Posicao esquerda = new Posicao(posicao.getRow(), posicao.getCollumn()-1);
				if(getTabuleiro().PosicaoExiste(esquerda)&& pecaOponente(esquerda)&&getTabuleiro().peca(esquerda)== partidaXadrez.getEnPassantVulneravel()) {
					mat[esquerda.getRow()-1][esquerda.getCollumn()]=true;
				}
				Posicao direita = new Posicao(posicao.getRow(), posicao.getCollumn()+1);
				if(getTabuleiro().PosicaoExiste(direita)&& pecaOponente(direita)&&getTabuleiro().peca(direita)== partidaXadrez.getEnPassantVulneravel()) {
					mat[direita.getRow()-1][direita.getCollumn()]=true;
				}
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
			
			
			if(posicao.getRow() == 4) {
				Posicao esquerda = new Posicao(posicao.getRow(), posicao.getCollumn()-1);
				if(getTabuleiro().PosicaoExiste(esquerda)&& pecaOponente(esquerda)&&getTabuleiro().peca(esquerda)== partidaXadrez.getEnPassantVulneravel()) {
					mat[esquerda.getRow()+1][esquerda.getCollumn()]=true;
				}
				Posicao direita = new Posicao(posicao.getRow(), posicao.getCollumn()+1);
				if(getTabuleiro().PosicaoExiste(direita)&& pecaOponente(direita)&&getTabuleiro().peca(direita)== partidaXadrez.getEnPassantVulneravel()) {
					mat[direita.getRow()+1][direita.getCollumn()]=true;
				}
			}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}