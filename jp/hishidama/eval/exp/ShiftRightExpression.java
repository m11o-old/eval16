package jp.hishidama.eval.exp;

/**
 * �E�V�t�g�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public class ShiftRightExpression extends Col2Expression {

	public static final String NAME = "sra";

	public ShiftRightExpression() {
		setOperator(">>");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected ShiftRightExpression(ShiftRightExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new ShiftRightExpression(this, s);
	}

	@Override
	protected Object operateObject(Object vl, Object vr) {
		return share.oper.shiftRight(vl, vr);
	}
}
