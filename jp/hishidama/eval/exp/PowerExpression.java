package jp.hishidama.eval.exp;

/**
 * �ݏ�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2007.02.16
 */
public class PowerExpression extends Col2Expression {

	public static final String NAME = "power";

	public PowerExpression() {
		setOperator("**");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected PowerExpression(PowerExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new PowerExpression(this, s);
	}

	@Override
	protected Object operateObject(Object vl, Object vr) {
		return share.oper.power(vl, vr);
	}
}
