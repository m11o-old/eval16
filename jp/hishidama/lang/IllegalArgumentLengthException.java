package jp.hishidama.lang;

/**
 * �����̌����Ԉ���Ă���ꍇ�ɃX���[������O
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2010.02.16
 */
public class IllegalArgumentLengthException extends IllegalArgumentException {
	private static final long serialVersionUID = -1652752633469109078L;

	protected int len;
	protected int min;
	protected int max;

	/**
	 * �R���X�g���N�^�[.
	 *
	 * @param len
	 *            �����̌�
	 * @param min
	 *            ���e���̍ŏ��l
	 * @param max
	 *            ���e���̍ő�l
	 */
	public IllegalArgumentLengthException(int len, int min, int max) {
		this.len = len;
		this.min = min;
		this.max = max;
	}

	/**
	 * �R���X�g���N�^�[.
	 *
	 * @param message
	 *            ���b�Z�[�W
	 * @param len
	 *            �����̌�
	 * @param min
	 *            ���e���̍ŏ��l
	 * @param max
	 *            ���e���̍ő�l
	 */
	public IllegalArgumentLengthException(String message, int len, int min,
			int max) {
		super(message);
		this.len = len;
		this.min = min;
		this.max = max;
	}

	/**
	 * �����̌��擾.
	 *
	 * @return �����̌�
	 */
	public int getLen() {
		return len;
	}

	/**
	 * �����̌��ݒ�.
	 *
	 * @param len
	 *            �����̌�
	 */
	public void setLen(int len) {
		this.len = len;
	}

	/**
	 * ���e���̍ŏ��l�擾.
	 *
	 * @return �ŏ���
	 */
	public int getMin() {
		return min;
	}

	/**
	 * ���e���̍ŏ��l�ݒ�.
	 *
	 * @param min
	 *            �ŏ���
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * ���e���̍ő�l�擾.
	 *
	 * @return �ő��
	 */
	public int getMax() {
		return max;
	}

	/**
	 * ���e���̍ő�l�ݒ�.
	 *
	 * @param max
	 *            �ő��
	 */
	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public String getMessage() {
		String msg = super.getMessage();
		if (msg == null) {
			msg = "";
		}
		msg += " args.length=" + len + " (" + min + ".." + max + ")";
		return msg;
	}
}
