package jp.hishidama.eval;

import jp.hishidama.eval.exp.*;
import jp.hishidama.eval.func.Function;
import jp.hishidama.eval.lex.LexFactory;
import jp.hishidama.eval.log.EvalLog;
import jp.hishidama.eval.oper.Operator;
import jp.hishidama.eval.rule.*;
import jp.hishidama.eval.var.Variable;

//コメント修正 不完全

/**
 * ルールファクトリークラス
 * <p>
 * ルールのインスタンスを生成する
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >ひしだま</a>
 * @since 2007.02.16
 * @version eval16
 */
public class ExpRuleFactory {

	private static ExpRuleFactory me;

	/**
	 * ルールファクトリー取得
	 * <p>
	 * デフォルトのファクトリーインスタンスを返す
	 * </p>
	 *
	 * @return ルールファクトリー
	 */
	public static ExpRuleFactory getInstance() {
		if (me == null) {
			me = new ExpRuleFactory();
		}
		return me;
	}

	/**
	 * デフォルトルール取得
	 * <p>
	 * デフォルトのルールインスタンスを返す
	 * </p>
	 *
	 * @return ルール
	 */
	public static Rule getDefaultRule() {
		return getInstance().getRule();
	}

	/**
	 * Javaルール取得
	 * <p>
	 * Javaルールのインスタンスを返す
	 * </p>
	 *
	 * @return ルール
	 * @since 2007.02.21
	 */
	public static Rule getJavaRule() {
		return JavaRuleFactory.getInstance().getRule();
	}

	private Rule cachedRule;

	/**
	 * コンストラクター
	 */
	public ExpRuleFactory() {
	}

	/**
	 * ルール取得
	 * <p>
	 * 一度だけルールを作成してキャッシュし、常にそのルールを返す
	 * </p>
	 *
	 * @return ルール
	 */
	public Rule getRule() {
		if (cachedRule == null) {
			cachedRule = createRule();
		}
		return cachedRule;
	}

	/**
	 * ルールを作成
	 *
	 * @return ルール
	 */
	protected Rule createRule() {
		return createRule(null, null, null, null);
	}

	/**
	 * ルールを作成
	 *
	 * @param var
	 *            �f�t�H���g�ϐ��Q
	 * @param func
	 *            �f�t�H���g�֐��Q
	 * @param oper
	 *            �f�t�H���g���Z
	 * @param log
	 *            �f�t�H���g���O�o�̓I�u�W�F�N�g
	 * @return ルール
	 * @since eval16
	 */
	protected Rule createRule(Variable var, Function func, Operator oper,
			EvalLog log) {
		ShareRuleValue share = new ShareRuleValue();
		share.lexFactory = getLexFactory();
		init(share);

		share.defaultVar = var;
		share.defaultFunc = func;
		share.defaultOper = oper;
		share.defaultLog = log;

		return share;
	}

	/**
	 * init()���Ŏg�p�����A�ꎞ�I�ȃO���[�o���ϐ�
	 */
	protected AbstractRule topRule;

	/**
	 * ���[���\�z.
	 * <p>
	 * ���[�����D���x���ɍ\�z�����B
	 * </p>
	 * <p>
	 * �����ŌĂ��ł���createXXX()�́A�I�[�o�[���C�h���邱�Ƃɂ����ēƎ����[�����������B<br>
	 * createXXX()��null���Ԃ����ꍇ�A���̃��[���i���邢�͉��Z�q�j�͎g�����Ȃ��B
	 * </p>
	 *
	 * @see jp.hishidama.eval.sample.FactorySample
	 */
	protected void init(ShareRuleValue share) {
		topRule = null;

		// ���[���{�̂��쐬
		initMainRule(null, share);
		topRule.initPriority(1);
		share.topRule = topRule;

		// �֐��������[�����쐬
		initFuncArgRule(share);

		topRule = null;
	}

	/**
	 * ���[���{�̍\�z
	 *
	 * @param rule
	 *            ���O�̃��[��
	 * @param share
	 */
	protected AbstractRule initMainRule(AbstractRule rule, ShareRuleValue share) {
		// �D�揇�ʂ̒Ⴂ�����琶������
		rule = add(rule, createCommaRule(share));
		rule = add(rule, createLetRule(share));
		rule = add(rule, createIfRule(share));
		rule = add(rule, createOrRule(share));
		rule = add(rule, createAndRule(share));
		rule = add(rule, createBitOrRule(share));
		rule = add(rule, createBitXorRule(share));
		rule = add(rule, createBitAndRule(share));
		rule = add(rule, createEqualRule(share));
		rule = add(rule, createGreaterRule(share));
		rule = add(rule, createShiftRule(share));
		rule = add(rule, createPlusRule(share));
		rule = add(rule, createMultRule(share));
		rule = add(rule, createSignRule(share));
		rule = add(rule, createPowerRule(share));
		rule = add(rule, createCol1AfterRule(share));
		rule = add(rule, createPrimaryRule(share));
		return rule;
	}

