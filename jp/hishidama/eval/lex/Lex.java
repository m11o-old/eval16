package jp.hishidama.eval.lex;

import java.util.*;

import jp.hishidama.eval.exp.AbstractExpression;
import jp.hishidama.eval.exp.ShareExpValue;
import jp.hishidama.eval.lex.comment.CommentLex;
import jp.hishidama.util.CharUtil;

/**
 * 字句解析クラス
 * <p>
 * 文字列の解析中の位置を保持する
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >ひしだま</a>
 * @version eval16
 */
public class Lex {

	/**
	 * ���Z�q�Q.
	 * <p>
	 * �������͂ŉ��Z�q�ƔF�����镶�����B ���Z�q�̕������ɂ����Ĕz���𕪂��Ă����B �i�������Z�q�������Ƀ}�b�`�������ׁj
	 * </p>
	 */
	protected List<String>[] opeList;

	/** ���͑Ώە�����. */
	protected String string;

	/** ���͒��̌��݈ʒu. */
	protected int pos = 0;

	/** ���݂̉��ߌ��ʂ̕������̒���. */
	protected int len = 0;

	/** ���݂̉��ߌ��ʂ��\���^�C�v. */
	protected int type = TYPE_ERR;

	/** 解釈タイプ：識別子 */
	public static final int TYPE_WORD = 0x7ffffff0;

	/**
	 * 解釈タイプ：識別子(数値)
	 *
	 * @since 2007.02.15
	 */
	public static final int TYPE_NUM = 0x7ffffff1;

	/** 解釈タイプ：演算子 */
	public static final int TYPE_OPE = 0x7ffffff2;

	/**
	 * 解釈タイプ：識別子(文字列)
	 *
	 * @since 2007.02.21
	 */
	public static final int TYPE_STRING = 0x7ffffff3;

	/**
	 * 解釈タイプ：識別子(文字)
	 *
	 * @since 2007.02.21
	 */
	public static final int TYPE_CHAR = 0x7ffffff4;

	/** 解釈タイプ：解釈終了 */
	public static final int TYPE_EOF = 0x7fffffff;

	/** 解釈タイプ：エラー */
	public static final int TYPE_ERR = -1;

	/**
	 * ���Z�q�̕�����.
	 * <p>
	 * �^�C�v�����Z�q�̂Ƃ��̂ݗL���B
	 * </p>
	 */
	protected String ope;

	/**
	 * Expression�̋��L����.
	 *
	 * @since 2007.02.09
	 */
	protected ShareExpValue expShare;

	/**
	 * �R���X�g���N�^�[.
	 *
	 * @param str
	 *            �Ώە�����
	 * @param exp
	 */
	protected Lex(String str, List<String>[] lists, AbstractExpression paren,
			ShareExpValue exp) {
		this.string = str;
		this.opeList = lists;
		expShare = exp;
		if (expShare.paren == null) {
			expShare.paren = paren;
		}
	}

	/**
	 * �󔒕����Q.
	 * <p>
	 * �󔒂Ɖ��߂��ăX�L�b�v���镶���B
	 * </p>
	 */
	protected String SPC_CHAR = " \t\r\n";

	/**
	 * �󔒔��f.
	 * <p>
	 * �w�肳�ꂽ�ʒu�̕������󔒂��ǂ����`�F�b�N�����B<br>
	 * �������̒����𒴂��Ă����ꍇ���󔒂Ƃ����B
	 * </p>
	 *
	 * @param pos
	 *            �ʒu
	 * @return �󔒂̂Ƃ��Atrue
	 * @see #SPC_CHAR
	 */
	protected boolean isSpace(int pos) {
		if (pos >= string.length()) {
			return true;
		}
		char c = string.charAt(pos);
		return SPC_CHAR.indexOf(c) >= 0;
	}

	/**
	 * コメント文字列一覧
	 */
	protected List<CommentLex> commentList = null;

	/**
	 * コメント文字列一覧設定
	 *
	 * @param list
	 *            コメント文字列一覧
	 * @since eval16
	 */
	public void setCommentLexList(List<CommentLex> list) {
		commentList = list;
	}

	/**
	 * コメント文字列一覧取得
	 *
	 * @return コメント文字列一覧
	 * @since eval16
	 */
	public List<CommentLex> getCommentLexList() {
		return commentList;
	}

