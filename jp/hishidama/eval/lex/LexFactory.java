package jp.hishidama.eval.lex;

import java.util.ArrayList;
import java.util.List;

import jp.hishidama.eval.exp.ShareExpValue;
import jp.hishidama.eval.lex.comment.BlockComment;
import jp.hishidama.eval.lex.comment.CommentLex;
import jp.hishidama.eval.lex.comment.LineComment;
import jp.hishidama.eval.rule.ShareRuleValue;

/**
 * Lex�t�@�N�g���[.
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since 2007.02.16
 * @version eval16
 */
public class LexFactory {

	/** �f�t�H���g�R�����g������ꗗ */
	protected List<CommentLex> defaultCommentList = new ArrayList<CommentLex>();

	/**
	 * �R���X�g���N�^�[.
	 */
	public LexFactory() {
		initCommentList();
	}

	protected void initCommentList() {
		defaultCommentList.add(new BlockComment("/*", "*/"));
		defaultCommentList.add(new LineComment("//"));
	}

	/**
	 * �f�t�H���g�R�����g������ݒ�.
	 *
	 * @param list
	 *            �R�����g������ꗗ
	 * @since eval16
	 */
	public void setDefaultCommentLexList(List<CommentLex> list) {
		defaultCommentList = list;
	}

	/**
	 * Lex�C���X�^���X����.
	 *
	 * @param str
	 *            �����͑Ώە�����
	 * @param opeList
	 *            ���Z�q�Q
	 * @param share
	 *            ���[�����ʏ��
	 * @param exp
	 *            �\����͖؋��ʏ��
	 * @return Lex�C���X�^���X
	 */
	public Lex create(String str, List<String>[] opeList, ShareRuleValue share,
			ShareExpValue exp) {
		Lex lex = new Lex(str, opeList, share.paren, exp);
		if (defaultCommentList != null) {
			lex.setCommentLexList(defaultCommentList);
		}
		return lex;
	}
}
