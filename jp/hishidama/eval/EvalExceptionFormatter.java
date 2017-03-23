package jp.hishidama.eval;

import static jp.hishidama.eval.EvalException.*;

/**
 * ���Z�G���[���b�Z�[�W�ҏW�N���X.
 * <p>
 * {@link EvalException}�p�̃G���[���b�Z�[�W��ҏW����B
 * </p>
 *
 * @author <a target="hishidama"
 *         href="http://www.ne.jp/asahi/hishidama/home/tech/soft/java/eval16.html"
 *         >�Ђ�����</a>
 * @since eval16
 */
public class EvalExceptionFormatter {

	private static EvalExceptionFormatter me;

	/**
	 * �f�t�H���g�t�H�[�}�b�^�[�擾
	 *
	 * @return �t�H�[�}�b�^�[
	 */
	public static EvalExceptionFormatter getDefault() {
		if (me == null) {
			me = new EvalExceptionFormatter();
		}
		return me;
	}

	/**
	 * �G���[���b�Z�[�W����.
	 * <p>
	 * {@link EvalException}�̃G���[���b�Z�[�W�𐶐�����B
	 * </p>
	 *
	 * @param e
	 *            ��O
	 * @return �G���[���b�Z�[�W
	 * @see EvalException#toString()
	 */
	public String toString(EvalException e) {
		String msg = getErrCodeMessage(e.getErrorCode());
		String fmt = getFormat(e, msg);
		return toString(e, fmt);
	}

	/**
	 * �R�[�h�ʃ��b�Z�[�W�擾
	 *
	 * @param code
	 *            �G���[�R�[�h
	 * @return �僁�b�Z�[�W�i�t�H�[�}�b�g�t���j
	 */
	public String getErrCodeMessage(int code) {
		switch (code) {
		case PARSE_NOT_FOUND_END_OP:
			return "���Z�q�u%0�v���݂�܂���B";
		case PARSE_INVALID_OP:
			return "���Z�q�̕��@�G���[�ł��B";
		case PARSE_INVALID_CHAR:
			return "���Ή��̎��ʎq�ł��B";
		case PARSE_END_OF_STR:
			return "���̉��߂̓r���ŕ����񂪏I�����Ă��܂��B";
		case PARSE_STILL_EXIST:
			return "���̉��߂��I���܂����������񂪎c���Ă��܂��B";
		case PARSE_NOT_FUNC:
			return "�֐��Ƃ��Ďg�p�ł��܂���B";
		case EXP_FORBIDDEN_CALL:
			return "�֎~����Ă��郁�\�b�h���Ăяo���܂����B";
		case EXP_NOT_VARIABLE:
			return "�ϐ��Ƃ��Ďg�p�ł��܂���B";
		case EXP_NOT_NUMBER:
			return "���l�Ƃ��Ďg�p�ł��܂���B";
		case EXP_NOT_LET:
			return "����ł��܂���B";
		case EXP_NOT_CHAR:
			return "�����Ƃ��Ďg�p�ł��܂���B";
		case EXP_NOT_STRING:
			return "������Ƃ��Ďg�p�ł��܂���B";
		case EXP_NOT_VAR_VALUE:
			return "�ϐ��̒l���擾�ł��܂���B";
		case EXP_NOT_LET_VAR:
			return "�ϐ��ɑ���ł��܂���B";
		case EXP_NOT_DEF_VAR:
			return "�ϐ�������`�ł��B";
		case EXP_NOT_DEF_OBJ:
			return "�I�u�W�F�N�g������`�ł��B";
		case EXP_NOT_ARR_VALUE:
			return "�z��̒l���擾�ł��܂���B";
		case EXP_NOT_LET_ARR:
			return "�z��ɑ���ł��܂���B";
		case EXP_NOT_FLD_VALUE:
			return "�t�B�[���h�̒l���擾�ł��܂���B";
		case EXP_NOT_LET_FIELD:
			return "�t�B�[���h�ɑ���ł��܂���B";
		case EXP_FUNC_CALL_ERROR:
			return "�֐��̌Ăяo���Ɏ��s���܂����B";
		default:
			return "�G���[���������܂����B";
		}
	}