	/**
	 * �R�����g���f.
	 *
	 * @param pos
	 *            �ʒu
	 * @return �R�����g�̂Ƃ��A���̃R�����g�����߂����I�u�W�F�N�g
	 * @since 2010.02.11
	 */
	protected CommentLex isCommentTop(int pos) {
		if (commentList != null) {
			for (CommentLex c : commentList) {
				if (c.isTop(string, pos) >= 0) {
					return c;
				}
			}
		}
		return null;
	}

	/**
	 * ���l�擪�������f.
	 * <p>
	 * �������̐擪�������ł����ΐ��l�Ɣ��f�����̂ŁA���̐擪�������`�F�b�N�����B
	 * </p>
	 *
	 * @param pos
	 *            �ʒu
	 * @return �����̂Ƃ��Atrue
	 * @since 2007.02.15
	 */
	protected boolean isNumberTop(int pos) {
		if (pos >= string.length()) {
			return false;
		}
		char c = string.charAt(pos);
		return ('0' <= c && c <= '9');
	}

	/**
	 * ������������.
	 * <p>
	 * ���Z�q�Ƃ��Ďg���ꂻ���ȋL�������A�����Ƃ��Ĉ��������B�����_�ȂǁB
	 * </p>
	 *
	 * @since 2007.02.15
	 */
	protected String NUMBER_CHAR = "._";

	/**
	 * ���ꐔ�����f.
	 * <p>
	 * ���Z�q�Ƃ��Ďg���ꂻ���ȋL�������A�����Ƃ��Ĉ��������ȕ������ǂ������`�F�b�N�����B<br>
	 * �Ⴆ�΃s���I�h�u.�v�̓I�u�W�F�N�g�ƃ����o�[�̋��؂��Ɏg�����A���l�̒��Ŏg���ꂽ�Ƃ��͏����_�Ƃ��ėD���I�Ɉ����K�v�������B
	 * </p>
	 *
	 * @param pos
	 *            �ʒu
	 * @return ���������̂Ƃ��Atrue
	 * @see #NUMBER_CHAR
	 * @since 2007.02.15
	 */
	protected boolean isSpecialNumber(int pos) {
		if (pos >= string.length()) {
			return false;
		}
		char c = string.charAt(pos);
		return NUMBER_CHAR.indexOf(c) >= 0;
	}

	/**
	 * 演算子比較
	 * <p>
	 * �w�肳�ꂽ�ʒu�̕��������Z�q���ǂ����`�F�b�N�����B
	 * </p>
	 *
	 * @param pos
	 *            演算子
	 * @return 現在位置が引数と等しい演算子のとき、true
	 */
	protected String isOperator(int pos) {
		// ���Z�q�̕������̑����������`�F�b�N
		for (int i = opeList.length - 1; i >= 0; i--) {
			if (pos + i >= string.length()) {
				continue;
			}
			List<String> list = opeList[i];
			if (list != null) {
				ope: for (int j = 0; j < list.size(); j++) {
					String ope = list.get(j);
					for (int k = 0; k <= i; k++) {
						char c = string.charAt(pos + k);
						char o = ope.charAt(k);
						if (c != o) {
							continue ope;
						}
					}
					return ope;
				}
			}
		}
		return null;
	}

	/**
	 * �������擪�������f.
	 *
	 * @param pos
	 *            �ʒu
	 * @return �����񂩂ǂ���
	 * @since 2007.02.21
	 */
	protected boolean isStringTop(int pos) {
		if (pos >= string.length()) {
			return false;
		}
		char c = string.charAt(pos);
		return c == '\"';
	}

	/**
	 * �������I���������f.
	 *
	 * @param pos
	 *            �ʒu
	 * @return �����񂩂ǂ���
	 * @since 2007.02.21
	 */
	protected boolean isStringEnd(int pos) {
		return isStringTop(pos);
	}

	/**
	 * �����擪�������f.
	 *
	 * @param pos
	 *            �ʒu
	 * @return �������ǂ���
	 * @since 2007.02.21
	 */
	protected boolean isCharTop(int pos) {
		if (pos >= string.length()) {
			return false;
		}
		char c = string.charAt(pos);
		return c == '\'';
	}

