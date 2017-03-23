package jp.hishidama.eval;

import jp.hishidama.eval.exp.AbstractExpression;
import jp.hishidama.eval.func.Function;
import jp.hishidama.eval.log.EvalLog;
import jp.hishidama.eval.oper.DoubleOperator;
import jp.hishidama.eval.oper.IntOperator;
import jp.hishidama.eval.oper.LongOperator;
import jp.hishidama.eval.oper.Operator;
import jp.hishidama.eval.ref.Refactor;
import jp.hishidama.eval.repl.Replace;
import jp.hishidama.eval.srch.Search;
import jp.hishidama.eval.var.Variable;

/**
 * ���N���X.
 * <p>
 * �\����͖؂�ێ����A���Z�̕]�������{����B
 * </p>
 *
 * @see Rule#parse(String)
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @version eval16
 */
public abstract class Expression {

	public Variable var;

	public Function func;

	public Operator oper;

	public EvalLog log;

	public Search srch;

	public Replace repl;

	protected AbstractExpression ae;

	/**
	 * �\����͎��s.
	 * <p>
	 * �f�t�H���g���[���ō\����͂��s���B
	 * </p>
	 *
	 * @param str
	 *            ��͑Ώە�����
	 * @return �\����͌���
	 * @throws EvalException
	 *             �\�������������Ƃ�
	 * @see ExpRuleFactory#getDefaultRule()
	 * @see Rule#parse(String)
	 */
	public static Expression parse(String str) {
		return ExpRuleFactory.getDefaultRule().parse(str);
	}

	/**
	 * �ϐ��Q�ݒ�.
	 * <p>
	 * �]�����s�̍ۂ� ���̒��ɕϐ�������΁A�����\�b�h�Ŏw�肵���ϐ��I�u�W�F�N�g�̃��\�b�h���Ă΂��B
	 * </p>
	 *
	 * @param var
	 *            �ϐ��I�u�W�F�N�g
	 * @see #evalInt()
	 * @see #evalLong()
	 * @see #evalDouble()
	 * @see #eval()
	 * @since 2007.02.09
	 */
	public void setVariable(Variable var) {
		this.var = var;
	}

	/**
	 * �֐��Q�ݒ�.
	 * <p>
	 * �]�����s�̍ۂ� ���̒��Ɋ֐�������΁A�����\�b�h�Ŏw�肵���֐��I�u�W�F�N�g�̃��\�b�h���Ă΂��B
	 * </p>
	 *
	 * @param func
	 *            �֐��I�u�W�F�N�g
	 * @see #evalInt()
	 * @see #evalLong()
	 * @see #evalDouble()
	 * @see #eval()
	 */
	public void setFunction(Function func) {
		this.func = func;
	}

	/**
	 * ���Z�ݒ�.
	 * <p>
	 * �]�����s�̍ۂ� ���̒��ɉ��Z�i+��-���j������΁A�w�肵�����Z�I�u�W�F�N�g�̃��\�b�h���Ă΂��B
	 * </p>
	 *
	 * @param oper
	 *            ���Z�I�u�W�F�N�g
	 * @see #eval()
	 * @since 2007.02.15
	 */
	public void setOperator(Operator oper) {
		this.oper = oper;
	}

	/**
	 * ���O�o�͐ݒ�.
	 * <p>
	 * �]�����s�̍ۂɎw�肵�����O�o�̓I�u�W�F�N�g�̃��\�b�h���Ă΂��B
	 * </p>
	 *
	 * @param log
	 *            ���O�o�̓I�u�W�F�N�g
	 * @see #eval()
	 * @since eval16
	 */
	public void setEvalLog(EvalLog log) {
		this.log = log;
	}

