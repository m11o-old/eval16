package jp.hishidama.eval.var;

import jp.hishidama.eval.exp.AbstractExpression;

/**
 * �ϐ��C���^�[�t�F�[�X.
 * <p>
 * �ϐ��ƕϐ��l���Ǘ�����C���^�[�t�F�[�X�B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2007.02.15
 * @version eval16
 */
public interface Variable {

	/**
	 * �ϐ��l�擾.
	 * <p>
	 * �ϐ���\���I�u�W�F�N�g��Ԃ��B�i�P�Ȃ�ϐ���z���z��j
	 * </p>
	 *
	 * @param name
	 *            �ϐ���
	 * @return �ϐ��I�u�W�F�N�g
	 */
	public Object getValue(Object name);

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
	public void setValue(Object name, Object value);

	/**
	 * �ϐ��l�擾.
	 * <p>
	 * �z��̗v�f��Ԃ��B
	 * </p>
	 *
	 * @param array
	 *            �z��I�u�W�F�N�g
	 * @param arrayName
	 *            �z��
	 * @param index
	 *            �Y��
	 * @param exp
	 *            �����\�b�h���Ăяo�������N���X
	 * @return �v�f�I�u�W�F�N�g
	 */
	public Object getArrayValue(Object array, String arrayName, Object index,
			AbstractExpression exp);

	/**
	 * �z��l�ݒ�.
	 * <p>
	 * �z��ɒl���Z�b�g����B
	 * </p>
	 *
	 * @param array
	 *            �z��I�u�W�F�N�g
	 * @param arrayName
	 *            �z��
	 * @param index
	 *            �Y��
	 * @param value
	 *            �l
	 * @param exp
	 *            �����\�b�h���Ăяo�������N���X
	 */
	public void setArrayValue(Object array, String arrayName, Object index,
			Object value, AbstractExpression exp);

	/**
	 * �t�B�[���h�l�擾.
	 * <p>
	 * �I�u�W�F�N�g�̃t�B�[���h�̒l��Ԃ��B
	 * </p>
	 *
	 * @param obj
	 *            �I�u�W�F�N�g
	 * @param objName
	 *            �I�u�W�F�N�g��
	 * @param field
	 *            �t�B�[���h��
	 * @param exp
	 *            �����\�b�h���Ăяo�������N���X
	 * @return �v�f�I�u�W�F�N�g
	 */
	public Object getFieldValue(Object obj, String objName, String field,
			AbstractExpression exp);

	/**
	 * �t�B�[���h�l�ݒ�.
	 * <p>
	 * �I�u�W�F�N�g�̃t�B�[���h�ɒl���Z�b�g����B
	 * </p>
	 *
	 * @param obj
	 *            �I�u�W�F�N�g
	 * @param objName
	 *            �I�u�W�F�N�g��
	 * @param field
	 *            �t�B�[���h��
	 * @param value
	 *            �l
	 * @param exp
	 *            �����\�b�h���Ăяo�������N���X
	 */
	public void setFieldValue(Object obj, String objName, String field,
			Object value, AbstractExpression exp);
}
