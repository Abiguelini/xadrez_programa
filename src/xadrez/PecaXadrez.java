package xadrez;
import jogodetabuleiro.Peca;
import jogodetabuleiro.Tabuleiro;

public abstract class PecaXadrez  extends Peca{
	protected Cores cores;

	public PecaXadrez(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro);
		this.cores = cores;
	}

	public Cores getcores() {
		return cores;
	}
}
