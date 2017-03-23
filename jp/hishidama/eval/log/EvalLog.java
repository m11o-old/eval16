package jp.hishidama.eval.log;

import jp.hishidama.eval.Expression;

/**
 * ���Z�����O�o�̓C���^�[�t�F�[�X.
 * <p>
 * {@link Expression#eval()}�ɂ����ĉ��Z�����s�����ۂɃ��O�o�͂���ׂ̃C���^�[�t�F�[�X�B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since eval16
 */
public interface EvalLog {

	/**
	 * ���O�o�́i�����Z�j.
	 *
	 * @param name
	 *            ���Z���inull�ȊO�j
	 * @param r
	 *            ���Z���ʂ̒l
	 */
	public void logEval(String name, Object r);

	/**
	 * ���O�o�́i�P�����Z�j.
	 *
	 * @param name
	 *            ���Z���inull�ȊO�j
	 * @param x
	 *            �l
	 * @param r
	 *            ���Z���ʂ̒l
	 */
	public void logEval(String name, Object x, Object r);

	/**
	 * ���O�o�́i�񍀉��Z�j.
	 *
	 * @param name
	 *            ���Z���inull�ȊO�j
	 * @param x
	 *            �l1
	 * @param y
	 *            �l2
	 * @param r
	 *            ���Z���ʂ̒l
	 */
	public void logEval(String name, Object x, Object y, Object r);

	/**
	 * ���O�o�́i�֐��j.
	 *
	 * @param name
	 *            ���Z���inull�ȊO�j
	 * @param funcName
	 *            �֐����inull�ȊO�j
	 * @param args
	 *            �֐��̈����̒l�inull�ȊO�j
	 * @param r
	 *            ���Z���ʂ̒l
	 */
	public void logEvalFunction(String name, String funcName, Object[] args,
			Object r);
}
