package xadrez.pecas;

import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Cores cores, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cores);
		this.partidaXadrez = partidaXadrez;

	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p == null || p.getcores() != getcores();
	}

	private boolean testeRoque(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p != null && p instanceof Torre && p.getcores() == getcores() && p.getContadorMov() == 0;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];

		Posicao p = new Posicao(0, 0);

		// cima
		p.darValores(posicao.getRow() - 1, posicao.getCollumn());
		if (getTabuleiro().PosicaoExiste(p) && podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// baixo

		p.darValores(posicao.getRow() + 1, posicao.getCollumn());
		if (getTabuleiro().PosicaoExiste(p) && podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// direita
		p.darValores(posicao.getRow(), posicao.getCollumn() + 1);
		if (getTabuleiro().PosicaoExiste(p) && podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// Esquerda

		p.darValores(posicao.getRow(), posicao.getCollumn() - 1);
		if (getTabuleiro().PosicaoExiste(p) && podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// NO
		p.darValores(posicao.getRow() - 1, posicao.getCollumn() - 1);
		if (getTabuleiro().PosicaoExiste(p) && podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// NE
		p.darValores(posicao.getRow() - 1, posicao.getCollumn() + 1);
		if (getTabuleiro().PosicaoExiste(p) && podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// SO

		p.darValores(posicao.getRow() + 1, posicao.getCollumn() - 1);
		if (getTabuleiro().PosicaoExiste(p) && podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
		// SE
		p.darValores(posicao.getRow() + 1, posicao.getCollumn() + 1);
		if (getTabuleiro().PosicaoExiste(p) && podeMover(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// MovimentoRoque

		if (!partidaXadrez.getCheck() && getContadorMov() == 0) {
			// roquePequeno
			Posicao teste1 = new Posicao(posicao.getRow(), posicao.getCollumn() + 3);
			if (testeRoque(teste1)) {
				Posicao p1 = new Posicao(posicao.getRow(), posicao.getCollumn() + 1);
				Posicao p2 = new Posicao(posicao.getRow(), posicao.getCollumn() + 2);
				if (getTabuleiro().peca(p1) == null & getTabuleiro().peca(p2) == null) {
					mat[posicao.getRow()][posicao.getCollumn() + 2] = true;
				}
			}
			Posicao teste2 = new Posicao(posicao.getRow(), posicao.getCollumn() - 4);
			if (testeRoque(teste2)) {
				Posicao p1 = new Posicao(posicao.getRow(), posicao.getCollumn() - 1);
				Posicao p2 = new Posicao(posicao.getRow(), posicao.getCollumn() - 2);
				Posicao p3 = new Posicao(posicao.getRow(), posicao.getCollumn() - 3);
				if (getTabuleiro().peca(p1) == null & getTabuleiro().peca(p2) == null
						&& getTabuleiro().peca(p3) == null) {
					mat[posicao.getRow()][posicao.getCollumn() - 2] = true;
				}
			}
		}

		return mat;
	}

}
