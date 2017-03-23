package jp.hishidama.eval.func;

/**
 * ���null��Ԃ��֐�.
 * <p>
 * �߂�l�Ƃ��ď��null��Ԃ��B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @version eval16
 */
public class VoidFunction implements Function {

	protected boolean dump;

	/**
	 * �R���X�g���N�^�[.
	 * <p>
	 * �R���\�[���o�͂��s��Ȃ��B
	 * </p>
	 */
	public VoidFunction() {
		this(false);
	}

	/**
	 * �R���X�g���N�^�[.
	 *
	 * @param dump
	 *            �o�͗L���itrue�̏ꍇ�A�֐����Ă΂ꂽ�ۂɊ֐����ƈ������R���\�[���ɏo�͂���j
	 */
	public VoidFunction(boolean dump) {
		this.dump = dump;
	}

	@Override
	public Object eval(String name, Object[] args) throws Exception {
		if (dump) {
			System.out.println(name + "�֐����Ă΂ꂽ");
			for (int i = 0; i < args.length; i++) {
				System.out.println("arg[" + i + "] " + args[i]);
			}
		}
		return null;

	}

	@Override
	public Object eval(Object object, String name, Object[] args)
			throws Exception {
		if (dump) {
			System.out.println(object + "." + name + "�֐����Ă΂ꂽ");
			for (int i = 0; i < args.length; i++) {
				System.out.println("arg[" + i + "] " + args[i]);
			}
		}

		return null;
	}
}
