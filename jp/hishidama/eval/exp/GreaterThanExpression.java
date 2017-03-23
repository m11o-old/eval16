package jp.hishidama.eval.exp;

/**
 * ��r���Z�i>�j�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public class GreaterThanExpression extends Col2Expression {

	public static final String NAME = "gt";

	public GreaterThanExpression() {
		setOperator(">");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected GreaterThanExpression(GreaterThanExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new GreaterThanExpression(this, s);
	}

	@Override
	protected Object operateObject(Object vl, Object vr) {
		return share.oper.greaterThan(vl, vr);
	}
}
