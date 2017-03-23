package jp.hishidama.lang.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import jp.hishidama.lang.IllegalArgumentLengthException;
import jp.hishidama.lang.reflect.conv.TypeConverter;
import jp.hishidama.lang.reflect.conv.TypeConverterManager;

/**
 * ���\�b�h�Ăяo���N���X.
 * <p>
 * �����̌^�𓖊Y���\�b�h�p�ɕϊ����ă��\�b�h���Ăяo���B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2010.02.16
 */
public class Invoker {

	protected String name;

	protected Method method;

	protected TypeConverter objConv;

	protected TypeConverter[] convs;

	/**
	 * �R���X�g���N�^�[.
	 *
	 * @param name
	 *            ���O
	 * @param clazz
	 *            �Ăяo���ΏۃN���X
	 * @param method
	 *            �Ăяo���Ώۃ��\�b�h
	 * @param manager
	 *            �^�ϊ��Ǘ��N���X
	 */
	public Invoker(String name, Class<?> clazz, Method method,
			TypeConverterManager manager) {
		this.name = name;
		this.method = method;

		initObjectConverter(clazz, manager);
		initArgsConverter(manager);
	}

	protected void initObjectConverter(Class<?> clazz,
			TypeConverterManager manager) {
		objConv = manager.getConverter(clazz);
	}

	protected void initArgsConverter(TypeConverterManager manager) {
		Class<?>[] types = method.getParameterTypes();
		convs = getArgsConverter(types, manager);
	}

	protected TypeConverter[] getArgsConverter(Class<?>[] types,
			TypeConverterManager manager) {
		TypeConverter[] convs = new TypeConverter[types.length];
		for (int i = 0; i < convs.length; i++) {
			TypeConverter conv = manager.getConverter(types[i]);
			if (conv == null) {
				StringBuilder sb = new StringBuilder(64);
				sb.append(name);
				sb.append(" class=");
				sb.append(types[i]);
				throw new UnsupportedOperationException(sb.toString());
			}
			convs[i] = conv;
		}
		return convs;
	}

	/**
	 * ���O�擾.
	 *
	 * @return ���O
	 */
	public String getName() {
		return name;
	}

	/**
	 * �����̌^�ϊ��I�u�W�F�N�g�擾.
	 *
	 * @return �^�ϊ��I�u�W�F�N�g
	 */
	public TypeConverter[] getTypeConverter() {
		return convs;
	}

	/**
	 * ���\�b�h�Ăяo��.
	 * <p>
	 * ���Y�I�u�W�F�N�g�ŊǗ�����Ă��郁�\�b�h���Ăяo���B<br>
	 * �ΏۃI�u�W�F�N�g�́A���Y�I�u�W�F�N�g�̃N���X�ɕϊ�����B<br>
	 * �e�������A���Y���\�b�h�̈����̌^�ɕϊ�����B
	 * </p>
	 *
	 * @param obj
	 *            ����ΏۃI�u�W�F�N�g
	 * @param args
	 *            ���\�b�h�̈���
	 * @return �Ăяo�����l
	 * @throws IllegalArgumentLengthException
	 *             �����̌������Y���\�b�h�̌��ƈ�v���Ȃ��ꍇ
	 * @throws Exception
	 *             ���\�b�h�Ăяo�����̗�O
	 */
	public Object invoke(Object obj, Object... args) throws Exception {
		checkArgs(args);
		obj = objectConvert(obj);

		Object[] cargs = new Object[convs.length];
		for (int i = 0; i < convs.length; i++) {
			TypeConverter conv = convs[i];
			cargs[i] = conv.convert(args[i]);
		}

		return method.invoke(obj, cargs);
	}

	protected void checkArgs(Object... args) {
		if (args.length != convs.length) {
			throw new IllegalArgumentLengthException(name, args.length,
					convs.length, convs.length);
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> T objectConvert(Object obj) {
		if (isStatic()) {
			return null;
		}
		if (objConv != null) {
			obj = objConv.convert(obj);
		}
		return (T) obj;
	}

	protected boolean isStatic() {
		if (method == null) {
			return false;
		}
		return Modifier.isStatic(method.getModifiers());
	}
}