package jp.hishidama.eval.exp;

/**
 * �r�b�g�r���I�_���a�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public class BitXorExpression extends Col2Expression {

	public static final String NAME = "bitXor";

	public BitXorExpression() {
		setOperator("^");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected BitXorExpression(BitXorExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new BitXorExpression(this, s);
	}

	@Override
	protected Object operateObject(Object vl, Object vr) {
		return share.oper.bitXor(vl, vr);
	}
}
