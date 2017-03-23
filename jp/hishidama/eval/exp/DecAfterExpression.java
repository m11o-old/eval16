package jp.hishidama.eval.exp;

/**
 * ��u�f�N�������g���Z�q�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2006.10.31
 */
public class DecAfterExpression extends Col1AfterExpression {

	public static final String NAME = "decAfter";

	public DecAfterExpression() {
		setOperator("--");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected DecAfterExpression(DecAfterExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new DecAfterExpression(this, s);
	}

	@Override
	public Object eval() {
		Object val = exp.eval();
		Object r = share.oper.inc(val, -1);
		exp.let(r, pos);
		if (share.log != null) {
			share.log.logEval(getExpressionName(), val, r);
		}
		return val;
	}
}