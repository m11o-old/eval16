package jp.hishidama.lang.reflect.conv;

/**
 * �I�u�W�F�N�g�^�ϊ��N���X.
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2010.02.16
 */
public abstract class TypeConverter {

	protected static final int UNMATCH = -1;
	protected static final int MATCH_NULL = 1;
	protected static final int MATCH_OBJECT = 2;
	protected static final int MATCH_STRING = 3;
	protected static final int MATCH_EQUALS = Short.MAX_VALUE;

	/**
	 * �^���}�b�`���Ă���x������Ԃ��B
	 *
	 * @param obj
	 *            �I�u�W�F�N�g
	 * @return ���v�x�i�傫���قǃ}�b�`���Ă���j
	 */
	public abstract int match(Object obj);

	/**
	 * �^�ϊ�.
	 *
	 * @param obj
	 *            �I�u�W�F�N�g
	 * @return �ϊ���I�u�W�F�N�g
	 */
	public abstract Object convert(Object obj);
}
