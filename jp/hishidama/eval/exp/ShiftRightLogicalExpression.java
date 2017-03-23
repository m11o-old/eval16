package jp.hishidama.eval.exp;

/**
 * �E�V�t�g(�����Ȃ�)�N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 */
public class ShiftRightLogicalExpression extends Col2Expression {

	public static final String NAME = "srl";

	public ShiftRightLogicalExpression() {
		setOperator(">>>");
	}

	@Override
	public String getExpressionName() {
		return NAME;
	}

	protected ShiftRightLogicalExpression(ShiftRightLogicalExpression from,
			ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new ShiftRightLogicalExpression(this, s);
	}

	@Override
	protected Object operateObject(Object vl, Object vr) {
		return share.oper.shiftRightLogical(vl, vr);
	}
}
