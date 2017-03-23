package jp.hishidama.eval.exp;

/**
 * ���V�t�g�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public class ShiftLeftExpression extends Col2Expression {

	public static final String NAME = "sla";

	public ShiftLeftExpression() {
		setOperator("<<");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected ShiftLeftExpression(ShiftLeftExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new ShiftLeftExpression(this, s);
	}

	@Override
	protected Object operateObject(Object vl, Object vr) {
		return share.oper.shiftLeft(vl, vr);
	}
}
