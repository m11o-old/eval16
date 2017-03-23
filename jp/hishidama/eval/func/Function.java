package jp.hishidama.eval.func;

/**
 * �֐��C���^�[�t�F�[�X.
 * <p>
 * ���̕]�����Ɏ��s�����֐����`����B
 * </p>
 *
 * @see jp.hishidama.eval.Expression#setFunction(Function)
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @version eval16
 */
public interface Function {

	/**
	 * �֐����s.
	 * <p>
	 * ���̕]�����Ɋ֐��i{@code �֐���()}�j���������ꍇ�A�����\�b�h���Ă΂��B
	 * </p>
	 *
	 * @param name
	 *            �֐����i�K��null�ȊO�j
	 * @param args
	 *            �����i�K��null�ȊO�j
	 *
	 * @return ���s����
	 * @throws Exception
	 *             ��O
	 * @see jp.hishidama.eval.exp.FunctionExpression#eval()
	 * @since 2010.02.15
	 */
	public Object eval(String name, Object[] args) throws Exception;

	/**
	 * ���\�b�h���s.
	 * <p>
	 * ���̕]�����Ƀ��\�b�h�i{@code �I�u�W�F�N�g.���\�b�h��()}�j���������ꍇ�A�����\�b�h���Ă΂��B
	 * </p>
	 *
	 * @param object
	 *            ���s�ΏۃI�u�W�F�N�g
	 * @param name
	 *            ���\�b�h���i�K��null�ȊO�j
	 * @param args
	 *            �����i�K��null�ȊO�j
	 *
	 * @return ���s����
	 * @throws Exception
	 *             ��O
	 * @see jp.hishidama.eval.exp.FunctionExpression#eval()
	 * @since 2007.02.15
	 */
	public Object eval(Object object, String name, Object[] args)
			throws Exception;
}