	/**
	 * �����I���������f.
	 *
	 * @param pos
	 *            �ʒu
	 * @return �������ǂ���
	 * @since 2007.02.21
	 */
	protected boolean isCharEnd(int pos) {
		return isCharTop(pos);
	}

	/**
	 * 現在位置解釈
	 * <p>
	 * 現在の位置を解釈し、内部状態をセットする
	 * </p>
	 */
	public void check() {
		// �󔒔��f
		for (;;) {
			if (isSpace(pos)) {
				if (pos >= string.length()) {
					// �������𒴂������I��
					type = TYPE_EOF;
					len = 0;
					return;
				}
				pos++;
				continue;
			}
			CommentLex comment = isCommentTop(pos);
			if (comment != null) {
				pos += comment.topLength();
				int end = comment.skip(string, pos);
				if (end < 0) {
					type = TYPE_EOF;
					return;
				}
				pos = end;
				continue;
			}
			break;
		}

		// �����񔻒f
		if (isStringTop(pos)) {
			processString();
			return;
		}
		// �������f
		if (isCharTop(pos)) {
			processChar();
			return;
		}

		// ���Z�q���f
		String ope = isOperator(pos);
		if (ope != null) {
			this.type = TYPE_OPE;
			this.ope = ope;
			this.len = ope.length();
			return;
		}

		// ���L�ȊO�͎��ʎq
		boolean number = isNumberTop(pos);
		type = number ? TYPE_NUM : TYPE_WORD;
		for (len = 1;; len++) {
			if (isSpace(pos + len) || isCommentTop(pos + len) != null) {
				break;
			}
			if (number && isSpecialNumber(pos + len)) {
				continue;
			}
			if (isOperator(pos + len) != null) {
				break;
			}
		}
	}

	protected void processString() {
		int[] ret = new int[1];
		type = TYPE_STRING;
		for (len = 1;;) {
			if (isStringEnd(pos + len)) {
				len++;
				return;
			}
			len += getCharLen(pos + len, ret);
			if (pos + len >= string.length()) {
				type = TYPE_EOF;
				break;
			}
		}
	}

	protected void processChar() {
		int[] ret = new int[1];
		type = TYPE_CHAR;
		for (len = 1;;) {
			if (isCharEnd(pos + len)) {
				len++;
				return;
			}
			len += getCharLen(pos + len, ret);
			if (pos + len >= string.length()) {
				type = TYPE_EOF;
				break;
			}
		}
	}

	protected int getCharLen(int pos, int[] ret) {
		CharUtil.escapeChar(string, pos, string.length(), ret);
		return ret[0];
	}

	/**
	 * 次位置解釈
	 * <p>
	 * 次の位置へ移動し、解釈して内部状態をセットする
	 * </p>
	 *
	 * @return 自分自身
	 */
	public Lex next() {
		pos += len;
		check();
		return this;
	}

	/**
	 * タイプ取得
	 *
	 * @return タイプ
	 */
	public int getType() {
		return type;
	}

	/**
	 * 演算子取得
	 * <p>
	 * タイプが演算子のときだけ有効
	 * </p>
	 *
	 * @return 演算子
	 */
	public String getOperator() {
		return ope;
	}

	/**
	 * 演算子比較
	 *
	 * @param ope
	 *            演算子
	 * @return 現在位置が引数と等しい演算子のとき、true
	 */
	public boolean isOperator(String ope) {
		if (type == TYPE_OPE) {
			return this.ope.equals(ope);
		}
		return false;
	}

	/**
	 * 識別子取得
	 *
	 * @return 識別子
	 */
	public String getWord() {
		return string.substring(pos, pos + len);
	}

	/**
	 * 文字列取得
	 * <p>
	 * 解釈中の文字列全体を返す
	 * </p>
	 *
	 * @return 文字列
	 */
	public String getString() {
		return string;
	}

	/**
	 * 位置取得
	 *
	 * @return 解釈中の位置
	 */
	public int getPos() {
		return pos;
	}

	/**
	 * Expression共有情報取得
	 *
	 * @return 共有情報
	 * @since 2007.02.09
	 */
	public ShareExpValue getShare() {
		return expShare;
	}
}
