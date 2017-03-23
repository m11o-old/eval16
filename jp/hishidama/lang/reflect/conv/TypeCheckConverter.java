package jp.hishidama.lang.reflect.conv;

/**
 * �ėp�^�ϊ��N���X.
 * <p>
 * �w�肳�ꂽ�^�ɃL���X�g����B<br>
 * �i���������āA�^����v���Ȃ��I�u�W�F�N�g�̏ꍇ��ClassCastException����������j
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2010.02.16
 */
public class TypeCheckConverter extends TypeConverter {

	protected Class<?> clazz;

	public TypeCheckConverter(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public int match(Object obj) {
		if (obj == null) {
			return MATCH_NULL;
		}

		if (clazz.isInstance(obj)) {
			return MATCH_EQUALS;
		}
		return UNMATCH;
	}

	@Override
	public Object convert(Object object) {
		if (object == null) {
			return null;
		}
		if (!clazz.isInstance(object)) {
			throw new ClassCastException(object.getClass().getName()
					+ " cannot be cast to " + clazz.getName());
		}
		return object;
	}
}
