package jp.hishidama.eval.var;

import java.util.*;

/**
 * �ϐ��Ǘ��N���X.
 * <p>
 * �ϐ��ƕϐ��l���Ǘ�����B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2007.02.13
 * @version eval16
 */
public class MapVariable<K, V> extends DefaultVariable {

	protected Map<K, V> map;

	/**
	 * �R���X�g���N�^�[.
	 * <p>
	 * ��̕ϐ��}�b�v���쐬����B
	 * </p>
	 */
	public MapVariable() {
		this(new HashMap<K, V>());
	}

	/**
	 * �R���X�g���N�^�[.
	 * <p>
	 * ��̕ϐ��}�b�v���쐬����B�i���I�^�ۏ؂��s���j
	 * </p>
	 *
	 * @param keyType
	 *            �L�[�̌^
	 * @param valueType
	 *            �l�̌^
	 * @see Collections#checkedMap(Map, Class, Class)
	 * @since eval16
	 */
	public MapVariable(Class<K> keyType, Class<V> valueType) {
		this(Collections.checkedMap(new HashMap<K, V>(), keyType, valueType));
	}

	/**
	 * �R���X�g���N�^�[.
	 *
	 * @param varMap
	 *            �ϐ��}�b�v
	 */
	public MapVariable(Map<K, V> varMap) {
		map = varMap;
	}

	/**
	 * �ϐ��}�b�v�ݒ�.
	 * <p>
	 * �ϐ���(String)�ƒl�̃}�b�v��ݒ肷��B
	 * </p>
	 * <p>
	 * �l�����l�̏ꍇ��Number�iLong��Double�j�ł��邱�ƁB<br>
	 * varMap.put("var", new Long(1));
	 * </p>
	 * <p>
	 * �l������ȊO�i�z��j�̏ꍇ��Java�̃I�u�W�F�N�g�����̂܂܊i�[����B<br>
	 * Long[] arr = new Long[2]; varMap.put("arr", arr);
	 * </p>
	 *
	 * @param varMap
	 *            �ϐ��}�b�v
	 */
	public void setMap(Map<K, V> varMap) {
		map = varMap;
	}

	/**
	 * �ϐ��}�b�v�擾.
	 *
	 * @return �ϐ��}�b�v
	 */
	public Map<K, V> getMap() {
		return map;
	}

	/**
	 * �l�ݒ�.
	 *
	 * @param name
	 *            �ϐ���
	 * @param value
	 *            �l
	 */
	public void put(K name, V value) {
		map.put(name, value);
	}

	/**
	 * �l�擾.
	 *
	 * @param name
	 *            �ϐ���
	 * @return �l
	 */
	public V get(K name) {
		return map.get(name);
	}

	/**
	 * �ϐ��I�u�W�F�N�g�擾.
	 * <p>
	 * �ϐ���\���I�u�W�F�N�g��Ԃ��B�i�P�Ȃ�ϐ���z���z��j
	 * </p>
	 *
	 * @param name
	 *            �ϐ���
	 * @return �ϐ��I�u�W�F�N�g
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getValue(Object name) {
		return get((K) name);
	}

	/**
	 * �ϐ��ݒ�.
	 * <p>
	 * �ϐ��ɒl���Z�b�g����B
	 * </p>
	 *
	 * @param name
	 *            �ϐ���
	 * @param value
	 *            �l
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setValue(Object name, Object value) {
		put((K) name, (V) value);
	}
}