	/**
	 * �]�����s(int).
	 *<p>
	 * �����\�b�h�ł́A{@link IntOperator}���g�p���ĉ��Z����B
	 * </p>
	 *
	 * @return ���Z����
	 * @throws EvalException
	 *             ���Z���ɃG���[�����������Ƃ�
	 * @see #setOperator(Operator)
	 * @see IntOperator
	 * @since eval16
	 */
	public int evalInt() {
		Operator bak = oper;
		try {
			if (!(oper instanceof IntOperator)) {
				setOperator(new IntOperator());
			}
			Number n = (Number) eval();
			if (n != null) {
				return n.intValue();
			} else {
				return 0;
			}
		} finally {
			oper = bak;
		}
	}

	/**
	 * �]�����s(long).
	 *<p>
	 * �����\�b�h�ł́A{@link LongOperator}���g�p���ĉ��Z����B
	 * </p>
	 *
	 * @return ���Z����
	 * @throws EvalException
	 *             ���Z���ɃG���[�����������Ƃ�
	 * @see #setOperator(Operator)
	 * @see LongOperator
	 */
	public long evalLong() {
		Operator bak = oper;
		try {
			if (!(oper instanceof LongOperator)) {
				setOperator(new LongOperator());
			}
			Number n = (Number) eval();
			if (n != null) {
				return n.longValue();
			} else {
				return 0;
			}
		} finally {
			oper = bak;
		}
	}

	/**
	 * �]�����s(double).
	 *<p>
	 * �����\�b�h�ł́A{@link DoubleOperator}���g�p���ĉ��Z����B
	 * </p>
	 *
	 * @return ���Z����
	 * @throws EvalException
	 *             ���Z���ɃG���[�����������Ƃ�
	 * @see #setOperator(Operator)
	 * @see DoubleOperator
	 */
	public double evalDouble() {
		Operator bak = oper;
		try {
			if (!(oper instanceof DoubleOperator)) {
				setOperator(new DoubleOperator());
			}
			Number n = (Number) eval();
			if (n != null) {
				return n.doubleValue();
			} else {
				return 0;
			}
		} finally {
			oper = bak;
		}
	}

	/**
	 * �]�����s(Object).
	 * <p>
	 * Object�^�ŉ��Z�����{���Č��ʂ�Ԃ��B<br>
	 * ���Z���s�N���X��o�^����K�v����B
	 * </p>
	 *
	 * @see #setOperator(Operator)
	 * @return ���Z����
	 * @throws EvalException
	 *             ���Z���ɃG���[�����������Ƃ�
	 * @since 2007.02.15
	 */
	public abstract Object eval();

	/**
	 * �œK�����s(Object).
	 * <p>
	 * ���ȈՍœK�����s���B���Z�͎w�肳�ꂽoper���g���čs���B<br>
	 * �ϐ��ɒl�������Ă���ꍇ�A�萔�ƌ��Ȃ��A�l�ɒu������B
	 * </p>
	 *
	 * @param var
	 *            �萔�Ƃ��Ĉ����ϐ��Q�inull�j
	 * @param oper
	 *            ���Z���s�N���X
	 * @throws EvalException
	 *             �œK�����ɃG���[�����������Ƃ�
	 * @since 2007.02.21
	 */
	public abstract void optimize(Variable var, Operator oper);

	/**
	 * �T�����s.
	 * <p>
	 * �\����͖؂̒T�����s���B<br>
	 * �S�\����͖؂ɂ��āA1���T���C���^�[�t�F�[�X�̃��\�b�h���Ăяo���B
	 * </p>
	 *
	 * @param srch
	 *            �T���C���^�[�t�F�[�X
	 * @see Search#search(AbstractExpression)
	 * @since 2007.02.17
	 */
	public abstract void search(Search srch);

