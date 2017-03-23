package jp.hishidama.eval.rule;

import jp.hishidama.eval.ExpRuleFactory;
import jp.hishidama.eval.exp.AbstractExpression;

/**
 * Java���[���t�@�N�g���[�N���X.
 * <p>
 * Java�ɋ߂����[���̃C���X�^���X�𐶐�����B
 * </p>
 * 
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html">�Ђ�����</a>
 * @since 2007.02.21
 */
public class JavaRuleFactory extends ExpRuleFactory {

	private static JavaRuleFactory me;

	/**
	 * ���[���t�@�N�g���[�擾.
	 * <p>
	 * Java���[���̃t�@�N�g���[�C���X�^���X��Ԃ��B
	 * </p>
	 * 
	 * @return ���[���t�@�N�g���[
	 */
	public static ExpRuleFactory getInstance() {
		if (me == null) {
			me = new JavaRuleFactory();
		}
		return me;
	}

	@Override
	protected AbstractRule createCommaRule(ShareRuleValue share) {
		return null;
	}

	@Override
	protected AbstractRule createPowerRule(ShareRuleValue share) {
		return null;
	}

	@Override
	protected AbstractExpression createLetPowerExpression() {
		return null;
	}
}
