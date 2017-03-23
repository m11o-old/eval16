package jp.hishidama.eval.exp;

import java.util.*;

import jp.hishidama.eval.EvalException;

/**
 * ���̒��ۃN���X.
 * <p>
 * �\����͖؂��\�����A���Z�̕]�������{����B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @version eval16
 */
public abstract class AbstractExpression {

	/**
	 * �S�̕�����.
	 * <p>
	 * ���ߑΏۑS�̂̕�����B
	 * </p>
	 */
	protected String string = null;

	/**
	 * �ʒu.
	 * <p>
	 * �S�̕�������̈ʒu�B
	 * </p>
	 */
	protected int pos = -1;

	private String ope1;

	private String ope2;

	public ShareExpValue share;

	protected AbstractExpression() {
	}

	protected AbstractExpression(AbstractExpression from, ShareExpValue s) {
		string = from.string;
		pos = from.pos;
		prio = from.prio;
		if (s != null) {
			share = s;
		} else {
			share = from.share;
		}
		ope1 = from.ope1;
		ope2 = from.ope2;
	}

	/**
	 * ��������.
	 * <p>
	 * ���C���X�^���X�̕������쐬����B
	 * </p>
	 *
	 * @param s
	 *            �V���ʏ��
	 * @return �V�C���X�^���X
	 * @since 2007.02.17
	 */
	public abstract AbstractExpression dup(ShareExpValue s);

	/**
	 * ���Z�q�擾.
	 * <p>
	 * ���N���X�ɌŗL�̉��Z�q��Ԃ��B
	 * </p>
	 *
	 * @return ���Z�q
	 */
	public final String getOperator() {
		return ope1;
	}

	/**
	 * �I�����Z�q�擾.
	 * <p>
	 * ")"��"]"�ȂǁB
	 * </p>
	 *
	 * @return ���Z�q�i���݂��Ȃ��ꍇ��null�j
	 * @since 2007.02.16
	 */
	public final String getEndOperator() {
		return ope2;
	}

	/**
	 * ���Z�q�Z�b�g.
	 *
	 * @param ope
	 *            ���Z�q
	 * @since 2007.02.17
	 */
	public final void setOperator(String ope) {
		ope1 = ope;
	}

	/**
	 * �I�����Z�q�Z�b�g.
	 *
	 * @param ope
	 *            ���Z�q
	 * @since 2007.02.17
	 */
	public final void setEndOperator(String ope) {
		ope2 = ope;
	}

	/**
	 * ���ʎq�擾.
	 *
	 * @return ���ʎq
	 */
	public String getWord() {
		return getOperator();
	}

	protected void setWord(String word) {
		throw new EvalException(EvalException.EXP_FORBIDDEN_CALL, this, null);
	}

	/**
	 * �����擾.
	 * <p>
	 * ���Z�̍��̐���Ԃ��B<br>
	 * �i��F�񍀉��Z�q�̏ꍇ�A2��Ԃ��j
	 * </p>
	 *
	 * @return ����
	 */
	protected abstract int getCols();

	/**
	 * �ʒu�ݒ�.
	 *
	 * @param string
	 *            ������
	 * @param pos
	 *            �ʒu
	 */
	protected final void setPos(String string, int pos) {
		this.string = string;
		this.pos = pos;
	}

	/**
	 * ��͖��擾.
	 *
	 * @return ��͖�
	 */
	public abstract String getExpressionName();

	/**
	 * ��͑Ώە�����擾.
	 *
	 * @return ������
	 */
	public final String getString() {
		return string;
	}

	/**
	 * �ʒu�擾.
	 *
	 * @return �ʒu
	 */
	public final int getPos() {
		return pos;
	}

	/**
	 * �擪�ʒu�擾.
	 * <p>
	 * �����̉��Z�ɑ������ԍ����̈ʒu��Ԃ��B
	 * </p>
	 *
	 * @return �ʒu
	 */
	protected abstract int getFirstPos();

	/** �D�揇��. */
	protected int prio;

	/**
	 * �D�揇�ʐݒ�.
	 *
	 * @param prio
	 *            �D�揇��
	 * @since 2006.10.27
	 */
	public final void setPriority(int prio) {
		this.prio = prio;
	}

	/**
	 * �D�揇�ʎ擾.
	 *
	 * @return �D�揇��
	 * @since 2006.10.27
	 */
	protected final int getPriority() {
		return prio;
	}

