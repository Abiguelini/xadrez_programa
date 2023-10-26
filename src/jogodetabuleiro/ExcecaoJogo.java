package jogodetabuleiro;

public class ExcecaoJogo extends RuntimeException {


	private static final long serialVersionUID = 1L;
	public ExcecaoJogo (String msg) {
		super(msg);
	}
}