package jp.hishidama.eval.lex.comment;

/**
 * �s�R�����g���߃N���X.
 * <p>
 * �s�R�����g�i��F//�`�j�̊J�n�������ێ�����B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since eval16
 */
public class LineComment extends CommentLex {

	/**
	 * �R���X�g���N�^�[.
	 *
	 * @param top
	 *            �J�n������inull�s�j
	 */
	public LineComment(String top) {
		super(top);
	}

	@Override
	public int isEnd(String string, int pos) {
		if (pos >= string.length()) {
			return 0; // �s�R�����g�ł́A������̖����܂ŗ����琳��ɃR�����g�I��
		}
		char c = string.charAt(pos);
		switch (c) {
		case '\r':
			if (pos + 1 < string.length()) {
				c = string.charAt(pos + 1);
				if (c == '\n') {
					return 2;
				}
			}
			return 1;
		case '\n':
			return 1;
		}
		return -1;
	}
}
