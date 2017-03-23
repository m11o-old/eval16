package jp.hishidama.eval.rule;

import java.util.*;

import jp.hishidama.eval.exp.*;
import jp.hishidama.eval.lex.*;

/**
 * ルール抽象クラス
 * <p>
 * 演算子の種類毎の優先順位を表し、構文解析を実際に行うクラス
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >ひしだま</a>
 * @version 2007.02.16
 */
public abstract class AbstractRule {

	/** 次の優先順位のルール */
	public AbstractRule nextRule;

	protected ShareRuleValue share;

	public AbstractRule(ShareRuleValue share) {
		this.share = share;
	}

	/**
	 * 演算子登録
	 * <p>
	 * 自分のルールに属する演算子を追加する。初期化用<br>
	 * 併せて、その演算子の式クラスの初期化を行う
	 * </p>
	 *
	 * @param exp
	 *            式インスタンス（演算子文字列保持用）
	 */
	public final void addExpression(AbstractExpression exp) {
		if (exp == null) {
			return;
		}
		String ope = exp.getOperator();
		addOperator(ope, exp);
		addLexOperator(exp.getEndOperator());

		if (exp instanceof ParenExpression) {
			share.paren = exp;
		}
	}

	/**
	 * ���Z�q�Q.
	 * <p>
	 * �����D�揇�ʂɑ����鉉�Z�q�B<br>
	 * Map&lt;���Z�q, ���Z�q�C���X�^���X&gt;
	 * </p>
	 * 
	 * @version 2007.02.16
	 */
	private final Map<String, AbstractExpression> opes = new HashMap<String, AbstractExpression>();

	/**
	 * 演算子追加
	 * <p>
	 * 自分のルールに属する演算子を追加する。初期化用
	 * </p>
	 *
	 * @param ope
	 *            演算子
	 * @param exp
	 *            ダミー演算子インスタンス
	 */
	public final void addOperator(String ope, AbstractExpression exp) {
		opes.put(ope, exp);

		addLexOperator(ope);
	}

	/**
	 * 演算子一覧取得
	 *
	 * @return 全演算子
	 * @since 2007.02.16
	 */
	public final String[] getOperators() {
		List<String> list = new ArrayList<String>();
		for (String s : opes.keySet()) {
			list.add(s);
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 解釈対象演算子追加
	 * <p>
	 * 演算子と解釈する文字列を追加する。初期処理でのみ使用<br>
	 * ここで作成したopeListはLexで使用する
	 * </p>
	 *
	 * @param ope
	 *            演算子
	 * @see Lex#isOperator(int)
	 * @since 2007.02.16
	 */
	public final void addLexOperator(String ope) {
		if (ope == null) {
			return;
		}
		int n = ope.length() - 1;
		if (share.opeList[n] == null) {
			share.opeList[n] = new ArrayList<String>();
		}
		share.opeList[n].add(ope);
	}

	/**
	 * ���Z�q���f.
	 * <p>
	 * �����̉��Z�q���A�����̃��[���ɑ����邩�ǂ����`�F�b�N�����B
	 * </p>
	 *
	 * @param ope
	 *            ���Z�q
	 * @return �����ɑ������ꍇ�Atrue
	 * @version 2006.11.07
	 */
	protected final boolean isMyOperator(String ope) {
		return opes.containsKey(ope);
	}

	/**
	 * ���Z�q�C���X�^���X����.
	 *
	 * @param ope
	 *            ���Z�q
	 * @param share
	 *            ���ʏ���
	 * @return ���Z�q�C���X�^���X
	 * @since 2006.11.07
	 * @version 2007.02.09
	 */
	protected final AbstractExpression newExpression(String ope,
			ShareExpValue share) {
		try {
			AbstractExpression org = opes.get(ope);
			AbstractExpression n = org.dup(share);
			n.setPriority(prio);
			n.share = share;
			return n;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** 優先順位 */
	public int prio;

	/**
	 * 優先順位初期化
	 *
	 * @param prio
	 *            優先順位
	 * @since 2006.10.27
	 */
	public final void initPriority(int prio) {
		this.prio = prio;

		if (nextRule != null) {
			nextRule.initPriority(prio + 1);
		}
	}

	/**
	 * �\������.
	 *
	 * @param lex
	 *            �������̓C���X�^���X
	 * @return �\�����͖؃C���X�^���X
	 */
	protected abstract AbstractExpression parse(Lex lex);

}
