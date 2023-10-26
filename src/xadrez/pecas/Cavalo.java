package xadrez.pecas;
 


import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

	public Cavalo(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "C";
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null  ||  p.getcores() != getcores();
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean [][] mat = new boolean [getTabuleiro().getRows()][getTabuleiro().getColumns()];
		
		Posicao p = new Posicao(0,0);
		
		
		p.darValores(posicao.getRow()-1, posicao.getCollumn()-2);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
			
		
				
		p.darValores(posicao.getRow()-2, posicao.getCollumn()-1);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
		
	
		p.darValores(posicao.getRow()+1, posicao.getCollumn()-2);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
					mat[p.getRow()][p.getCollumn()] = true;
		}
		
	
		
		p.darValores(posicao.getRow()-2, posicao.getCollumn()+1);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}	
		
		
		p.darValores(posicao.getRow()-1, posicao.getCollumn()+2);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
				
		
		p.darValores(posicao.getRow()+2, posicao.getCollumn()-1);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
				
				
		p.darValores(posicao.getRow()+2, posicao.getCollumn()+1);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
	
		p.darValores(posicao.getRow()+1, posicao.getCollumn()+2);
		if(getTabuleiro().PosicaoExiste(p)&& podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
		return mat;
	}
	

}
