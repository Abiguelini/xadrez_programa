package xadrez.pecas;
 


import jogodetabuleiro.Posicao;
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

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null  ||  p.getcores() != getcores();
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean [][] mat = new boolean [getTabuleiro().getRows()][getTabuleiro().getColumns()];
		
		Posicao p = new Posicao(0,0);
		
		//cima 
		p.darValores(posicao.getRow()-1, posicao.getCollumn());
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
			
		//baixo
				
		p.darValores(posicao.getRow()+1, posicao.getCollumn());
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
		
		//direita 
		p.darValores(posicao.getRow(), posicao.getCollumn()+1);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
					mat[p.getRow()][p.getCollumn()] = true;
		}
		
		//Esquerda 
		
		p.darValores(posicao.getRow(), posicao.getCollumn()-1);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}	
		
		//NO
		p.darValores(posicao.getRow()-1, posicao.getCollumn()-1);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
				
		//NE
		p.darValores(posicao.getRow()-1, posicao.getCollumn()+1);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
				
		//SO
				
		p.darValores(posicao.getRow()+1, posicao.getCollumn()-1);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
		//SE 
		p.darValores(posicao.getRow()+1, posicao.getCollumn()+1);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
		return mat;
	}
	

}
