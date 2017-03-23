package jp.hishidama.eval.ref;

/**
 * ���t�@�N�^�����O�C���^�[�t�F�[�X.
 * <p>
 * ���t�@�N�^�����O�̓��e����������C���^�[�t�F�[�X�B
 * </p>
 * 
 * @see jp.hishidama.eval.Expression#refactorName(Refactor)
 * @see jp.hishidama.eval.Expression#refactorFunc(Refactor, Rule)
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html">�Ђ�����</a>
 * @since 2007.02.19
 */
public interface Refactor {

	/**
	 * �V���̎擾.
	 * <p>
	 * ���t�@�N�^�����O�ŕύX����V���̂�Ԃ��B
	 * </p>
	 * <p>
	 * �ϐ�������уI�u�W�F�N�g�̃t�B�[���h���ɑ΂��ē����\�b�h���Ă΂��B<br>
	 * ���̖��̂�ύX����ꍇ�͐V���̂�Ԃ��B�ύX���Ȃ��ꍇ��null��Ԃ��B
	 * </p>
	 * <p>
	 * �I�u�W�F�N�g���܂�ł���ꍇ�́A���̃C���X�^���X��Ԃ��ׂ̕ϐ��C���^�[�t�F�[�X��o�^����K�v������B
	 * </p>
	 * 
	 * @param target
	 *            �u���O�v���I�u�W�F�N�g�̃t�B�[���h�ł���Ƃ��A���̃I�u�W�F�N�g�B����ȊO�̏ꍇ��null
	 * @param name
	 *            ���O
	 * @return �V���́i�ύX���Ȃ��ꍇ��null�j
	 */
	public String getNewName(Object target, String name);

	/**
	 * �V�֐����擾.
	 * <p>
	 * ���t�@�N�^�����O�ŕύX����V�֐�����Ԃ��B
	 * </p>
	 * <p>
	 * �֐�������уI�u�W�F�N�g�̃��\�b�h���ɑ΂��ē����\�b�h���Ă΂��B<br>
	 * ���̖��̂�ύX����ꍇ�͐V���̂�Ԃ��B�ύX���Ȃ��ꍇ��null��Ԃ��B
	 * </p>
	 * <p>
	 * �I�u�W�F�N�g���܂�ł���ꍇ�́A���̃C���X�^���X��Ԃ��ׂ̕ϐ��C���^�[�t�F�[�X��o�^����K�v������B
	 * </p>
	 * <p>
	 * ���݂̂Ƃ���A�֐��̈����͍l�����Ȃ��B�i�I�[�o�[���[�h�����̓���̊֐����͑S�ĕύX�����j
	 * </p>
	 * 
	 * @param target
	 *            �u���O�v���I�u�W�F�N�g�̃t�B�[���h�ł���Ƃ��A���̃I�u�W�F�N�g�B����ȊO�̏ꍇ��null
	 * @param name
	 *            �֐���
	 * @return �V�֐����i�ύX���Ȃ��ꍇ��null�j
	 */
	public String getNewFuncName(Object target, String name);
}
