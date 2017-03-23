package jp.hishidama.eval.lex.comment;

/**
 * �u���b�N�R�����g���߃N���X.
 * <p>
 * �u���b�N�R�����g�i��F/&#x2a;�`&#x2a;/�j�̊J�n�E�I���������ێ�����B<br>
 * �u���b�N�R�����g�̃l�X�g�͏o���Ȃ��B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since eval16
 */
public class BlockComment extends CommentLex {

	/** �I�������� */
	protected String end;

	/**
	 * �R���X�g���N�^�[.
	 *
	 * @param top
	 *            �J�n������inull�s�j
	 * @param end
	 *            �I��������inull�s�j
	 */
	public BlockComment(String top, String end) {
		super(top);
		this.end = end;
	}

	/**
	 * �I��������擾.
	 *
	 * @return �I��������
	 */
	public String getEndString() {
		return end;
	}

	@Override
	public int isEnd(String string, int pos) {
		return is(end, string, pos);
	}
}
