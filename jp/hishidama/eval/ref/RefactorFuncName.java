package jp.hishidama.eval.ref;

/**
 * �֐����ύX���t�@�N�^�����O�N���X.
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2007.02.19
 */
public class RefactorFuncName extends RefactorAdapter {

	protected Class<?> targetClass;

	protected String oldName;

	protected String newName;

	public RefactorFuncName(Class<?> targetClass, String oldName, String newName) {
		this.targetClass = targetClass;
		this.oldName = oldName;
		this.newName = newName;
		if (oldName == null || newName == null) {
			throw new NullPointerException();
		}
	}

	@Override
	public String getNewFuncName(Object target, String name) {
		if (!name.equals(oldName)) {
			return null;
		}
		if (targetClass == null) {
			if (target == null) {
				return newName;
			}
		} else {
			if (target != null
					&& targetClass.isAssignableFrom(target.getClass())) {
				return newName;
			}
		}
		return null;
	}

}
