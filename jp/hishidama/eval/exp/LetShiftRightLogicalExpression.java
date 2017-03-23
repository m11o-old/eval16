package jp.hishidama.eval.exp;

/**
 * �E�V�t�g�i�����Ȃ��j������Z�q�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public class LetShiftRightLogicalExpression extends ShiftRightLogicalExpression {

	public static final String NAME = "srlLet";

	public LetShiftRightLogicalExpression() {
		setOperator(">>>=");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected LetShiftRightLogicalExpression(
			LetShiftRightLogicalExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new LetShiftRightLogicalExpression(this, s);
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