	/**
	 * �֐��������[���\�z
	 *
	 * @param share
	 */
	protected void initFuncArgRule(ShareRuleValue share) {
		AbstractRule argRule = share.funcArgRule = createFuncArgRule(share);

		String[] a_opes = argRule.getOperators();
		String[] t_opes = topRule.getOperators();

		boolean match = false;
		s: for (String argOpe : a_opes) {
			for (String topOpe : t_opes) {
				if (argOpe.equals(topOpe)) {
					match = true;
					break s;
				}
			}
		}
		if (match) {
			// �������Z�q�����ꍇ�́A���D���x�̃��[�����Z�b�g
			argRule.nextRule = topRule.nextRule;
		} else {
			// ���Z�q���قȂ��ꍇ�́A�ŏ��ʃ��[�����Z�b�g
			argRule.nextRule = topRule;
		}
		argRule.prio = topRule.prio;
	}

	/**
	 * ���[���D���x�֘A�t��
	 *
	 * @param rule
	 *            �O���[��
	 * @param r
	 *            �V���[��
	 * @return �����[��
	 */
	protected final AbstractRule add(AbstractRule rule, AbstractRule r) {
		if (r == null) {
			return rule;
		}
		if (topRule == null) {
			topRule = r;
		}
		if (rule != null) {
			rule.nextRule = r;
		}
		return r;
	}

