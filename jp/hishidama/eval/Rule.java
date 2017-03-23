package jp.hishidama.eval;

import jp.hishidama.eval.func.Function;
import jp.hishidama.eval.log.EvalLog;
import jp.hishidama.eval.oper.Operator;
import jp.hishidama.eval.var.Variable;

/**
 * ���[���N���X.
 * <p>
 * �\����̓��[���֘A�̃��[�U�[�����B
 * </p>
 *
 * @see jp.hishidama.eval.ExpRuleFactory#getRule()
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @version eval16
 */
public abstract class Rule implements Cloneable {

	protected Variable defaultVar = null;

	protected Function defaultFunc = null;

	protected Operator defaultOper = null;

	protected EvalLog defaultLog = null;

	/**
	 * �f�t�H���g�ϐ��Q�擾.
	 *
	 * @return �f�t�H���g�̕ϐ��Q
	 * @since eval16
	 */
	public Variable getDefaultVariable() {
		return defaultVar;
	}

	/**
	 * �f�t�H���g�ϐ��Q��ݒ肵�����[����Ԃ��B
	 *
	 * @param var
	 *            �ϐ��Q
	 * @return �f�t�H���g�l���ݒ肳�ꂽ���[��
	 * @since eval16
	 */
	public Rule defaultVariable(Variable var) {
		Rule rule = this.clone();
		rule.defaultVar = var;
		return rule;
	}

	/**
	 * �f�t�H���g�֐��Q�擾.
	 *
	 * @return �f�t�H���g�̊֐��Q
	 * @since eval16
	 */
	public Function getDefaultFunction() {
		return defaultFunc;
	}

	/**
	 * �f�t�H���g�֐��Q��ݒ肵�����[����Ԃ��B
	 *
	 * @param func
	 *            �֐��Q
	 * @return �f�t�H���g�l���ݒ肳�ꂽ���[��
	 */
	public Rule defaultFunction(Function func) {
		Rule rule = this.clone();
		rule.defaultFunc = func;
		return rule;
	}

	/**
	 * �f�t�H���g���Z�Q�擾.
	 *
	 * @return �f�t�H���g�̉��Z
	 * @since eval16
	 */
	public Operator getDefaultOperator() {
		return defaultOper;
	}

	/**
	 * �f�t�H���g���Z�Q��ݒ肵�����[����Ԃ��B
	 *
	 * @param oper
	 *            ���Z
	 * @return �f�t�H���g�l���ݒ肳�ꂽ���[��
	 * @since eval16
	 */
	public Rule defaultOperator(Operator oper) {
		Rule rule = this.clone();
		rule.defaultOper = oper;
		return rule;
	}

	/**
	 * �f�t�H���g���O�o�͎擾.
	 *
	 * @return �f�t�H���g�̃��O�o�̓I�u�W�F�N�g
	 * @since eval16
	 */
	public EvalLog getDefaultEvalLog() {
		return defaultLog;
	}

	/**
	 * �f�t�H���g���O�o�͂�ݒ肵�����[����Ԃ��B
	 *
	 * @param log
	 *            ���O�o�̓I�u�W�F�N�g
	 * @return �f�t�H���g�l���ݒ肳�ꂽ���[��
	 * @since eval16
	 */
	public Rule defaultEvalLog(EvalLog log) {
		Rule rule = this.clone();
		rule.defaultLog = log;
		return rule;
	}

	/**
	 * �\�����
	 *
	 * @param str
	 *            ��͑Ώە�����
	 * @return �\����͌���
	 */
	public abstract Expression parse(String str);

	@Override
	protected Rule clone() {
		try {
			return (Rule) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
}