	/**
	 * ������s.
	 * <p>
	 * �ϐ��i��Expression�j�ɒl��������B
	 * </p>
	 *
	 * @param val
	 *            �������l
	 * @param pos
	 *            ���Z�q�̈ʒu�i�G���[���Ɏg�p�j
	 * @throws EvalException
	 *             ���Ӓl���ϐ��łȂ��Ƃ�
	 * @since 2007.2.13
	 */
	protected void let(Object val, int pos) {
		// �ϐ��Ƃ��Ĉ�����N���X�́A�����\�b�h���I�[�o�[���C�h���Ēl��������B
		throw new EvalException(EvalException.EXP_NOT_LET, toString(), this,
				null);
	}

	/**
	 * �ϐ��擾.
	 * <p>
	 * �ϐ��l�擾�i�]���j�p�B
	 * </p>
	 *
	 * @return �ϐ���\���I�u�W�F�N�g
	 * @throws EvalException
	 *             �ϐ��ł͂Ȃ��Ƃ�
	 * @since 2006.10.27
	 * @version 2007.02.13
	 */
	protected Object getVariable() {
		// �ϐ��Ƃ��Ĉ�����N���X�́A�����\�b�h���I�[�o�[���C�h���ĕϐ��I�u�W�F�N�g��Ԃ��B
		// int first = getFirstPos();
		// String word = string.substring(first, pos);
		String word = this.toString();
		throw new EvalException(EvalException.EXP_NOT_VARIABLE, word, this,
				null);
	}

	/**
	 * �����̒l�ݒ�.
	 * <p>
	 * �]�������s���ă��X�g�ɃZ�b�g����B
	 * </p>
	 *
	 * @param args
	 *            �l��ݒ肷�郊�X�g
	 * @since 2005.02.15
	 */
	protected void evalArgs(List<Object> args) {
		args.add(eval());
	}

	/**
	 * �]�����s.
	 * <p>
	 * Object�^�ŉ��Z�����{���Č��ʂ�Ԃ��B<br>
	 * ���Z���s�N���X��o�^����K�v����B
	 * </p>
	 *
	 * @return ���Z����
	 * @throws EvalException
	 *             ���Z���ɃG���[�����������Ƃ�
	 * @since 2007.02.15
	 */
	public abstract Object eval();

	/**
	 * �T�����s(�[���D��).
	 *
	 * @since 2007.02.17
	 */
	protected abstract void search();

	/**
	 * �ϊ����s
	 *
	 * @return Expression
	 * @since 2007.02.20
	 */
	protected abstract AbstractExpression replace();

	/**
	 * ���Ӓl�Ƃ��ĕϊ����s
	 *
	 * @return Expression
	 * @since 2007.02.20
	 */
	protected abstract AbstractExpression replaceVar();

	/**
	 * �I�u�W�F�N�g��r.
	 * <p>
	 * �؍\�������������ǂ������`�F�b�N����B<br>
	 * ���Z�q�̕�����\���̈Ⴂ�͈ӎ����Ȃ��B
	 * </p>
	 *
	 * @param obj
	 *            �I�u�W�F�N�g
	 * @return �������Ƃ��Atrue
	 * @see #same(AbstractExpression)
	 * @since 2007.02.27
	 */
	@Override
	public abstract boolean equals(Object obj);

	/**
	 * �n�b�V���R�[�h�l�擾.
	 *
	 * @return �n�b�V���R�[�h�l
	 * @since 2007.02.27
	 */
	@Override
	public abstract int hashCode();

	/**
	 * �I�u�W�F�N�g��r.
	 * <p>
	 * ���Z�q�̕�����\���܂Ŋ܂߂ăI�u�W�F�N�g�����������ǂ����`�F�b�N����B
	 * </p>
	 *
	 * @param exp
	 *            ��r�Ώ�
	 * @return �������Ƃ��Atrue
	 * @see #equals(Object)
	 * @since 2007.02.27
	 */
	public boolean same(AbstractExpression exp) {
		return same(getOperator(), exp.getOperator())
				&& same(getEndOperator(), exp.getEndOperator())
				&& this.equals(exp);
	}

	private static boolean same(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equals(str2);
	}

	/**
	 * �f�o�b�O�p�_���v.
	 *
	 * @param n
	 *            �^�u�p�̌���
	 */
	public abstract void dump(int n);

	/**
	 * ������\���ϊ�.
	 * <p>
	 * ���̕�����\����Ԃ��B
	 * </p>
	 *
	 * @return ������\��
	 * @since 2006.10.27
	 */
	@Override
	public abstract String toString();
}
