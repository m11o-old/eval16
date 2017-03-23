package jp.hishidama.eval.rule;

import java.util.*;

import jp.hishidama.eval.EvalException;
import jp.hishidama.eval.Expression;
import jp.hishidama.eval.Rule;
import jp.hishidama.eval.exp.AbstractExpression;
import jp.hishidama.eval.exp.ShareExpValue;
import jp.hishidama.eval.lex.Lex;
import jp.hishidama.eval.lex.LexFactory;
import jp.hishidama.eval.oper.Operator;
import jp.hishidama.eval.ref.Refactor;
import jp.hishidama.eval.srch.Search;
import jp.hishidama.eval.var.Variable;

/**
 * ���[�����ʏ��N���X.
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @version eval16
 */
public class ShareRuleValue extends Rule {
	public AbstractRule topRule;

	public AbstractRule funcArgRule;

	/**
	 * Lex���쐬����t�@�N�g���[.
	 */
	public LexFactory lexFactory;

	/**
	 * ���Z�q�Q.
	 * <p>
	 * �����͂ŉ��Z�q�ƔF�����镶����B ���Z�q�̕������ɂ���Ĕz��𕪂��Ă���B �i�������Z�q�����Ƀ}�b�`������ׁj
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	protected List<String>[] opeList = new List[4];

	/** �ۊ��ʂ̉��Z�q */
	public AbstractExpression paren;

	@Override
	public Expression parse(String str) {
		if (str == null) {
			return null;
		}
		if (str.trim().length() <= 0) {
			return new EmptyExpression();
		}

		ShareExpValue exp = new ShareExpValue();

		AbstractExpression x = parse(str, exp);
		exp.setAbstractExpression(x);

		if (defaultVar != null) {
			exp.setVariable(defaultVar);
		}
		if (defaultFunc != null) {
			exp.setFunction(defaultFunc);
		}
		if (defaultOper != null) {
			exp.setOperator(defaultOper);
		}
		if (defaultLog != null) {
			exp.setEvalLog(defaultLog);
		}

		return exp;
	}

	/**
	 * �\����́i�����p�j
	 *
	 * @param str
	 *            ��͑Ώە�����
	 * @param exp
	 *            �����ʏ��
	 * @return �\����͖�
	 * @since 2007.02.20
	 */
	public AbstractExpression parse(String str, ShareExpValue exp) {
		if (str == null) {
			return null;
		}

		Lex lex = lexFactory.create(str, opeList, this, exp);
		lex.check();

		AbstractExpression x = topRule.parse(lex);
		if (lex.getType() != Lex.TYPE_EOF) {
			throw new EvalException(EvalException.PARSE_STILL_EXIST, lex);
		}
		return x;
	}

	/**
	 * ��͑Ώۂ������Ƃ��ɕԂ���Expression
	 */
	class EmptyExpression extends Expression {

		@Override
		public Object eval() {
			return null;
		}

		@Override
		public void optimize(Variable var, Operator oper) {
		}

		@Override
		public void search(Search srch) {
		}

		@Override
		public void refactorName(Refactor ref) {
		}

		@Override
		public void refactorFunc(Refactor ref, Rule rule) {
		}

		@Override
		public Expression dup() {
			return new EmptyExpression();
		}

		@Override
		public boolean same(Expression obj) {
			if (obj instanceof EmptyExpression) {
				return true;
			}
			return false;
		}

		@Override
		public String toString() {
			return "";
		}
	}
}
