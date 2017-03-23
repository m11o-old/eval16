package jp.hishidama.eval.exp;

/**
 * �P�����Z�q�N���X.
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @version 2006.11.07
 */
public abstract class Col1Expression extends AbstractExpression {

	/**
	 * ���C���X�^���X�����i�P���O�u���Z�q�p�j.
	 *
	 * @param exp
	 *            ���Z�q�C���X�^���X
	 * @param string
	 *            �S�̕�����
	 * @param pos
	 *            �ʒu
	 * @param x
	 *            ���C���X�^���X
	 * @return ���C���X�^���X
	 */
	public static AbstractExpression create(AbstractExpression exp,
			String string, int pos, AbstractExpression x) {
		Col1Expression n = (Col1Expression) exp;
		n.setExpression(x);
		n.setPos(string, pos);
		return n;
	}

	/** �����̎�. */
	protected AbstractExpression exp;

	protected Col1Expression() {
	}

	protected Col1Expression(Col1Expression from, ShareExpValue s) {
		super(from, s);
		if (from.exp != null) {
			exp = from.exp.dup(s);
		}
	}

	/**
	 * �����ݒ�.
	 *
	 * @param x
	 *            ���C���X�^���X
	 */
	public void setExpression(AbstractExpression x) {
		exp = x;
	}

	@Override
	protected final int getCols() {
		return 1;
	}

	@Override
	protected final int getFirstPos() {
		return exp.getFirstPos();
	}

	@Override
	protected void search() {
		share.srch.search(this);
		if (share.srch.end()) {
			return;
		}

		if (share.srch.search1_begin(this)) {
			return;
		}
		if (share.srch.end()) {
			return;
		}

		exp.search();
		if (share.srch.end()) {
			return;
		}

		share.srch.search1_end(this);
	}

	@Override
	protected AbstractExpression replace() {
		exp = exp.replace();
		return share.repl.replace1(this);
	}

	@Override
	protected AbstractExpression replaceVar() {
		exp = exp.replaceVar();
		return share.repl.replaceVar1(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Col1Expression) {
			Col1Expression e = (Col1Expression) obj;
			if (getClass() == e.getClass()) {
				if (exp == null) {
					return e.exp == null;
				}
				if (e.exp == null) {
					return false;
				}
				return exp.equals(e.exp);
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() ^ exp.hashCode();
	}

	@Override
	public void dump(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(' ');
		}
		sb.append(getOperator());
		System.out.println(sb.toString());
		if (exp != null) {
			exp.dump(n + 1);
		}
	}

	@Override
	public String toString() {
		if (exp == null) {
			return getOperator();
		}
		StringBuilder sb = new StringBuilder();
		if (exp.getPriority() > prio) {
			sb.append(getOperator());
			sb.append(exp.toString());
		} else if (exp.getPriority() == prio) {
			sb.append(getOperator());
			sb.append(' ');
			sb.append(exp.toString());
		} else {
			sb.append(getOperator());
			sb.append(share.paren.getOperator());
			sb.append(exp.toString());
			sb.append(share.paren.getEndOperator());
		}
		return sb.toString();
	}
}
