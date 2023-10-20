package xadrez;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import jogodetabuleiro.Peca;
import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	private int turno;
	private Cores jogadorAtual;
	private boolean check;
	private boolean checkMate;
	
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
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate(){
		return checkMate;
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
		
		if (testeCheck(jogadorAtual)) {
			DesfazerMovimento(origem, fifi, pecaCapturada);
			throw new ExcecaoXadrez("Você não pode se colocar em check");
		}
		
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		if(testeCheckMate(oponente(jogadorAtual))){
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		return  (PecaXadrez)pecaCapturada;
		
	}
	private Peca makePeca (Posicao origem, Posicao fifi) {
		PecaXadrez	p = (PecaXadrez)tabuleiro.removerPeca(origem);
		p.aumentoContador();
		Peca pecaCapturada = tabuleiro.removerPeca(fifi);
		tabuleiro.LugaresPeca(p, fifi);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
			
		}
		return pecaCapturada;
		
		
	}
	
	
	private void DesfazerMovimento(Posicao origem, Posicao fifi, Peca pecaCapturada ) {
		
		PecaXadrez p =	(PecaXadrez)tabuleiro.removerPeca(fifi);
		p.menosContador();
		tabuleiro.LugaresPeca(p, origem);
		
		if (pecaCapturada != null) {
			tabuleiro.LugaresPeca(pecaCapturada, fifi);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
	}
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
	
	private Cores oponente(Cores cores) {
		return (cores == Cores.WHITE)? Cores.BLACK : Cores.WHITE;
	}
	
	private PecaXadrez rei(Cores cores) {
		List <Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getcores()==cores).collect(Collectors.toList());
		for(Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe "+ cores + "rei no tabuleiro");
	}
	private void Novaposicao( char coluna, int linha, PecaXadrez peca) {
		tabuleiro.LugaresPeca(peca, new Novaposicao(coluna, linha).Toposicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private boolean testeCheck(Cores cores) {
		Posicao posicaoRei = rei(cores).getNovaposicao().Toposicao();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getcores()==oponente(cores)).collect(Collectors.toList());
		for ( Peca p : pecasOponente) {
			boolean [][] matriz = p.movimentosPossiveis();
			if(matriz[posicaoRei.getRow()][posicaoRei.getCollumn()]) {
				return true;
			}
		}
			return false;
	}
	
	private boolean testeCheckMate(Cores cores) {
		if (!testeCheck(cores)) {
			return false;
		}
		List <Peca> lista =  pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getcores()==cores).collect(Collectors.toList());
		for (Peca p : lista) {
			boolean [][] matriz = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getColumns(); i++) {
				for (int j=0; j>tabuleiro.getRows(); j++) {
					if(matriz[i][j]) {
						Posicao origem = ((PecaXadrez)p).getNovaposicao().Toposicao();
						Posicao fifi = new Posicao(i, j);
						Peca pecaCapturada = makePeca(origem, fifi);
						DesfazerMovimento(origem, fifi, pecaCapturada);
						if(!testeCheck(cores));
					}
				}
			}
		}
		return true;
	}
	
	
	public void SetUpInicial() {
		
		Novaposicao('a', 1, new Torre(tabuleiro, Cores.WHITE));
		Novaposicao('c', 1, new Bispo(tabuleiro, Cores.WHITE));
		Novaposicao('f', 1, new Bispo(tabuleiro, Cores.WHITE));
		Novaposicao('h', 1, new Torre(tabuleiro, Cores.WHITE));
		Novaposicao('e', 1, new Rei(tabuleiro, Cores.WHITE));
		Novaposicao('a', 2, new Peao(tabuleiro, Cores.WHITE));
		Novaposicao('b', 2, new Peao(tabuleiro, Cores.WHITE));
		Novaposicao('c', 2, new Peao(tabuleiro, Cores.WHITE));
		Novaposicao('d', 2, new Peao(tabuleiro, Cores.WHITE));
		Novaposicao('e', 2, new Peao(tabuleiro, Cores.WHITE));
		Novaposicao('f', 2, new Peao(tabuleiro, Cores.WHITE));
		Novaposicao('g', 2, new Peao(tabuleiro, Cores.WHITE));
		Novaposicao('h', 2, new Peao(tabuleiro, Cores.WHITE));
		

		Novaposicao('c', 8, new Bispo(tabuleiro, Cores.BLACK));
		Novaposicao('f', 8, new Bispo(tabuleiro, Cores.BLACK));
		Novaposicao('a', 8, new Torre(tabuleiro, Cores.BLACK));
		Novaposicao('e', 8, new Torre(tabuleiro, Cores.BLACK));
		Novaposicao('h', 8, new Rei(tabuleiro, Cores.BLACK));
		Novaposicao('a', 7, new Peao(tabuleiro, Cores.BLACK));
		Novaposicao('b', 7, new Peao(tabuleiro, Cores.BLACK));
		Novaposicao('c', 7, new Peao(tabuleiro, Cores.BLACK));
		Novaposicao('d', 7, new Peao(tabuleiro, Cores.BLACK));
		Novaposicao('e', 7, new Peao(tabuleiro, Cores.BLACK));
		Novaposicao('f', 7, new Peao(tabuleiro, Cores.BLACK));
		Novaposicao('g', 7, new Peao(tabuleiro, Cores.BLACK));
		Novaposicao('h', 7, new Peao(tabuleiro, Cores.BLACK));
	}

}
