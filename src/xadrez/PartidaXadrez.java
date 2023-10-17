package xadrez;

import java.util.ArrayList;
import java.util.List;

import jogodetabuleiro.Peca;
import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	private int turno;
	private Cores jogadorAtual;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();


	public PartidaXadrez() {
		tabuleiro = new Tabuleiro (8, 8);
		turno = 1;
		jogadorAtual = Cores.WHITE;
		SetUpInicial();
		
		}
	
	public int getTurno() {
		return turno;
	}
	
	public Cores GetjogadorAtual() {
		return jogadorAtual;
	}
	
	
	
	public PecaXadrez[][] getPecaXadrez(){
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getRows()][tabuleiro.getColumns()];
		for (int i = 0 ; i<tabuleiro.getRows(); i++)
			 for ( int j = 0; j<tabuleiro.getColumns(); j++)
				 mat [i][j]= (PecaXadrez) tabuleiro.peca(i,j);
		
		return mat;
	}
	
	public boolean[][] movimentosPossiveis(Novaposicao origem){
		Posicao posicao = origem.Toposicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public PecaXadrez moverPeca (Novaposicao posicaoOrigem, Novaposicao posicaoFinal) {
		Posicao origem = posicaoOrigem.Toposicao();
		Posicao fifi = posicaoFinal.Toposicao();
		validarPosicaoOrigem (origem);
		validarPosicaoFinal(origem, fifi);
		Peca pecaCapturada = makePeca( origem , fifi);
		proximoTurno();	
		return  (PecaXadrez)pecaCapturada;
		
	}
	public Peca makePeca (Posicao origem, Posicao fifi) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(fifi);
		tabuleiro.LugaresPeca(p, fifi);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
			
		}
		return pecaCapturada;
		
		
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.TemUmaPeca(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posição de origem.");
		}
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getcores()){
			throw new ExcecaoXadrez("peca não é sua.");
		}
		if (!tabuleiro.peca(posicao).existeMovimento()) {
			throw new ExcecaoXadrez("Não existe movimentos possiveis.");
		}
	}
	
	private void validarPosicaoFinal(Posicao origem, Posicao fifi) {
		if (!tabuleiro.peca(origem).movimentosPossiveis(fifi)) {
			throw new ExcecaoXadrez("Peça escolhida não pode se mover para posição escolhida.");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual==Cores.WHITE) ? Cores.BLACK : Cores.WHITE;
	}
	public void Novaposicao( char coluna, int linha, PecaXadrez peca) {
		tabuleiro.LugaresPeca(peca, new Novaposicao(coluna, linha).Toposicao());
		pecasNoTabuleiro.add(peca);
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
