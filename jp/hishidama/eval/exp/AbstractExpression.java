package jp.hishidama.eval.exp;

import java.util.*;

import jp.hishidama.eval.EvalException;

/**
 * 式の抽象クラス
 * <p>
 * 構文解析木を構成し、演算の評価を実施する
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >ひしだま</a>
 * @version eval16
 */
public abstract class AbstractExpression {

	/**
	 * �S�̕�����.
	 * <p>
	 * ���ߑΏۑS�̂̕������B
	 * </p>
	 */
	protected String string = null;

	/**
	 * �ʒu.
	 * <p>
	 * �S�̕��������̈ʒu�B
	 * </p>
	 */
	protected int pos = -1;

	private String ope1;

	private String ope2;

	public ShareExpValue share;

	protected AbstractExpression() {
	}

	protected AbstractExpression(AbstractExpression from, ShareExpValue s) {
		string = from.string;
		pos = from.pos;
		prio = from.prio;
		if (s != null) {
			share = s;
		} else {
			share = from.share;
		}
		ope1 = from.ope1;
		ope2 = from.ope2;
	}

	/**
	 * 複製生成
	 * <p>
	 * 当インスタンスの複製を作成する
	 * </p>
	 *
	 * @param s
	 *            新共通情報
	 * @return 新インスタンス
	 * @since 2007.02.17
	 */
	public abstract AbstractExpression dup(ShareExpValue s);

	/**
	 * 演算子取得
	 * <p>
	 * 式クラスに固有の演算子を返す
	 * </p>
	 *
	 * @return 演算子
	 */
	public final String getOperator() {
		return ope1;
	}

	/**
	 * 終了演算子取得
	 * <p>
	 * ")"や"]"など
	 * </p>
	 *
	 * @return 演算子（存在しない場合はnull）
	 * @since 2007.02.16
	 */
	public final String getEndOperator() {
		return ope2;
	}

	/**
	 * 演算子セット
	 *
	 * @param ope
	 *            演算子
	 * @since 2007.02.17
	 */
	public final void setOperator(String ope) {
		ope1 = ope;
	}

	/**
	 * 終了演算子セット
	 *
	 * @param ope
	 *            演算子
	 * @since 2007.02.17
	 */
	public final void setEndOperator(String ope) {
		ope2 = ope;
	}

	/**
	 * 識別子取得
	 *
	 * @return 識別子
	 */
	public String getWord() {
		return getOperator();
	}

	protected void setWord(String word) {
		throw new EvalException(EvalException.EXP_FORBIDDEN_CALL, this, null);
	}

	/**
	 * �����擾.
	 * <p>
	 * ���Z�̍��̐����Ԃ��B<br>
	 * �i���F�񍀉��Z�q�̏ꍇ�A2���Ԃ��j
	 * </p>
	 *
	 * @return ����
	 */
	protected abstract int getCols();

	/**
	 * �ʒu�ݒ�.
	 *
	 * @param string
	 *            ������
	 * @param pos
	 *            �ʒu
	 */
	protected final void setPos(String string, int pos) {
		this.string = string;
		this.pos = pos;
	}

	/**
	 * 解析名取得
	 *
	 * @return 解析名
	 */
	public abstract String getExpressionName();

	/**
	 * 解析対象文字列取得
	 *
	 * @return 文字列
	 */
	public final String getString() {
		return string;
	}

	/**
	 * 位置取得
	 *
	 * @return 位置
	 */
	public final int getPos() {
		return pos;
	}

	/**
	 * �擪�ʒu�擾.
	 * <p>
	 * �����̉��Z�ɑ��������ԍ����̈ʒu���Ԃ��B
	 * </p>
	 *
	 * @return �ʒu
	 */
	protected abstract int getFirstPos();

	/** �D�揇��. */
	protected int prio;

	/**
	 * 優先順位設定
	 *
	 * @param prio
	 *            優先順位
	 * @since 2006.10.27
	 */
	public final void setPriority(int prio) {
		this.prio = prio;
	}

	/**
	 * �D�揇�ʎ擾.
	 *
	 * @return �D�揇��
	 * @since 2006.10.27
	 */
	protected final int getPriority() {
		return prio;
	}

