package jp.hishidama.lang.reflect.conv;

import java.util.Map;

/**
 * Map�ϊ��N���X.
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2010.02.16
 */
public class MapConverter extends TypeConverter {

	public static final MapConverter INSTANCE = new MapConverter();

	@Override
	public int match(Object obj) {
		if (obj == null) {
			return MATCH_NULL;
		}

		if (obj instanceof Map) {
			return MATCH_EQUALS;
		}
		return UNMATCH;
	}

	@Override
	public Map<?, ?> convert(Object object) {
		if (object == null) {
			return null;
		}
		return (Map<?, ?>) object; // Map�łȂ��������O����
	}
}
