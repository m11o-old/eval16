package jp.hishidama.eval.exp;

/**
 * ���V�t�g������Z�q�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public final class LetShiftLeftExpression extends ShiftLeftExpression {

	public static final String NAME = "slaLet";

	public LetShiftLeftExpression() {
		setOperator("<<=");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected LetShiftLeftExpression(LetShiftLeftExpression from,
			ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new LetShiftLeftExpression(this, s);
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
