package jp.hishidama.eval.exp;

/**
 * �r�b�g�_���a������Z�q�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public class LetOrExpression extends BitOrExpression {

	public static final String NAME = "bitOrLet";

	public LetOrExpression() {
		setOperator("|=");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected LetOrExpression(LetOrExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new LetOrExpression(this, s);
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
