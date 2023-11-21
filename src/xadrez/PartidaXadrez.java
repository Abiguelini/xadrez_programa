package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogodetabuleiro.Peca;
import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	private int turno;
	private Cores jogadorAtual;
	private boolean check;
	private boolean checkMate;
	private PecaXadrez enPassantVulneravel;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cores.WHITE;
		SetUpInicial();

	}

	public int getTurno() {
		return turno;
	}

	public PecaXadrez getEnPassantVulneravel() {
		return enPassantVulneravel;
	}

	public Cores GetjogadorAtual() {
		return jogadorAtual;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}

	public PecaXadrez[][] getPecaXadrez() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getRows()][tabuleiro.getColumns()];
		for (int i = 0; i < tabuleiro.getRows(); i++)
			for (int j = 0; j < tabuleiro.getColumns(); j++)
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);

		return mat;
	}

	public boolean[][] movimentosPossiveis(Novaposicao origem) {
		Posicao posicao = origem.Toposicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	public PecaXadrez moverPeca(Novaposicao posicaoOrigem, Novaposicao posicaoFinal) {
		Posicao origem = posicaoOrigem.Toposicao();
		Posicao fifi = posicaoFinal.Toposicao();
		validarPosicaoOrigem(origem);
		validarPosicaoFinal(origem, fifi);
		Peca pecaCapturada = makePeca(origem, fifi);

		if (testeCheck(jogadorAtual)) {
			DesfazerMovimento(origem, fifi, pecaCapturada);
			throw new ExcecaoXadrez("Você não pode se colocar em check");
		}

		check = (testeCheck(oponente(jogadorAtual))) ? true : false;

		PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.peca(fifi);

		if (testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		} else {
			proximoTurno();
		}

		if (pecaMovida instanceof Peao
				&& (fifi.getRow() == origem.getRow() - 2 || fifi.getRow() == origem.getRow() + 2)) {
			enPassantVulneravel = pecaMovida;
		} else {
			enPassantVulneravel = null;
		}

		return (PecaXadrez) pecaCapturada;

	}

	private Peca makePeca(Posicao origem, Posicao fifi) {

		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
		p.aumentoMov();
		Peca pecaCapturada = tabuleiro.removerPeca(fifi);
		tabuleiro.LugaresPeca(p, fifi);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);

		}

		// Movimento especial roque direita

		if (p instanceof Rei && fifi.getCollumn() == origem.getCollumn() + 2) {
			Posicao origemTorre = new Posicao(origem.getRow(), origem.getCollumn() + 3);
			Posicao finalTorre = new Posicao(origem.getRow(), origem.getCollumn() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
			tabuleiro.LugaresPeca(torre, finalTorre);
			torre.aumentoMov();

		}

		// Movimento especial roque esquerda

		if (p instanceof Rei && fifi.getCollumn() == origem.getCollumn() - 2) {
			Posicao origemTorre = new Posicao(origem.getRow(), origem.getCollumn() - 4);
			Posicao finalTorre = new Posicao(origem.getRow(), origem.getCollumn() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
			tabuleiro.LugaresPeca(torre, finalTorre);
			torre.aumentoMov();

		}

		// enPassant

		if (p instanceof Peao) {
			if (origem.getCollumn() != fifi.getCollumn() && pecaCapturada == null) {
				Posicao peaoPosicao;
				if (p.getcores() == Cores.WHITE) {
					peaoPosicao = new Posicao(fifi.getRow() + 1, fifi.getCollumn());
				} else {
					peaoPosicao = new Posicao(fifi.getRow() - 1, fifi.getCollumn());
				}
				pecaCapturada = tabuleiro.removerPeca(peaoPosicao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}

		return pecaCapturada;

	}

	private void DesfazerMovimento(Posicao origem, Posicao fifi, Peca pecaCapturada) {

		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(fifi);
		p.menosMov();
		tabuleiro.LugaresPeca(p, origem);

		if (pecaCapturada != null) {
			tabuleiro.LugaresPeca(pecaCapturada, fifi);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}

		// Movimento especial roque direita

		if (p instanceof Rei && fifi.getCollumn() == origem.getCollumn() + 2) {
			Posicao origemTorre = new Posicao(origem.getRow(), origem.getCollumn() + 3);
			Posicao finalTorre = new Posicao(origem.getRow(), origem.getCollumn() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(finalTorre);
			tabuleiro.LugaresPeca(torre, origemTorre);
			torre.menosMov();

		}

		// Movimento especial roque esquerda

		if (p instanceof Rei && fifi.getCollumn() == origem.getCollumn() - 2) {
			Posicao origemTorre = new Posicao(origem.getRow(), origem.getCollumn() - 4);
			Posicao finalTorre = new Posicao(origem.getRow(), origem.getCollumn() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(finalTorre);
			tabuleiro.LugaresPeca(torre, origemTorre);
			torre.menosMov();

		}
		
		if (p instanceof Peao) {
			if (origem.getCollumn() != fifi.getCollumn() && pecaCapturada == enPassantVulneravel) {
				PecaXadrez peao = (PecaXadrez)tabuleiro.removerPeca(fifi);
				Posicao peaoPosicao;
				if (p.getcores() == Cores.WHITE) {
					peaoPosicao = new Posicao(3, origem.getCollumn());
				} else {
					peaoPosicao = new Posicao(4, origem.getCollumn());
				}
				tabuleiro.LugaresPeca(peao, peaoPosicao);
			}
		}

	}

	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.TemUmaPeca(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posição de origem.");
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getcores()) {
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
		jogadorAtual = (jogadorAtual == Cores.WHITE) ? Cores.BLACK : Cores.WHITE;
	}

	private Cores oponente(Cores cores) {
		return (cores == Cores.WHITE) ? Cores.BLACK : Cores.WHITE;
	}

	private PecaXadrez rei(Cores cores) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getcores() == cores)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Não existe " + cores + "rei no tabuleiro");
	}

	private void Novaposicao(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.LugaresPeca(peca, new Novaposicao(coluna, linha).Toposicao());
		pecasNoTabuleiro.add(peca);
	}

	private boolean testeCheck(Cores cores) {
		Posicao posicaoRei = rei(cores).getNovaposicao().Toposicao();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getcores() == oponente(cores))
				.collect(Collectors.toList());
		for (Peca p : pecasOponente) {
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaoRei.getRow()][posicaoRei.getCollumn()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testeCheckMate(Cores cores) {
		if (!testeCheck(cores)) {
			return false;
		}
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getcores() == cores)
				.collect(Collectors.toList());
		for (Peca p : lista) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getRows(); i++) {
				for (int j = 0; j < tabuleiro.getColumns(); j++) {
					if (mat[i][j]) {

						Posicao origem = ((PecaXadrez) p).getNovaposicao().Toposicao();
						Posicao fifi = new Posicao(i, j);
						Peca pecaCapturada = makePeca(origem, fifi);
						boolean testeCheck = testeCheck(cores);
						DesfazerMovimento(origem, fifi, pecaCapturada);
						if (!testeCheck) {
							return false;
						}

					}
				}
			}
		}
		return true;
	}

	public void SetUpInicial() {
		Novaposicao('a', 1, new Torre(tabuleiro, Cores.WHITE));
		Novaposicao('h', 1, new Torre(tabuleiro, Cores.WHITE));
		Novaposicao('e', 1, new Rei(tabuleiro, Cores.WHITE, this));
		Novaposicao('a', 2, new Peao(tabuleiro, Cores.WHITE, this));
		Novaposicao('b', 2, new Peao(tabuleiro, Cores.WHITE, this));
		Novaposicao('c', 2, new Peao(tabuleiro, Cores.WHITE, this));
		Novaposicao('d', 2, new Peao(tabuleiro, Cores.WHITE, this));
		Novaposicao('e', 2, new Peao(tabuleiro, Cores.WHITE, this));
		Novaposicao('f', 2, new Peao(tabuleiro, Cores.WHITE, this));
		Novaposicao('g', 2, new Peao(tabuleiro, Cores.WHITE, this));
		Novaposicao('h', 2, new Peao(tabuleiro, Cores.WHITE, this));
		Novaposicao('c', 1, new Bispo(tabuleiro, Cores.WHITE));
		Novaposicao('f', 1, new Bispo(tabuleiro, Cores.WHITE));
		Novaposicao('b', 1, new Cavalo(tabuleiro, Cores.WHITE));
		Novaposicao('g', 1, new Cavalo(tabuleiro, Cores.WHITE));
		Novaposicao('d', 1, new Rainha(tabuleiro, Cores.WHITE));

		Novaposicao('d', 8, new Rainha(tabuleiro, Cores.BLACK));
		Novaposicao('b', 8, new Cavalo(tabuleiro, Cores.BLACK));
		Novaposicao('g', 8, new Cavalo(tabuleiro, Cores.BLACK));
		Novaposicao('c', 8, new Bispo(tabuleiro, Cores.BLACK));
		Novaposicao('f', 8, new Bispo(tabuleiro, Cores.BLACK));
		Novaposicao('a', 8, new Torre(tabuleiro, Cores.BLACK));
		Novaposicao('h', 8, new Torre(tabuleiro, Cores.BLACK));
		Novaposicao('e', 8, new Rei(tabuleiro, Cores.BLACK, this));
		Novaposicao('a', 7, new Peao(tabuleiro, Cores.BLACK, this));
		Novaposicao('b', 7, new Peao(tabuleiro, Cores.BLACK, this));
		Novaposicao('c', 7, new Peao(tabuleiro, Cores.BLACK, this));
		Novaposicao('d', 7, new Peao(tabuleiro, Cores.BLACK, this));
		Novaposicao('e', 7, new Peao(tabuleiro, Cores.BLACK, this));
		Novaposicao('f', 7, new Peao(tabuleiro, Cores.BLACK, this));
		Novaposicao('g', 7, new Peao(tabuleiro, Cores.BLACK, this));
		Novaposicao('h', 7, new Peao(tabuleiro, Cores.BLACK, this));
	}

}