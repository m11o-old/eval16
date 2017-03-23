package jp.hishidama.eval.exp;

import jp.hishidama.eval.EvalException;
import jp.hishidama.eval.lex.Lex;

/**
 * �ϐ��N���X.
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @version 2007.02.15
 */
public class VariableExpression extends WordExpression {

	public static final String NAME = "variable";

	@Override
	public String getExpressionName() {
		return NAME;
	}

	/**
	 * ���C���X�^���X�����i���ʎq�p�j.
	 * 
	 * @param lex
	 *            ���ߒ��ʒu
	 * @param prio
	 *            �D�揇��
	 * @return ���C���X�^���X
	 */
	public static AbstractExpression create(Lex lex, int prio) {
		AbstractExpression exp = new VariableExpression(lex.getWord());
		exp.setPos(lex.getString(), lex.getPos());
		exp.setPriority(prio);
		exp.share = lex.getShare();
		return exp;
	}

	public VariableExpression(String str) {
		super(str);
	}

	protected VariableExpression(VariableExpression from, ShareExpValue s) {
		super(from, s);
	}

	@Override
	public AbstractExpression dup(ShareExpValue s) {
		return new VariableExpression(this, s);
	}

	@Override
	public Object eval() {
		try {
			Object x = getVarValue();
			Object r = share.oper.variable(x, this);
			if (share.log != null) {
				share.log.logEval(getExpressionName(), x, r);
			}
			return r;
		} catch (EvalException e) {
			throw e;
		} catch (Exception e) {
			throw new EvalException(EvalException.EXP_NOT_VAR_VALUE, this, e);
		}
	}

	@Override
	protected void let(Object val, int pos) {
		String name = getWord();
		try {
			share.var.setValue(name, val);
		} catch (EvalException e) {
			throw e;
		} catch (Exception e) {
			throw new EvalException(EvalException.EXP_NOT_LET_VAR,
					getExpressionName(), name, string, pos, e);
		}
	}

	/**
	 * �ϐ��l�擾.
	 * 
	 * @return �ϐ��l�i�K��null�ȊO�j
	 * @throws EvalException
	 *             �ϐ��������Ƃ�
	 * @version 2007.02.09
	 */
	private Object getVarValue() {
		String word = getWord();

		Object val;
		try {
			val = share.var.getValue(word);
		} catch (EvalException e) {
			throw e;
		} catch (Exception e) {
			throw new EvalException(EvalException.EXP_NOT_VAR_VALUE, word,
					this, e);
		}
		if (val == null) {
			throw new EvalException(EvalException.EXP_NOT_DEF_VAR, word, this,
					null);
		}
		return val;
	}

	@Override
	protected Object getVariable() {
		try {
			return share.var.getValue(word);
		} catch (EvalException e) {
			throw e;
		} catch (Exception e) {
			throw new EvalException(EvalException.EXP_NOT_VARIABLE, word, this,
					e);
		}
	}
}
