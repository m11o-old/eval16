package jp.hishidama.lang.reflect.conv;

import java.lang.reflect.Array;

/**
 * �z��ϊ��N���X.
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2010.02.16
 */
public class ArrayConverter extends TypeConverter {

	/** �z��N���X */
	protected Class<?> clazz;

	/** �z��̗v�f�̃R���o�[�^�[ */
	protected TypeConverter conv;

	/**
	 * �R���X�g���N�^�[.
	 *
	 * @param clazz
	 *            �z��N���X
	 * @param manager
	 *            �^�ϊ��I�u�W�F�N�g�Ǘ��N���X
	 */
	public ArrayConverter(Class<?> clazz, TypeConverterManager manager) {
		assert clazz.isArray() : clazz;
		this.clazz = clazz;
		this.conv = manager.getConverter(clazz.getComponentType());
	}

	@Override
	public int match(Object obj) {
		if (obj == null) {
			return MATCH_NULL;
		}

		if (clazz.isInstance(obj)) {
			if (clazz.getComponentType() == obj.getClass().getComponentType()) {
				return MATCH_EQUALS;
			}
			return MATCH_EQUALS - 1;
		}
		if (obj.getClass().isArray()) {
			int r = MATCH_EQUALS / 2;
			int sz = Array.getLength(obj);
			for (int i = 0; i < sz; i++) {
				Object elem = Array.get(obj, i);
				if (elem == null) {
					continue;
				}
				int t = conv.match(elem);
				if (t <= UNMATCH) {
					return UNMATCH;
				}
				r = Math.min(r, t);
			}
			return r;
		}

		return UNMATCH;
	}

	@Override
	public Object convert(Object object) {
		if (object == null) {
			return null;
		}
		if (clazz.isInstance(object)) {
			return object;
		}
		if (object.getClass().isArray()) {
			int sz = Array.getLength(object);
			Object arr = Array.newInstance(clazz.getComponentType(), sz);
			for (int i = 0; i < sz; i++) {
				Array.set(arr, i, conv.convert(Array.get(object, i)));
			}
			return arr;
		}

		// �z��łȂ��ꍇ�A���̃I�u�W�F�N�g��v�f�Ƃ���z������
		Object arr = Array.newInstance(clazz.getComponentType(), 1);
		Array.set(arr, 0, conv.convert(object));
		return arr;
	}
}
