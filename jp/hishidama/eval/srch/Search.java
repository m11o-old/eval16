package jp.hishidama.eval.srch;

import jp.hishidama.eval.exp.*;

/**
 * �T���C���^�[�t�F�[�X.
 * <p>
 * �S��͖؍\����T������Ƃ��ɌĂ΂��C���^�[�t�F�[�X�B
 * </p>
 * 
 * @see jp.hishidama.eval.Expression#search(Search)
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html">�Ђ�����</a>
 * @since 2007.02.17
 * @version 2007.02.20
 */
public interface Search {

	/**
	 * @return �T���S�̂��I������Ƃ��Atrue
	 * @since 2007.02.20
	 */
	public boolean end();

	/**
	 * �T�����s
	 * <p>
	 * �T�����s���ɁA���ꂼ��̖؍\���ň�x���Ă΂��B
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 */
	public void search(AbstractExpression exp);

	/**
	 * �T�����s�i�P��j
	 * <p>
	 * �T�����ɁA���l�܂��͕ϐ��ł���ΌĂ΂��B
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @since 2007.02.20
	 */
	public void search0(WordExpression exp);

	/**
	 * �T�����s�i�P�����Z�q�j
	 * <p>
	 * �T�����ɁA�P�����Z�q�ł���ΌĂ΂��B�i�����̒T���O�j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @return �����Z�q�̒T�����I������Ƃ��Atrue
	 * @since 2007.02.20
	 */
	public boolean search1_begin(Col1Expression exp);

	/**
	 * �T�����s�i�P�����Z�q�j
	 * <p>
	 * �T�����ɁA�P�����Z�q�ł���ΌĂ΂��B�i�����̒T����j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @since 2007.02.20
	 */
	public void search1_end(Col1Expression exp);

	/**
	 * �T�����s�i�񍀉��Z�q�j
	 * <p>
	 * �T�����ɁA�񍀉��Z�q�ł���ΌĂ΂��B�i�ŏ��j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @return �����Z�q�̒T�����I������Ƃ��Atrue
	 * @since 2007.02.20
	 */
	public boolean search2_begin(Col2Expression exp);

	/**
	 * �T�����s�i�񍀉��Z�q�j
	 * <p>
	 * �T�����ɁA�񍀉��Z�q�ł���ΌĂ΂��B�i��1���̒T����j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @return �����Z�q�̒T�����I������Ƃ��Atrue
	 * @since 2007.02.20
	 */
	public boolean search2_2(Col2Expression exp);

	/**
	 * �T�����s�i�񍀉��Z�q�j
	 * <p>
	 * �T�����ɁA�񍀉��Z�q�ł���ΌĂ΂��B�i�Ō�i��2���̒T����j�j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @since 2007.02.20
	 */
	public void search2_end(Col2Expression exp);

	/**
	 * �T�����s�i�O�����Z�q�j
	 * <p>
	 * �T�����ɁA�O�����Z�q�ł���ΌĂ΂��B�i�ŏ��j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @return �����Z�q�̒T�����I������Ƃ��Atrue
	 * @since 2007.02.20
	 */
	public boolean search3_begin(Col3Expression exp);

	/**
	 * �T�����s�i�O�����Z�q�j
	 * <p>
	 * �T�����ɁA�O�����Z�q�ł���ΌĂ΂��B�i�������Z�q�̒T����j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @return �����Z�q�̒T�����I������Ƃ��Atrue
	 * @since 2007.02.20
	 */
	public boolean search3_2(Col3Expression exp);

	/**
	 * �T�����s�i�O�����Z�q�j
	 * <p>
	 * �T�����ɁA�O�����Z�q�ł���ΌĂ΂��B�i��2���̒T����j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @return �����Z�q�̒T�����I������Ƃ��Atrue
	 * @since 2007.02.20
	 */
	public boolean search3_3(Col3Expression exp);

	/**
	 * �T�����s�i�O�����Z�q�j
	 * <p>
	 * �T�����ɁA�O�����Z�q�ł���ΌĂ΂��B�i�Ō�i��3���̒T����j�j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @since 2007.02.20
	 */
	public void search3_end(Col3Expression exp);

	/**
	 * �T�����s�i�֐��j
	 * <p>
	 * �T�����ɁA�֐��ł���ΌĂ΂��B�i�ŏ��j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @return �����Z�q�̒T�����I������Ƃ��Atrue
	 * @since 2007.02.20
	 */
	public boolean searchFunc_begin(FunctionExpression exp);

	/**
	 * �T�����s�i�֐��j
	 * <p>
	 * �T�����ɁA�֐��ł���ΌĂ΂��B�i�I�u�W�F�N�g�̒T����j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @return �����Z�q�̒T�����I������Ƃ��Atrue
	 * @since 2007.02.20
	 */
	public boolean searchFunc_2(FunctionExpression exp);

	/**
	 * �T�����s�i�֐��j
	 * <p>
	 * �T�����ɁA�֐��ł���ΌĂ΂��B�i�Ō�i�����̒T����j�j
	 * </p>
	 * 
	 * @param exp
	 *            �T���Ώ�Expression
	 * @since 2007.02.20
	 */
	public void searchFunc_end(FunctionExpression exp);
}
