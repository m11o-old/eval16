package jp.hishidama.eval;

import jp.hishidama.eval.exp.AbstractExpression;
import jp.hishidama.eval.lex.Lex;

/**
 * ���Z�G���[�N���X.
 * <p>
 * �\����͂Ɏ��s�����ꍇ��]���̎��s�Ɏ��s�����ꍇ�ɃX���[�����B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @version 2007.03.02
 */
@SuppressWarnings("serial")
public class EvalException extends RuntimeException {

	/**
	 * �����ʂ����݂��Ȃ�.
	 * <p>
	 * %0�F������
	 * </p>
	 */
	public static final int PARSE_NOT_FOUND_END_OP = 1001;

	/** ����`�̉��Z�q */
	public static final int PARSE_INVALID_OP = 1002;

	/** ����`�̕��� */
	public static final int PARSE_INVALID_CHAR = 1003;

	/** ���߂̓r���ŕ����񂪏I������ */
	public static final int PARSE_END_OF_STR = 1004;

	/** ���߂��I�������̂ɕ����񂪎c���Ă��� */
	public static final int PARSE_STILL_EXIST = 1005;

	/** �֐��Ƃ��Ĉ����Ȃ� */
	public static final int PARSE_NOT_FUNC = 1101;

	/**
	 * �֎~���ꂽ���\�b�h���Ăяo����.
	 * <p>
	 * �l�����錴���F
	 * <ul>
	 * <li>�K�v�ȃ��\�b�h���I�[�o�[���C�h����Ă��Ȃ�</li>
	 * <li>���̃N���X�ł́A�d�l�セ�̃��\�b�h���Ă�ł͂����Ȃ�</li>
	 * </p>
	 */
	public static final int EXP_FORBIDDEN_CALL = 2001;

	/** �ϐ��Ƃ��Ĉ����Ȃ� */
	public static final int EXP_NOT_VARIABLE = 2002;

	/** ���l�Ƃ��Ĉ����Ȃ� */
	public static final int EXP_NOT_NUMBER = 2003;

	/** ����ł��Ȃ� */
	public static final int EXP_NOT_LET = 2004;

	/**
	 * �����Ƃ��Ĉ����Ȃ�
	 */
	public static final int EXP_NOT_CHAR = 2005;

	/**
	 * ������Ƃ��Ĉ����Ȃ�
	 */
	public static final int EXP_NOT_STRING = 2006;

	/** �ϐ��l���擾�ł��Ȃ� */
	public static final int EXP_NOT_VAR_VALUE = 2101;

	/** �ϐ��ɑ���ł��Ȃ� */
	public static final int EXP_NOT_LET_VAR = 2102;

	/** �ϐ�������` */
	public static final int EXP_NOT_DEF_VAR = 2103;

	/** �I�u�W�F�N�g������` */
	public static final int EXP_NOT_DEF_OBJ = 2104;

	/** �z��l���擾�ł��Ȃ� */
	public static final int EXP_NOT_ARR_VALUE = 2201;

	/** �z��ɑ���ł��Ȃ� */
	public static final int EXP_NOT_LET_ARR = 2202;

	/** �t�B�[���h�l���擾�ł��Ȃ� */
	public static final int EXP_NOT_FLD_VALUE = 2301;

	/** �t�B�[���h�ɑ���ł��Ȃ� */
	public static final int EXP_NOT_LET_FIELD = 2302;

	/** �֐��Ăяo���̎��s */
	public static final int EXP_FUNC_CALL_ERROR = 2401;

	/**
	 * �G���[���b�Z�[�W�R�[�h.
	 *
	 * @since 2007.03.02
	 */
	protected int msgCode;

	/**
	 * �G���[���b�Z�[�W�̃I�v�V����.
	 *
	 * @since 2007.03.02
	 */
	protected String[] msgOpt;

	/** �G���[�̋N����������. */
	protected String string;

	/** �����񒆂̃G���[�̋N�����ʒu. */
	protected int pos = -1;

	/** ������͂��Ă�����. */
	protected String ename = "word";

	/** ���ߒ��̕�����. */
	protected String word;

