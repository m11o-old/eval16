package jp.hishidama.eval.repl;

import jp.hishidama.eval.exp.*;

/**
 * �\����͖ؒu���C���^�[�t�F�[�X.
 * <p>
 * AbstractExpression#replace()���Ă΂ꂽ�ہA�\����͖؂̂��ꂼ��̏����ɉ��������C���^�[�t�F�[�X�̃��\�b�h���Ă΂��B<br>
 * �e���\�b�h�ł́A�\����͖؂�u������ꍇ�͐V�����\����͖؂�Ԃ��A�u�����Ȃ��ꍇ�͈��������̂܂ܕԂ��K�v������B
 * </p>
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html">�Ђ�����</a>
 * @since 2007.02.20
 */
public interface Replace {

	/**
	 * ���ʎq�u��
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replace0(WordExpression exp);

	/**
	 * �P�����Z�q�u��
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replace1(Col1Expression exp);

	/**
	 * �񍀉��Z�q�u��
	 * <p>
	 * �����Z�Ȃǂ̒P���ȓ񍀉��Z�q�̂Ƃ��Ă΂��B
	 * </p>
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replace2(Col2Expression exp);

	/**
	 * �񍀉��Z�q�i����j�u��
	 * <p>
	 * and,or,�J���}�Ȃǂ̒P���łȂ��񍀉��Z�q�̂Ƃ��Ă΂��B
	 * </p>
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replace2(Col2OpeExpression exp);

	/**
	 * �O�����Z�q�u��
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replace3(Col3Expression exp);

	/**
	 * ���ʎq�u���i���Ӓl�Ƃ��Ďg�p�����ꍇ�j
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replaceVar0(WordExpression exp);

	/**
	 * �P�����Z�q�u���i���Ӓl�Ƃ��Ďg�p�����ꍇ�j
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replaceVar1(Col1Expression exp);

	/**
	 * �񍀉��Z�q�u���i���Ӓl�Ƃ��Ďg�p�����ꍇ�j
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replaceVar2(Col2Expression exp);

	/**
	 * �񍀉��Z�q�u���i���Ӓl�Ƃ��Ďg�p�����ꍇ�j
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replaceVar2(Col2OpeExpression exp);

	/**
	 * �O�����Z�q�u���i���Ӓl�Ƃ��Ďg�p�����ꍇ�j
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replaceVar3(Col3Expression exp);

	/**
	 * �֐��u��
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replaceFunc(FunctionExpression exp);

	/**
	 * ������Z�q�u��
	 * 
	 * @param exp
	 * @return �Vexp
	 */
	AbstractExpression replaceLet(Col2Expression exp);

}
