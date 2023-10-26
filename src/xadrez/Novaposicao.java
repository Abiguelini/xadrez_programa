package xadrez;

import jogodetabuleiro.Posicao;

public class Novaposicao {
	private char coluna;
	private int linha;

	public Novaposicao(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ExcecaoXadrez("valores invalidos.");
		}
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public char getColuna() {
		return coluna;
	}

	protected Posicao Toposicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}

	protected static Novaposicao FromPosition(Posicao posicao) {
		return new Novaposicao((char) ('a' + posicao.getCollumn()), (8 - posicao.getRow()));
	}

	@Override
	public String toString() {
		return "" + coluna + linha;
	}

}
