package jp.hishidama.eval.func;

import java.util.HashMap;
import java.util.Map;

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
 * @since 2007.02.16
 * @version 2010.02.16
 * @see InvokeUtil
 */
public class InvokeFunction implements Function {

	protected Map<Class<?>, InvokeUtil> map = new HashMap<Class<?>, InvokeUtil>();

	@Override
	public Object eval(String name, Object[] args) throws Exception {
		return null;
	}

	@Override
	public Object eval(Object object, String name, Object[] args)
			throws Exception {
		if (object == null) {
			return null;
		}

		Class<?> clazz = object.getClass();
		if (!map.containsKey(clazz)) {
			InvokeUtil invoker = new InvokeUtil();
			invoker.addMethods(clazz, "");
			map.put(clazz, invoker);
		}

		InvokeUtil invoker = map.get(clazz);
		return invoker.invoke(name, object, args);
	}
}
