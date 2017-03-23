package jp.hishidama.eval.exp;

/**
 * �񍀉��Z�q(�Ǝ�����)�N���X.
 * <p>
 * ���Z��Ǝ���������^�C�v�B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2006.11.6
 */
public abstract class Col2OpeExpression extends Col2Expression {

	protected Col2OpeExpression() {
	}

	protected Col2OpeExpression(Col2Expression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	protected final Object operateObject(Object vl, Object vr) {
		throw new UnsupportedOperationException("���̃��\�b�h���Ă΂�Ă͂����Ȃ�");
	}

	@Override
	protected AbstractExpression replace() {
		expl = expl.replace();
		expr = expr.replace();
		return share.repl.replace2(this);
	}

	@Override
	protected AbstractExpression replaceVar() {
		expl = expl.replaceVar();
		expr = expr.replaceVar();
		return share.repl.replaceVar2(this);
	}
}