	/**
	 * �������s.
	 * <p>
	 * �ϐ��i��Expression�j�ɒl�����������B
	 * </p>
	 *
	 * @param val
	 *            ���������l
	 * @param pos
	 *            ���Z�q�̈ʒu�i�G���[���Ɏg�p�j
	 * @throws EvalException
	 *             ���Ӓl���ϐ��łȂ��Ƃ�
	 * @since 2007.2.13
	 */
	protected void let(Object val, int pos) {
		// �ϐ��Ƃ��Ĉ������N���X�́A�����\�b�h���I�[�o�[���C�h���Ēl�����������B
		throw new EvalException(EvalException.EXP_NOT_LET, toString(), this,
				null);
	}

	/**
	 * �ϐ��擾.
	 * <p>
	 * �ϐ��l�擾�i�]���j�p�B
	 * </p>
	 *
	 * @return �ϐ����\���I�u�W�F�N�g
	 * @throws EvalException
	 *             �ϐ��ł͂Ȃ��Ƃ�
	 * @since 2006.10.27
	 * @version 2007.02.13
	 */
	protected Object getVariable() {
		// �ϐ��Ƃ��Ĉ������N���X�́A�����\�b�h���I�[�o�[���C�h���ĕϐ��I�u�W�F�N�g���Ԃ��B
		// int first = getFirstPos();
		// String word = string.substring(first, pos);
		String word = this.toString();
		throw new EvalException(EvalException.EXP_NOT_VARIABLE, word, this,
				null);
	}

	/**
	 * �����̒l�ݒ�.
	 * <p>
	 * �]�������s���ă��X�g�ɃZ�b�g�����B
	 * </p>
	 *
	 * @param args
	 *            �l���ݒ肷�郊�X�g
	 * @since 2005.02.15
	 */
	protected void evalArgs(List<Object> args) {
		args.add(eval());
	}

	/**
	 * 評価実行
	 * <p>
	 * Object型で演算を実施して結果を返す<br>
	 * 演算実行クラスを登録する必要あり
	 * </p>
	 *
	 * @return 演算結果
	 * @throws EvalException
	 *             演算中にエラーが発生したとき
	 * @since 2007.02.15
	 */
	public abstract Object eval();

	/**
	 * �T�����s(�[���D��).
	 *
	 * @since 2007.02.17
	 */
	protected abstract void search();

	/**
	 * �ϊ����s
	 *
	 * @return Expression
	 * @since 2007.02.20
	 */
	protected abstract AbstractExpression replace();

	/**
	 * ���Ӓl�Ƃ��ĕϊ����s
	 *
	 * @return Expression
	 * @since 2007.02.20
	 */
	protected abstract AbstractExpression replaceVar();

	/**
	 * オブジェクト比較
	 * <p>
	 * 木構造が等しいかどうかをチェックする<br>
	 * 演算子の文字列表現の違いは意識しない
	 * </p>
	 *
	 * @param obj
	 *            オブジェクト
	 * @return 等しいとき、true
	 * @see #same(AbstractExpression)
	 * @since 2007.02.27
	 */
	@Override
	public abstract boolean equals(Object obj);

	/**
	 * ハッシュコード値取得
	 *
	 * @return ハッシュコード値
	 * @since 2007.02.27
	 */
	@Override
	public abstract int hashCode();

	/**
	 * オブジェクト比較
	 * <p>
	 * 演算子の文字列表現まで含めてオブジェクトが等しいかどうかチェックする
	 * </p>
	 *
	 * @param exp
	 *            比較対象
	 * @return 等しいとき、true
	 * @see #equals(Object)
	 * @since 2007.02.27
	 */
	public boolean same(AbstractExpression exp) {
		return same(getOperator(), exp.getOperator())
				&& same(getEndOperator(), exp.getEndOperator())
				&& this.equals(exp);
	}

	private static boolean same(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equals(str2);
	}

	/**
	 * デバッグ用ダンプ
	 *
	 * @param n
	 *            タブ用の桁数
	 */
	public abstract void dump(int n);

	/**
	 * 文字列表現変換
	 * <p>
	 * 式の文字列表現を返す
	 * </p>
	 *
	 * @return 文字列表現
	 * @since 2006.10.27
	 */
	@Override
	public abstract String toString();
}
