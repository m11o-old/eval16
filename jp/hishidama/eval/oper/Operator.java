package jp.hishidama.eval.oper;

import jp.hishidama.eval.EvalException;
import jp.hishidama.eval.exp.AbstractExpression;
import jp.hishidama.eval.exp.CharExpression;
import jp.hishidama.eval.exp.NumberExpression;
import jp.hishidama.eval.exp.StringExpression;

/**
 * ���Z���s�C���^�[�t�F�[�X.
 * <p>
 * eval()�ɂ����āA���ۂ̉��Z�����s����N���X�ׂ̈̃C���^�[�t�F�[�X�B
 * </p>
 *
 * @see jp.hishidama.eval.Expression#setOperator(Operator)
 * @see jp.hishidama.eval.Expression#eval()
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2007.02.15
 * @version eval16
 */
public interface Operator {
	/**
	 * �ݏ扉�Z.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 * @since 2007.02.16
	 */
	public Object power(Object x, Object y);

	/**
	 * ���������Z.
	 *
	 * @param x
	 * @return �l
	 */
	public Object signPlus(Object x);

	/**
	 * ���������Z.
	 *
	 * @param x
	 * @return �l
	 */
	public Object signMinus(Object x);

	/**
	 * ���Z.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object plus(Object x, Object y);

	/**
	 * ���Z.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object minus(Object x, Object y);

	/**
	 * ��Z.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object mult(Object x, Object y);

	/**
	 * ���Z.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object div(Object x, Object y);

	/**
	 * �]�Z.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object mod(Object x, Object y);

	/**
	 * �r�b�g�ے�.
	 *
	 * @param x
	 * @return �l
	 */
	public Object bitNot(Object x);

	/**
	 * ���V�t�g.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object shiftLeft(Object x, Object y);

	/**
	 * �E�V�t�g.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object shiftRight(Object x, Object y);

	/**
	 * �_���E�V�t�g.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object shiftRightLogical(Object x, Object y);

	/**
	 * �r�b�g�_����.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object bitAnd(Object x, Object y);

	/**
	 * �r�b�g�_���a.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object bitOr(Object x, Object y);

	/**
	 * �r�b�g�r���I�_���a.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object bitXor(Object x, Object y);

	/**
	 * �ے�.
	 *
	 * @param x
	 * @return �l
	 */
	public Object not(Object x);

	/**
	 * ����.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object equal(Object x, Object y);

	/**
	 * �s����.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object notEqual(Object x, Object y);

	/**
	 * ��菬.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object lessThan(Object x, Object y);

	/**
	 * �ȉ�.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object lessEqual(Object x, Object y);

	/**
	 * ����.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object greaterThan(Object x, Object y);

	/**
	 * �ȏ�.
	 *
	 * @param x
	 * @param y
	 * @return �l
	 */
	public Object greaterEqual(Object x, Object y);

	/**
	 * �^�U�l.
	 * <p>
	 * �I�u�W�F�N�g��^�U�l�ɕϊ�����B
	 * </p>
	 *
	 * @param x
	 * @return �^�U�l
	 */
	public boolean bool(Object x);

	/**
	 * �C���N�������g�E�f�N�������g
	 *
	 * @param x
	 * @param inc
	 *            �C���N�������g�̂Ƃ�+1�A�f�N�������g�̂Ƃ�-1
	 * @return �l
	 */
	public Object inc(Object x, int inc);

	/**
	 * �����֕ϊ�.
	 * <p>
	 * �V���O���N�H�[�e�[�V�����ň͂܂ꂽ�������l�ɕϊ�����B<br>
	 * �G�X�P�[�v���������߂������ꍇ���͓����\�b�h�ŕϊ�����B
	 * </p>
	 *
	 * @param word
	 *            �l
	 * @param exp
	 *            Expression
	 * @return �l
	 * @see CharExpression
	 * @since eval16
	 */
	public Object character(String word, AbstractExpression exp)
			throws EvalException;

	/**
	 * ������֕ϊ�.
	 * <p>
	 * �_�u���N�H�[�e�[�V�����ň͂܂ꂽ�������l�ɕϊ�����B<br>
	 * ��{�I�ɂ́A������word�����̂܂ܕԂ����ƂɂȂ邾�낤�B<br>
	 * �G�X�P�[�v���������߂������ꍇ���͓����\�b�h�ŕϊ�����B
	 * </p>
	 *
	 * @param word
	 *            �l
	 * @param exp
	 *            Expression
	 * @return �l
	 * @see StringExpression
	 * @since eval16
	 */
	public Object string(String word, AbstractExpression exp)
			throws EvalException;

	/**
	 * ���l�֕ϊ�.
	 * <p>
	 * �����݂̂ō\�����ꂽ�������l�ɕϊ�����B<br>
	 * �Ⴆ�΁u123�v�Ƃ����������Integer�^��123�ɕϊ����ĕԂ��B
	 * </p>
	 *
	 * @param word
	 *            �l
	 * @param exp
	 *            Expression
	 * @return �l
	 * @see NumberExpression
	 * @since eval16
	 */
	public Object number(String word, AbstractExpression exp)
			throws EvalException;

	/**
	 * �ϐ�����擾�����l��ϊ�.
	 * <p>
	 * �ϐ�����擾�����l�ɑ΂��āA�^�ɉ����ēƎ��̕ϊ����s�������ꍇ�ɓ����\�b�h�ŕϊ�����B
	 * </p>
	 *
	 * @param value
	 *            �l
	 * @param exp
	 *            Expression
	 * @return �l
	 * @since eval16
	 */
	public Object variable(Object value, AbstractExpression exp)
			throws EvalException;
}
