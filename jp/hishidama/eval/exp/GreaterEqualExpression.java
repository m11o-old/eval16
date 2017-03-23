package jp.hishidama.eval.exp;

/**
 * ��r���Z�i>=�j�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public class GreaterEqualExpression extends Col2Expression {

	public static final String NAME = "ge";

	public GreaterEqualExpression() {
		setOperator(">=");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected GreaterEqualExpression(GreaterEqualExpression from,
			ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new GreaterEqualExpression(this, s);
	}

	@Override
	protected Object operateObject(Object vl, Object vr) {
		return share.oper.greaterEqual(vl, vr);
	}
}