	/**
	 * �t�H�[�}�b�g�擾
	 *
	 * @param e
	 *            ��O
	 * @param msgFmt
	 *            �僁�b�Z�[�W
	 * @return ���b�Z�[�W�t�H�[�}�b�g
	 */
	public String getFormat(EvalException e, String msgFmt) {
		StringBuilder fmt = new StringBuilder(128);
		fmt.append(msgFmt);

		String word = e.getWord();
		String string = e.getString();

		boolean bWord = false;
		if (word != null && word.length() > 0) {
			bWord = true;
			if (word.equals(string)) {
				bWord = false;
			}
		}
		if (bWord) {
			fmt.append(" %n=�u%w�v");
		}

		int pos = e.getPos();
		if (pos >= 0) {
			fmt.append(" pos=%p");
		}
		if (string != null) {
			fmt.append(" string=�u%s�v");
		}
		if (e.getCause() != null) {
			fmt.append(" cause by %e");
		}

		return fmt.toString();
	}

	/**
	 * �G���[���b�Z�[�W�擾.
	 * <p>
	 * �t�H�[�}�b�g�����Ƀ��b�Z�[�W��ҏW����B
	 * <table border="1">
	 * <tr>
	 * <td>%0�`9</td>
	 * <td>�t������0�`9�ԁB���݂��Ȃ��ꍇ�͋󕶎���</td>
	 * </tr>
	 * <tr>
	 * <td>%c</td>
	 * <td>�G���[�R�[�h</td>
	 * </tr>
	 * <tr>
	 * <td>%n</td>
	 * <td>��͖�</td>
	 * </tr>
	 * <tr>
	 * <td>%s</td>
	 * <td>��͑O�̕�����</td>
	 * </tr>
	 * <tr>
	 * <td>%p</td>
	 * <td>�G���[�����������A��͑O��������̈ʒu</td>
	 * </tr>
	 * <tr>
	 * <td>%w</td>
	 * <td>�G���[�̌����ƂȂ���������</td>
	 * </tr>
	 * <tr>
	 * <td>%e</td>
	 * <td>���炩�̗�O�����ŃG���[�ƂȂ����ꍇ�́A���̗�O</td>
	 * </tr>
	 * <tr>
	 * <td>%%</td>
	 * <td>�u%�v</td>
	 * </tr>
	 * </table>
	 * </p>
	 *
	 * @param e
	 *            ��O
	 * @param format
	 *            �t�H�[�}�b�g
	 * @return �G���[���b�Z�[�W
	 */
	public String toString(EvalException e, String format) {
		StringBuilder sb = new StringBuilder(256);
		int len = format.length();
		for (int i = 0; i < len; i++) {
			char c = format.charAt(i);
			if (c != '%') {
				sb.append(c);
			} else {
				if (i + 1 >= len) {
					sb.append(c);
					break;
				}
				c = format.charAt(++i);
				switch (c) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9': {
					int n = c - '0';
					String[] msgOpt = e.getOption();
					if (msgOpt != null && n < msgOpt.length) {
						sb.append(msgOpt[n]);
					} else {
						sb.append('%');
						sb.append(c);
					}
					break;
				}
				case 'c':
					sb.append(e.getErrorCode());
					break;
				case 'n':
					sb.append(e.getExpressionName());
					break;
				case 'w':
					sb.append(e.getWord());
					break;
				case 'p':
					sb.append(e.getPos());
					break;
				case 's':
					sb.append(e.getString());
					break;
				case 'e':
					sb.append(e.getCause());
					break;
				case '%':
					sb.append('%');
					break;
				default:
					sb.append('%');
					sb.append(c);
					break;
				}
			}
		}
		return sb.toString();
	}
}