	/**
	 * ���t�@�N�^�����O�i���ʎq���ύX�j.
	 * <p>
	 * �ϐ���/�֐������邢�̓I�u�W�F�N�g�̃t�B�[���h��/���\�b�h����ϊ�����B
	 * </p>
	 * <p>
	 * ���̒��ɃI�u�W�F�N�g�̃����o�[�����݂���ꍇ�́A�I�u�W�F�N�g���擾���ĕύX�Ώۂ̃I�u�W�F�N�g���ǂ������肷��B<br>
	 * ���������āA�I�u�W�F�N�g���݂�ꍇ��setVariable()�ŃI�u�W�F�N�g�̕ϐ���Ԃ��悤�ɂ��Ă����K�v������B
	 * </p>
	 *
	 * @param ref
	 *            ���t�@�N�^�����O�C���^�[�t�F�[�X
	 * @since 2007.02.19
	 */
	public abstract void refactorName(Refactor ref);

	/**
	 * ���t�@�N�^�����O�i�֐��ւ̕ύX�j.
	 * <p>
	 * �ϐ������邢�̓I�u�W�F�N�g�̃t�B�[���h�����֐��i���邢�̓��\�b�h�j�ɕϊ�����B
	 * </p>
	 * <p>
	 * ���̒��ɃI�u�W�F�N�g�̃����o�[�����݂���ꍇ�́A�I�u�W�F�N�g���擾���ĕύX�Ώۂ̃I�u�W�F�N�g���ǂ������肷��B<br>
	 * ���������āA�I�u�W�F�N�g���݂�ꍇ��setVariable()�ŃI�u�W�F�N�g�̕ϐ���Ԃ��悤�ɂ��Ă����K�v������B
	 * </p>
	 *
	 * @param ref
	 *            ���t�@�N�^�����O�C���^�[�t�F�[�X
	 * @param rule
	 *            �ϊ����Ɏg�p���郋�[��
	 * @since 2007.02.20
	 */
	public abstract void refactorFunc(Refactor ref, Rule rule);

	/**
	 * �����쐬.
	 * <p>
	 * ���C���X�^���X�̕������쐬����B
	 * </p>
	 *
	 * @return ����
	 * @since 2007.02.17
	 */
	public abstract Expression dup();

	/**
	 * �I�u�W�F�N�g��r.
	 * <p>
	 * ���Z��͌��ʂ����������ǂ������`�F�b�N����B<br>
	 * ���Z�q�̕�����\���̈Ⴂ�͈ӎ����Ȃ��B
	 * </p>
	 *
	 * @param obj
	 *            �I�u�W�F�N�g
	 * @return �������Ƃ��Atrue
	 * @see #same(Expression)
	 * @since 2007.02.27
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Expression) {
			AbstractExpression e = ((Expression) obj).ae;
			if (ae == null && e == null) {
				return true;
			}
			if (ae == null || e == null) {
				return false;
			}
			return ae.equals(e);
		}
		return super.equals(obj);
	}

	/**
	 * �n�b�V���R�[�h�l�擾.
	 *
	 * @return �n�b�V���R�[�h�l
	 * @since 2007.02.27
	 */
	@Override
	public int hashCode() {
		if (ae == null) {
			return 0;
		}
		return ae.hashCode();
	}

	/**
	 * �I�u�W�F�N�g��r.
	 * <p>
	 * ���Z�q�̕�����\���܂Ŋ܂߂ăI�u�W�F�N�g�����������ǂ����`�F�b�N����B
	 * </p>
	 *
	 * @param obj
	 *            ��r�Ώ�
	 * @return �������Ƃ��Atrue
	 * @see #equals(Object)
	 * @since 2007.02.27
	 */
	public boolean same(Expression obj) {
		AbstractExpression e = obj.ae;
		if (ae == null) {
			return e == null;
		}
		return ae.same(e);
	}

	/**
	 * ��`�F�b�N.
	 * <p>
	 * ��͌��ʂ��󂩂ǂ������`�F�b�N����B
	 * </p>
	 *
	 * @return ��̂Ƃ��Atrue
	 * @since 2007.03.01
	 */
	public boolean isEmpty() {
		return ae == null;
	}

	@Override
	public String toString() {
		if (ae == null) {
			return "";
		}
		return ae.toString();
	}
}
