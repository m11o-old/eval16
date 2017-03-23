package jp.hishidama.eval.func;

import jp.hishidama.lang.reflect.InvokeUtil;

/**
 * ���t���N�V�������g�p�����֐�.
 * <p>
 * ���t���N�V������Method#invoke()���g�p���ăI�u�W�F�N�g�̃��\�b�h���Ăяo���B<br>
 * �I�u�W�F�N�g�������ꍇ��null��Ԃ��B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2010.02.16
 */
public class MathFunction implements Function {

	protected InvokeUtil invoker;

	@Override
	public Object eval(String name, Object[] args) throws Exception {
		if (invoker == null) {
			invoker = new InvokeUtil();
			invoker.addMethods(Math.class, "");
		}
		return invoker.invoke(name, null, args);
	}

	@Override
	public Object eval(Object object, String name, Object[] args)
			throws Exception {
		return eval(name, args);
	}
}
