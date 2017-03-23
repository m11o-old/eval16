package jp.hishidama.eval.lex.comment;

/**
 * �R�����g���߃N���X.
 * <p>
 * �R�����g�̊J�n�E�I�������߂���B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since eval16
 */
public abstract class CommentLex {

	/** �J�n������ */
	protected String top;

	/**
	 * �R���X�g���N�^�[.
	 *
	 * @param top
	 *            �J�n������inull�s�j
	 */
	protected CommentLex(String top) {
		this.top = top;
	}

	/**
	 * �J�n������ƈ�v���Ă��邩.
	 *
	 * @param string
	 *            �Ώە�����
	 * @param pos
	 *            �ʒu
	 * @return 0�ȏ�F��v���Ă��镶�����A���F��v���Ă��Ȃ�
	 */
	public int isTop(String string, int pos) {
		return is(top, string, pos);
	}

	/**
	 * �I��������ƈ�v���Ă��邩.
	 *
	 * @param string
	 *            �Ώە�����
	 * @param pos
	 *            �ʒu
	 * @return 0�ȏ�F��v���Ă��镶�����A���F��v���Ă��Ȃ�
	 */
	public abstract int isEnd(String string, int pos);

	protected int is(String ope, String string, int pos) {
		int size = ope.length();
		for (int i = 0; i < size; i++, pos++) {
			if (pos >= string.length()) {
				return -1;
			}
			char c = string.charAt(pos);
			char d = ope.charAt(i);
			if (c != d) {
				return -1;
			}
		}
		return size;
	}

	/**
	 * �J�n������擾.
	 *
	 * @return �J�n������
	 */
	public String getTopString() {
		return top;
	}

	/**
	 * �J�n�����񒷎擾.
	 *
	 * @return �J�n������̒���
	 */
	public int topLength() {
		return top.length();
	}

	/**
	 * �R�����g�X�L�b�v.
	 *
	 * @param string
	 *            �Ώە�����
	 * @param pos
	 *            �X�L�b�v�J�n�ʒu
	 * @return �R�����g�̎��̕����̈ʒu�i���̏ꍇ�A�R�����g���I�������ɕ����񂪏I������j
	 */
	public int skip(String string, int pos) {
		for (;;) {
			if (pos > string.length()) {
				return -1; // ������̍Ō�܂ŗ���
			}
			int e = isEnd(string, pos);
			if (e < 0) {
				pos++;
				continue;
			}
			return pos + e;
		}
	}
}
