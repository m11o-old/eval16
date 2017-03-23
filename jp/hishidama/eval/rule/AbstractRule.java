package jp.hishidama.eval.rule;

import java.util.*;

import jp.hishidama.eval.exp.*;
import jp.hishidama.eval.lex.*;

/**
 * ���[�����ۃN���X.
 * <p>
 * ���Z�q�̎�ޖ��̗D�揇�ʂ�\���A�\����͂����ۂɍs���N���X�B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @version 2007.02.16
 */
public abstract class AbstractRule {

	/** ���̗D�揇�ʂ̃��[��. */
	public AbstractRule nextRule;

	protected ShareRuleValue share;

	public AbstractRule(ShareRuleValue share) {
		this.share = share;
	}

	/**
	 * ���Z�q�o�^.
	 * <p>
	 * �����̃��[���ɑ����鉉�Z�q��ǉ�����B�������p�B<br>
	 * �����āA���̉��Z�q�̎��N���X�̏��������s���B
	 * </p>
	 *
	 * @param exp
	 *            ���C���X�^���X�i���Z�q������ێ��p�j
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
	 * ���Z�q�ǉ�.
	 * <p>
	 * �����̃��[���ɑ����鉉�Z�q��ǉ�����B�������p�B
	 * </p>
	 *
	 * @param ope
	 *            ���Z�q
	 * @param exp
	 *            �_�~�[���Z�q�C���X�^���X
	 */
	public final void addOperator(String ope, AbstractExpression exp) {
		opes.put(ope, exp);

		addLexOperator(ope);
	}

	/**
	 * ���Z�q�ꗗ�擾.
	 *
	 * @return �S���Z�q
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
	 * ���ߑΏۉ��Z�q�ǉ�.
	 * <p>
	 * ���Z�q�Ɖ��߂��镶�����ǉ�����B���������ł̂ݎg�p�B<br>
	 * �����ō쐬����opeList��Lex�Ŏg�p����B
	 * </p>
	 *
	 * @param ope
	 *            ���Z�q
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
	 * �����̉��Z�q���A�����̃��[���ɑ����邩�ǂ����`�F�b�N����B
	 * </p>
	 *
	 * @param ope
	 *            ���Z�q
	 * @return �����ɑ�����ꍇ�Atrue
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
	 *            ���ʏ��
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

	/** �D�揇��. */
	public int prio;

	/**
	 * �D�揇�ʏ�����.
	 *
	 * @param prio
	 *            �D�揇��
	 * @since 2006.10.27
	 */
	public final void initPriority(int prio) {
		this.prio = prio;

		if (nextRule != null) {
			nextRule.initPriority(prio + 1);
		}
	}

	/**
	 * �\�����.
	 *
	 * @param lex
	 *            �����̓C���X�^���X
	 * @return �\����͖؃C���X�^���X
	 */
	protected abstract AbstractExpression parse(Lex lex);

}