	/**
	 * �J���}���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createCommaRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createCommaExpression());
		return me;
	}

	protected AbstractExpression createCommaExpression() {
		return new CommaExpression();
	}

	/**
	 * �������[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createLetRule(ShareRuleValue share) {
		AbstractRule me = new Col2RightJoinRule(share);
		me.addExpression(createLetExpression());
		me.addExpression(createLetMultExpression());
		me.addExpression(createLetDivExpression());
		me.addExpression(createLetModExpression());
		me.addExpression(createLetPlusExpression());
		me.addExpression(createLetMinusExpression());
		me.addExpression(createLetShiftLeftExpression());
		me.addExpression(createLetShiftRightExpression());
		me.addExpression(createLetShiftRightLogicalExpression());
		me.addExpression(createLetAndExpression());
		me.addExpression(createLetOrExpression());
		me.addExpression(createLetXorExpression());
		me.addExpression(createLetPowerExpression());
		return me;
	}

	protected AbstractExpression createLetExpression() {
		return new LetExpression();
	}

	protected AbstractExpression createLetMultExpression() {
		return new LetMultExpression();
	}

	protected AbstractExpression createLetDivExpression() {
		return new LetDivExpression();
	}

	protected AbstractExpression createLetModExpression() {
		return new LetModExpression();
	}

	protected AbstractExpression createLetPlusExpression() {
		return new LetPlusExpression();
	}

	protected AbstractExpression createLetMinusExpression() {
		return new LetMinusExpression();
	}

	protected AbstractExpression createLetShiftLeftExpression() {
		return new LetShiftLeftExpression();
	}

	protected AbstractExpression createLetShiftRightExpression() {
		return new LetShiftRightExpression();
	}

	protected AbstractExpression createLetShiftRightLogicalExpression() {
		return new LetShiftRightLogicalExpression();
	}

	protected AbstractExpression createLetAndExpression() {
		return new LetAndExpression();
	}

	protected AbstractExpression createLetOrExpression() {
		return new LetOrExpression();
	}

	protected AbstractExpression createLetXorExpression() {
		return new LetXorExpression();
	}

	protected AbstractExpression createLetPowerExpression() {
		return new LetPowerExpression();
	}

	/**
	 * �������Z���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createIfRule(ShareRuleValue share) {
		Col3Rule me = new Col3Rule(share);
		me.addExpression(me.cond = createIfExpression());
		return me;
	}

	protected AbstractExpression createIfExpression() {
		return new IfExpression();
	}

	/**
	 * �_���a���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createOrRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createOrExpression());
		return me;
	}

	protected AbstractExpression createOrExpression() {
		return new OrExpression();
	}

	/**
	 * �_���σ��[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createAndRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createAndExpression());
		return me;
	}

	protected AbstractExpression createAndExpression() {
		return new AndExpression();
	}

	/**
	 * �r�b�g�_���a���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createBitOrRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createBitOrExpression());
		return me;
	}

	protected AbstractExpression createBitOrExpression() {
		return new BitOrExpression();
	}

	/**
	 * �r�b�g�r���I�_���a���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createBitXorRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createBitXorExpression());
		return me;
	}

	protected AbstractExpression createBitXorExpression() {
		return new BitXorExpression();
	}

	/**
	 * �r�b�g�_���σ��[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createBitAndRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createBitAndExpression());
		return me;
	}

	protected AbstractExpression createBitAndExpression() {
		return new BitAndExpression();
	}

	/**
	 * �������[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createEqualRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createEqualExpression());
		me.addExpression(createNotEqualExpression());
		return me;
	}

	protected AbstractExpression createEqualExpression() {
		return new EqualExpression();
	}

	protected AbstractExpression createNotEqualExpression() {
		return new NotEqualExpression();
	}

	/**
	 * ���r���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createGreaterRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createLessThanExpression());
		me.addExpression(createLessEqualExpression());
		me.addExpression(createGreaterThanExpression());
		me.addExpression(createGreaterEqualExpression());
		return me;
	}

	protected AbstractExpression createLessThanExpression() {
		return new LessThanExpression();
	}

	protected AbstractExpression createLessEqualExpression() {
		return new LessEqualExpression();
	}

	protected AbstractExpression createGreaterThanExpression() {
		return new GreaterThanExpression();
	}

	protected AbstractExpression createGreaterEqualExpression() {
		return new GreaterEqualExpression();
	}

	/**
	 * �V�t�g���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createShiftRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createShiftLeftExpression());
		me.addExpression(createShiftRightExpression());
		me.addExpression(createShiftRightLogicalExpression());
		return me;
	}

	protected AbstractExpression createShiftLeftExpression() {
		return new ShiftLeftExpression();
	}

	protected AbstractExpression createShiftRightExpression() {
		return new ShiftRightExpression();
	}

	protected AbstractExpression createShiftRightLogicalExpression() {
		return new ShiftRightLogicalExpression();
	}

	/**
	 * �����Z���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createPlusRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createPlusExpression());
		me.addExpression(createMinusExpression());
		return me;
	}

	protected AbstractExpression createPlusExpression() {
		return new PlusExpression();
	}

	protected AbstractExpression createMinusExpression() {
		return new MinusExpression();
	}

	/**
	 * �揜�Z���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createMultRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createMultExpression());
		me.addExpression(createDivExpression());
		me.addExpression(createModExpression());
		return me;
	}

	protected AbstractExpression createMultExpression() {
		return new MultExpression();
	}

	protected AbstractExpression createDivExpression() {
		return new DivExpression();
	}

	protected AbstractExpression createModExpression() {
		return new ModExpression();
	}

	/**
	 * �O�u�P�����Z�q���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createSignRule(ShareRuleValue share) {
		AbstractRule me = new Col1BeforeRule(share);
		me.addExpression(createSignPlusExpression());
		me.addExpression(createSignMinusExpression());
		me.addExpression(createBitNotExpression());
		me.addExpression(createNotExpression());
		me.addExpression(createIncBeforeExpression());
		me.addExpression(createDecBeforeExpression());
		return me;
	}

	protected AbstractExpression createSignPlusExpression() {
		return new SignPlusExpression();
	}

	protected AbstractExpression createSignMinusExpression() {
		return new SignMinusExpression();
	}

	protected AbstractExpression createBitNotExpression() {
		return new BitNotExpression();
	}

	protected AbstractExpression createNotExpression() {
		return new NotExpression();
	}

	protected AbstractExpression createIncBeforeExpression() {
		return new IncBeforeExpression();
	}

	protected AbstractExpression createDecBeforeExpression() {
		return new DecBeforeExpression();
	}

	/**
	 * �ݏ惋�[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createPowerRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createPowerExpression());
		return me;
	}

	protected AbstractExpression createPowerExpression() {
		return new PowerExpression();
	}

	/**
	 * ���u�P�����Z�q���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createCol1AfterRule(ShareRuleValue share) {
		Col1AfterRule me = new Col1AfterRule(share);
		me.addExpression(me.func = createFunctionExpression());
		me.addExpression(me.array = createArrayExpression());
		me.addExpression(createIncAfterExpression());
		me.addExpression(createDecAfterExpression());
		me.addExpression(me.field = createFieldExpression());
		return me;
	}

	protected AbstractExpression createFunctionExpression() {
		return new FunctionExpression();
	}

	protected AbstractExpression createArrayExpression() {
		return new ArrayExpression();
	}

	protected AbstractExpression createIncAfterExpression() {
		return new IncAfterExpression();
	}

	protected AbstractExpression createDecAfterExpression() {
		return new DecAfterExpression();
	}

	protected AbstractExpression createFieldExpression() {
		return new FieldExpression();
	}

	/**
	 * ���{���[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createPrimaryRule(ShareRuleValue share) {
		AbstractRule me = new PrimaryRule(share);
		me.addExpression(createParenExpression());
		// me.addOperator("(", null);
		// me.addLexOperator(")");
		return me;
	}

	protected AbstractExpression createParenExpression() {
		return new ParenExpression();
	}

	/**
	 * �֐��������[���쐬
	 *
	 * @param share
	 * @return ���[��
	 */
	protected AbstractRule createFuncArgRule(ShareRuleValue share) {
		AbstractRule me = new Col2Rule(share);
		me.addExpression(createFuncArgExpression());
		return me;
	}

	protected AbstractExpression createFuncArgExpression() {
		return new FuncArgExpression();
	}

	protected LexFactory defaultLexFactory;

	protected LexFactory getLexFactory() {
		if (defaultLexFactory == null) {
			defaultLexFactory = new LexFactory();
		}
		return defaultLexFactory;
	}
}
