package jogodetabuleiro;

public class Tabuleiro {
	private int rows;
	private int columns;
	private Peca[][] pecas;

	public Tabuleiro(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new ExcecaoJogo("Tem que ter ao menos 1 liha e uma coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pecas = new Peca[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Peca peca(int row, int columns) {
		if (!PosicaoExiste(row, columns)) {
			throw new ExcecaoJogo("Posição não está no tabuleiro.");
		}
		return pecas[row][columns];
	}

	public Peca peca(Posicao posicao) {
		if (!PosicaoExiste(posicao)) {
			throw new ExcecaoJogo("Posição não está no tabuleiro.");
		}
		return pecas[posicao.getRow()][posicao.getCollumn()];
	}

	public void LugaresPeca(Peca peca, Posicao posicao) {
		if (TemUmaPeca(posicao)) {
			throw new ExcecaoJogo("Já existe outra peça aqui." + posicao);

		}
		pecas[posicao.getRow()][posicao.getCollumn()] = peca;
		peca.posicao = posicao;
	}

	public Peca removerPeca(Posicao posicao) {
		if (!PosicaoExiste(posicao)) {
			throw new ExcecaoJogo("Posição não está no tabuleiro.");
		}
		if (peca(posicao) == null) {
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getRow()][posicao.getCollumn()] = null;
		return aux;
	}

	private boolean PosicaoExiste(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean PosicaoExiste(Posicao posicao) {
		return PosicaoExiste(posicao.getRow(), posicao.getCollumn());
	}

	public boolean TemUmaPeca(Posicao posicao) {
		if (!PosicaoExiste(posicao)) {
			throw new ExcecaoJogo("Posição não está no tabuleiro.");
		}
		return peca(posicao) != null;
	}
}
