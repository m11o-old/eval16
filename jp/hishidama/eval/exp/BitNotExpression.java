package jp.hishidama.eval.exp;

/**
 * �r�b�g�ے�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public class BitNotExpression extends Col1Expression {

	public static final String NAME = "bitNot";

	public BitNotExpression() {
		setOperator("~");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected BitNotExpression(BitNotExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new BitNotExpression(this, s);
	}

	@Override
	public Object eval() {
		Object x = exp.eval();
		Object r = share.oper.bitNot(x);
		if (share.log != null) {
			share.log.logEval(getExpressionName(), x, r);
		}
		return r;
	}
}
