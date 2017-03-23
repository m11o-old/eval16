package jp.hishidama.eval.exp;

/**
 * �����N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public class EqualExpression extends Col2Expression {

	public static final String NAME = "eq";

	public EqualExpression() {
		setOperator("==");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected EqualExpression(EqualExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new EqualExpression(this, s);
	}

	@Override
	protected Object operateObject(Object vl, Object vr) {
		return share.oper.equal(vl, vr);
	}
}
