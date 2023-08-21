package xadrez;

import jogodetabuleiro.Peca;
import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
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
	
	public PecaXadrez moverPeca (Novaposicao posicaoOrigem, Novaposicao posicaoFinal) {
		Posicao origem = posicaoOrigem.Toposicao();
		Posicao fifi = posicaoFinal.Toposicao();
		validarPosicaoOrigem (origem);
		Peca pecaCapturada = makePeca( origem , fifi);
		return  (PecaXadrez)pecaCapturada;
		
	}
	public Peca makePeca (Posicao origem, Posicao fifi) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(fifi);
		tabuleiro.LugaresPeca(p, fifi);
		return pecaCapturada;
		
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.PosicaoExiste(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posição de origem");
		}
		if (!tabuleiro.peca(posicao).existeMovimento()) {
			throw new ExcecaoXadrez("Não existe movimentos possiveis.");
		}
	}
	
	public void Novaposicao( char coluna, int linha, PecaXadrez peca) {
		tabuleiro.LugaresPeca(peca, new Novaposicao(coluna, linha).Toposicao());
	}
	
	public void SetUpInicial() {
		Novaposicao('c', 1, new Torre(tabuleiro, Cores.WHITE));
		Novaposicao('c', 2, new Torre(tabuleiro, Cores.WHITE));
		Novaposicao('d', 2, new Torre(tabuleiro, Cores.WHITE));
		Novaposicao('e', 2, new Torre(tabuleiro, Cores.WHITE));
		Novaposicao('e', 1, new Torre(tabuleiro, Cores.WHITE));
		Novaposicao('d', 1, new Rei(tabuleiro, Cores.WHITE));

		Novaposicao('c', 7, new Torre(tabuleiro, Cores.BLACK));
		Novaposicao('c', 8, new Torre(tabuleiro, Cores.BLACK));
		Novaposicao('d', 7, new Torre(tabuleiro, Cores.BLACK));
		Novaposicao('e', 7, new Torre(tabuleiro, Cores.BLACK));
		Novaposicao('e', 8, new Torre(tabuleiro, Cores.BLACK));
		Novaposicao('d', 8, new Rei(tabuleiro, Cores.BLACK));
	}

}
