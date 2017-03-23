package jp.hishidama.util;

public class NumberUtil {

	/**
	 * ���l�ϊ�.
	 * <p>
	 * �ړ����t���̐��l�������long�ɕϊ�����B <table border="1"> <caption>�ړ���</caption>
	 * <tr>
	 * <td>0b</td>
	 * <td>��i��</td>
	 * </tr>
	 * <tr>
	 * <td>0o</td>
	 * <td>���i��</td>
	 * </tr>
	 * <tr>
	 * <td>0x</td>
	 * <td>�\�Z�i��</td>
	 * </tr>
	 * <tr>
	 * <td>���̑�</td>
	 * <td>�\�i��</td>
	 * </tr>
	 * </table>
	 * </p>
	 * <p>
	 * �ڔ����́A�P���ɖ�������B <table border="1"> <caption>�ڔ���</caption>
	 * <tr>
	 * <td>l</td>
	 * <td>.</td>
	 * </tr>
	 * </table>
	 * </p>
	 * 
	 * @param str
	 *            ������i�ړ����t���j
	 * @return ���l
	 * @throws NumberFormatException
	 */
	public static long parseLong(String str) {
		if (str == null) {
			return 0;
		}
		str = str.trim();
		int len = str.length();
		if (len <= 0) {
			return 0;
		}

		// �ڔ������J�b�g
		switch (str.charAt(len - 1)) {
		case 'l':
		case 'L':
		case '.':
			len--;
			break;
		}

		if (len >= 3 && str.charAt(0) == '0') {
			switch (str.charAt(1)) {
			case 'b':
			case 'B':
				return parseLongBin(str, 2, len - 2);
			case 'o':
			case 'O':
				return parseLongOct(str, 2, len - 2);
			case 'x':
			case 'X':
				return parseLongHex(str, 2, len - 2);
			}
		}
		return parseLongDec(str, 0, len);
	}

	/**
	 * ��i���ϊ�.
	 * <p>
	 * ��������i���Ƃ���long�ɕϊ�����B
	 * </p>
	 * 
	 * @param str
	 *            ������
	 * @return ���l
	 * @throws NumberFormatException
	 */
	public static long parseLongBin(String str) {
		if (str == null) {
			return 0;
		}
		return parseLongBin(str, 0, str.length());
	}

	/**
	 * ��i���ϊ�.
	 * <p>
	 * ��������i���Ƃ���long�ɕϊ�����B
	 * </p>
	 * 
	 * @param str
	 *            ������
	 * @param pos
	 *            �ʒu
	 * @param len
	 *            ����
	 * @return ���l
	 * @throws NumberFormatException
	 */
	public static long parseLongBin(String str, int pos, int len) {
		long ret = 0;
		for (int i = 0; i < len; i++) {
			ret *= 2;
			char c = str.charAt(pos++);
			switch (c) {
			case '0':
				break;
			case '1':
				ret += 1;
				break;
			default:
				throw new NumberFormatException(str.substring(pos, len));
			}
		}
		return ret;
	}

	/**
	 * ���i���ϊ�.
	 * <p>
	 * ������𔪐i���Ƃ���long�ɕϊ�����B
	 * </p>
	 * 
	 * @param str
	 *            ������
	 * @return ���l
	 * @throws NumberFormatException
	 */
	public static long parseLongOct(String str) {
		if (str == null) {
			return 0;
		}
		return parseLongOct(str, 0, str.length());
	}

	/**
	 * ���i���ϊ�.
	 * <p>
	 * ������𔪐i���Ƃ���long�ɕϊ�����B
	 * </p>
	 * 
	 * @param str
	 *            ������
	 * @param pos
	 *            �ʒu
	 * @param len
	 *            ����
	 * @return ���l
	 * @throws NumberFormatException
	 */
	public static long parseLongOct(String str, int pos, int len) {
		long ret = 0;
		for (int i = 0; i < len; i++) {
			ret *= 8;
			char c = str.charAt(pos++);
			switch (c) {
			case '0':
				break;
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
				ret += c - '0';
				break;
			default:
				throw new NumberFormatException(str.substring(pos, len));
			}
		}
		return ret;
	}

	/**
	 * �\�i���ϊ�.
	 * <p>
	 * ��������\�i���Ƃ���long�ɕϊ�����B
	 * </p>
	 * 
	 * @param str
	 *            ������
	 * @return ���l
	 * @throws NumberFormatException
	 */
	public static long parseLongDec(String str) {
		if (str == null) {
			return 0;
		}
		return parseLongDec(str, 0, str.length());
	}

	/**
	 * �\�i���ϊ�.
	 * <p>
	 * ��������\�i���Ƃ���long�ɕϊ�����B
	 * </p>
	 * 
	 * @param str
	 *            ������
	 * @param pos
	 *            �ʒu
	 * @param len
	 *            ����
	 * @return ���l
	 * @throws NumberFormatException
	 */
	public static long parseLongDec(String str, int pos, int len) {
		long ret = 0;
		for (int i = 0; i < len; i++) {
			ret *= 10;
			char c = str.charAt(pos++);
			switch (c) {
			case '0':
				break;
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				ret += c - '0';
				break;
			default:
				throw new NumberFormatException(str.substring(pos, len));
			}
		}
		return ret;
	}

	/**
	 * �\�Z�i���ϊ�.
	 * <p>
	 * ��������\�Z�i���Ƃ���long�ɕϊ�����B
	 * </p>
	 * 
	 * @param str
	 *            ������
	 * @return ���l
	 * @throws NumberFormatException
	 */
	public static long parseLongHex(String str) {
		if (str == null) {
			return 0;
		}
		return parseLongHex(str, 0, str.length());
	}

	/**
	 * �\�Z�i���ϊ�.
	 * <p>
	 * ��������\�Z�i���Ƃ���long�ɕϊ�����B
	 * </p>
	 * 
	 * @param str
	 *            ������
	 * @param pos
	 *            �ʒu
	 * @param len
	 *            ����
	 * @return ���l
	 * @throws NumberFormatException
	 */
	public static long parseLongHex(String str, int pos, int len) {
		long ret = 0;
		for (int i = 0; i < len; i++) {
			ret *= 16;
			char c = str.charAt(pos++);
			switch (c) {
			case '0':
				break;
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				ret += c - '0';
				break;
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
				ret += c - 'a' + 10;
				break;
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
				ret += c - 'A' + 10;
				break;
			default:
				throw new NumberFormatException(str.substring(pos, len));
			}
		}
		return ret;
	}

}