	protected EvalException(RuntimeException e) {
		super(e);
	}

	/**
	 * �R���X�g���N�^�[
	 *
	 * @param msg
	 *            �G���[���b�Z�[�W�R�[�h
	 * @param lex
	 *            �����͈ʒu
	 * @since 2007.03.02
	 */
	public EvalException(int msg, Lex lex) {
		this(msg, null, lex);
	}

	/**
	 * �R���X�g���N�^�[
	 *
	 * @param msg
	 *            �G���[���b�Z�[�W�R�[�h
	 * @param opt
	 *            �G���[���b�Z�[�W�I�v�V����
	 * @param lex
	 *            �����͈ʒu
	 * @since 2007.03.02
	 */
	public EvalException(int msg, String[] opt, Lex lex) {
		this.msgCode = msg;
		this.msgOpt = opt;
		if (lex != null) {
			this.string = lex.getString();
			this.pos = lex.getPos();
			this.ename = "word";
			this.word = lex.getWord();
		}
	}

	/**
	 * �R���X�g���N�^�[
	 *
	 * @param msg
	 *            �G���[���b�Z�[�W�R�[�h
	 * @param exp
	 *            Expression
	 * @param e
	 *            �����ƂȂ�����O
	 */
	public EvalException(int msg, AbstractExpression exp, Throwable e) {
		this(msg, exp.getExpressionName(), exp.getWord(), exp.getString(), exp
				.getPos(), e);
	}

	/**
	 * �R���X�g���N�^�[
	 *
	 * @param msg
	 *            �G���[���b�Z�[�W�R�[�h
	 * @param word
	 *            �Ώە�����
	 * @param exp
	 *            Expression
	 * @param e
	 *            �����ƂȂ�����O
	 */
	public EvalException(int msg, String word, AbstractExpression exp,
			Throwable e) {
		this(msg, exp.getExpressionName(), word, exp.getString(), exp.getPos(),
				e);
	}

	/**
	 * �R���X�g���N�^�[
	 *
	 * @param msg
	 *            �G���[���b�Z�[�W�R�[�h
	 * @param expName
	 *            ��͖�
	 * @param word
	 *            �Ώە�����
	 * @param string
	 *            �S������
	 * @param pos
	 *            �ʒu
	 * @param e
	 *            �����ƂȂ�����O
	 * @since 2007.03.02
	 */
	public EvalException(int msg, String expName, String word, String string,
			int pos, Throwable e) {
		initException(e);
		this.msgCode = msg;
		this.string = string;
		this.pos = pos;
		this.ename = expName;
		this.word = word;
	}

	protected void initException(Throwable e) {
		while (e != null) {
			if (e.getClass() == RuntimeException.class && e.getCause() != null) {
				e = e.getCause();
			} else {
				break;
			}
		}
		if (e != null) {
			super.initCause(e);
		}
	}

	/**
	 * �G���[�R�[�h�擾
	 *
	 * @return �G���[�R�[�h
	 * @since 2007.03.02
	 */
	public int getErrorCode() {
		return msgCode;
	}

	/**
	 * ���b�Z�[�W�t�����擾
	 *
	 * @return �t�����
	 * @since 2007.03.02
	 */
	public String[] getOption() {
		return msgOpt;
	}

	/**
	 * ��͖��̎擾.
	 *
	 * @return ��͖�
	 */
	public String getExpressionName() {
		return ename;
	}

	/**
	 * �Ώێ��ʎq�擾
	 *
	 * @return ������
	 * @since 2007.03.02
	 */
	public String getWord() {
		return word;
	}

	/**
	 * ��͑O������擾
	 *
	 * @return ������
	 * @since 2007.03.02
	 */
	public String getString() {
		return string;
	}

	/**
	 * �G���[�����ʒu�擾
	 *
	 * @return �ʒu
	 * @since 2007.03.02
	 */
	public int getPos() {
		return pos;
	}

	/**
	 * @see EvalExceptionFormatter#toString(EvalException)
	 */
	@Override
	public String toString() {
		return EvalExceptionFormatter.getDefault().toString(this);
	}
}
