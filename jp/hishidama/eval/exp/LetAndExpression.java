package jp.hishidama.eval.exp;

/**
 * �r�b�g�_���ϑ�����Z�q�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public final class LetAndExpression extends BitAndExpression {

	public static final String NAME = "bitAndLet";

	public LetAndExpression() {
		setOperator("&=");
	}

	@Override
	public String getExpressionName() {
		return "bitAndLet";
	}

	protected LetAndExpression(LetAndExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new LetAndExpression(this, s);
	}

	@Override
	public Object eval() {
		Object val = super.eval();
		expl.let(val, pos);
		return val;
	}

	@Override
	protected AbstractExpression replace() {
		expl = expl.replaceVar();
		expr = expr.replace();
		return share.repl.replaceLet(this);
	}
}
