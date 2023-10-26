package xadrez;
import jogodetabuleiro.Peca;
import jogodetabuleiro.Posicao;
import jogodetabuleiro.Tabuleiro;

public abstract class PecaXadrez  extends Peca{
	protected Cores cores;
	private int contadorMov;

	public PecaXadrez(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro);
		this.cores = cores;
	}

	public Cores getcores() {
		return cores;
	}
	public int getContadorMov() {
		return contadorMov;
	}
	public void aumentoMov() {
		contadorMov++;
	}
	public void menosMov() {
		contadorMov--;
	}
	
	protected boolean pecaOponente(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getcores() != cores;
	}
	
	public Novaposicao getNovaposicao() {
		return Novaposicao.FromPosition(posicao);
	}
}
