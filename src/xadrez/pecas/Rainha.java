package xadrez.pecas;

import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Q";
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p == null || p.getcores() != getcores();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];

		Posicao p = new Posicao(0, 0);

		// Cima

		p.darValores(posicao.getRow() - 1, posicao.getCollumn());
		while (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// Esquerda

		p.darValores(posicao.getRow(), posicao.getCollumn() - 1);
		while (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
			p.setCollumn(p.getCollumn() - 1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// direita

		p.darValores(posicao.getRow(), posicao.getCollumn() + 1);
		while (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
			p.setCollumn(p.getCollumn() + 1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
		// baixo

		p.darValores(posicao.getRow() + 1, posicao.getCollumn());
		while (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// NO

		p.darValores(posicao.getRow() - 1, posicao.getCollumn() - 1);
		while (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
			p.darValores(p.getRow() - 1, p.getCollumn() - 1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// ND

		p.darValores(posicao.getRow() - 1, posicao.getCollumn() + 1);
		while (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
			p.darValores(p.getRow() - 1, p.getCollumn() + 1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// SD

		p.darValores(posicao.getRow() + 1, posicao.getCollumn() + 1);
		while (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
			p.darValores(p.getRow() + 1, p.getCollumn() + 1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
		// SO

		p.darValores(posicao.getRow() + 1, posicao.getCollumn() - 1);
		while (getTabuleiro().PosicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
			p.darValores(p.getRow() + 1, p.getCollumn() - 1);
		}
		if (getTabuleiro().PosicaoExiste(p) && pecaOponente(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}
		return mat;
	}

}
