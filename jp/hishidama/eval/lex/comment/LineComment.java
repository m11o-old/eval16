package jp.hishidama.eval.lex.comment;

/**
 * 行コメント解釈クラス
 * <p>
 * 行コメント（例：//～）の開始文字列を保持する
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >ひしだま</a>
 * @since eval16
 */
public class LineComment extends CommentLex {

	/**
	 * コンストラクター
	 *
	 * @param top
	 *            開始文字列（null不可）
	 */
	public LineComment(String top) {
		super(top);
	}

	@Override
	public int isEnd(String string, int pos) {
		if (pos >= string.length()) {
			return 0; // �s�R�����g�ł́A�������̖����܂ŗ����琳���ɃR�����g�I��
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
